package cn.ekgc.esp.controller;

import cn.ekgc.esp.Service.RoleService;
import cn.ekgc.esp.Service.UserService;
import cn.ekgc.esp.base.controller.BaseController;
import cn.ekgc.esp.pojo.entity.Role;
import cn.ekgc.esp.pojo.entity.User;
import cn.ekgc.esp.pojo.vo.Page;
import cn.ekgc.esp.pojo.vo.ResultVO;
import cn.ekgc.esp.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <b>用户模块控制层</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService service;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private SendEmailUtil send;

	/**
	 * <b>转发到登录界面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/login")
	public String forwardLoginPage() throws Exception {
		return "user/user_login";
	}

	/**
	 * <b>采用异步方式进行系统登录</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	@ResponseBody
	public ResultVO loginUser(User user) throws Exception {
		// 校验用户手机号码和登录密码是否有效
		if (ValidationUtil.checkCellphone(user.getCellphone())
				&& ValidationUtil.checkPassword(user.getPassword())) {
			User loginUser = service.getUserByCellphone(user.getCellphone());
			if (loginUser != null && loginUser.getPassword().equals(MD5Util.encrypt(user.getPassword()))) {
				if (loginUser.getStatus().equals(ConstantUtil.SYSTEM_STATUS_ENABLE)) {
					// 登录成功，使用 Token 技术完成用户状态保持
					/*
						在 web 应用中，以 token 为核心的 Auth 2.0，使用 JWT 实现
					 */
					// 生成对应的 Token
					String token = TokenUtils.createToken(loginUser.getId());
					// 使用 HttpServletResponse 将 Token 交给浏览器
					response.setHeader("Authorization", token);
					// 将生成的用户信息存储于 Redis 中
					redisTemplate.opsForValue().set(token, loginUser.getId().toString());
					// 设置 Redis 中过期时间
					redisTemplate.expire(token, 30, TimeUnit.MINUTES);
					return ResultVO.successResult();
				} else {
					ResultVO.failureResult("该用户已被禁止登录");
				}
			}
		}
		return ResultVO.failureResult("请填写有效的手机号码和登录密码");
	}

	@GetMapping("/index/{id}")
	public String forwardIndexPage() throws Exception {
		return "user/user_index";
	}

	/**
	 * <b>分页查询信息</b>
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page")
	@ResponseBody
	public Page<User> queryPage(Integer pageNum, Integer pageSize, Integer draw) throws Exception {
		Page<User> page = new Page<User>(pageNum, pageSize, draw);
		// 进行数据查询
		page = service.getPage(page);
		return page;
	}

	/**
	 * <b>根据主键修改状态</b>
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/status/{id}")
	@ResponseBody
	public ResultVO changeStatus(@PathVariable("id") Long id, Integer status) throws Exception {
		// 封装对象
		User user = new User();
		user.setId(id);
		user.setStatus(status);
		User query = service.getUserById(id);
		boolean flag = service.update(user);
		if (flag) {
			String msg = "";
			String title = "";
			if (status == 0) {
				title = "ESP禁用提示";
				msg = "您已被管理员禁用";
			} else {
				title = "ESP启用提示";
				msg = "您已被管理员启用";
			}
			send.send("296506895@qq.com", query.getAvatar(), title, msg);
			return ResultVO.successResult("修改成功！");
		}
		return ResultVO.failureResult("修改失败！");
	}

	@GetMapping("/detail/{id}")
	public String forwardDetail(@PathVariable Long id, ModelMap map) throws Exception {
		User user = service.getUserById(id);
		map.put("user", user);
		return "user/detail";
	}

	@GetMapping("/update/{id}")
	public String forwardUpdate(@PathVariable Long id, ModelMap map) throws Exception {
		User user = service.getUserById(id);
		List<Role> roleList = roleService.getList();
		map.put("upUser", user);
		map.put("roleList", roleList);
		return "user/update";
	}

	@PostMapping("/update")
	@ResponseBody
	public ResultVO<User> updateAndSave(User entity, Long roleId) throws Exception {
		if (entity.getPassword() != null && !"".equals(entity.getPassword())) {
			// 此时是修改密码
			boolean flag = service.update(entity);
			if (flag) {
				return ResultVO.successResult("密码修改成功！");
			}
			return ResultVO.failureResult("密码长度不得小于4位!");
		}
		if (entity.getName() != null && !"".equals(entity.getName().trim()) && entity.getCellphone() != null
				&& !"".equals(entity.getCellphone().trim()) && entity.getIdCard() != null && !"".equals(entity.getIdCard().trim())
				&& entity.getAvatar() != null && !"".equals(entity.getAvatar().trim())) {
			if (ValidationUtil.checkCellphone(entity.getCellphone())) {
				if (ValidationUtil.checkIdCard(entity.getIdCard())) {
					if (ValidationUtil.checkEmail(entity.getAvatar())) {
						if (entity.getId() != null) {
							// 此时是修改信息
							Long id = entity.getId();
							User user = service.getUserById(id);
							Role roleNew = new Role();
							roleNew.setId(roleId);
							entity.setRole(roleNew);
							Long roleIdOld = user.getRole().getId();
							if (user.getCellphone().equals(entity.getCellphone())) {
								// 未修改手机号码
								if (user.getIdCard().equals(entity.getIdCard())) {
									// 身份证号码未修改
									boolean flag = service.update(entity);
									if (flag) {
										if (roleId != roleIdOld) {
											Role role = roleService.getById(roleId);
											String msg = "您已被ESP管理员将角色修改为" + role.getName();
											send.send("296506895@qq.com", entity.getAvatar(), "ESP信息修改", msg);
										}
										return ResultVO.successResult();
									} else {
										return ResultVO.failureResult("修改失败");
									}
								} else {
									// 身份证号码修改
									boolean flag3 = false;
									if (service.getUserByIdCard(entity.getIdCard()) == null) {
										flag3 = true;
									}
									if (flag3) {
										// 身份证号码未修改
										boolean flag = service.update(entity);
										if (flag) {
											if (roleId != roleIdOld) {
												Role role = roleService.getById(roleId);
												String msg = "您已被ESP管理员将角色修改为" + role.getName();
												send.send("296506895@qq.com", entity.getAvatar(), "ESP信息修改", msg);
											}
											return ResultVO.successResult();
										} else {
											return ResultVO.failureResult("修改失败");
										}
									}
									return ResultVO.failureResult("身份证号码已存在");
								}
							} else {
								// 手机号码已修改
								boolean flag1 = false;
								if (service.getUserByCellphone(entity.getCellphone()) == null) {
									flag1 = true;
								}
								if (flag1) {
									if (user.getIdCard().equals(entity.getIdCard())) {
										// 身份证号码未修改
										boolean flag = service.update(entity);
										if (flag) {
											if (roleId != roleIdOld) {
												Role role = roleService.getById(roleId);
												String msg = "您已被ESP管理员将角色修改为" + role.getName();
												send.send("296506895@qq.com", entity.getAvatar(), "ESP信息修改", msg);
											}
											return ResultVO.successResult();
										} else {
											return ResultVO.failureResult("修改失败");
										}
									} else {
										// 身份证号码修改
										boolean flag2 = false;
										if (service.getUserByIdCard(entity.getIdCard()) == null) {
											flag2 = true;
										}
										if (flag2) {
											// 身份证号码未修改
											boolean flag = service.update(entity);
											if (flag) {
												if (roleId != roleIdOld) {
													Role role = roleService.getById(roleId);
													String msg = "您已被ESP管理员将角色修改为" + role.getName();
													send.send("296506895@qq.com", entity.getAvatar(), "ESP信息修改", msg);
												}
												return ResultVO.successResult();
											} else {
												return ResultVO.failureResult("修改失败");
											}
										}
										return ResultVO.failureResult("身份证号码已存在");
									}
								}
								return ResultVO.failureResult("手机号码已存在");
							}
						} else {
							// 此时是新增信息
							boolean flag1 = false;
							if (service.getUserByCellphone(entity.getCellphone()) == null) {
								flag1 = true;
							}
							boolean flag2 = false;
							if (service.getUserByIdCard(entity.getIdCard()) == null) {
								flag2 = true;
							}
							if (flag1 && flag2) {
								Role role = new Role();
								role.setId(roleId);
								entity.setRole(role);
								boolean flag = service.save(entity);
								if (flag) {
									return ResultVO.successResult();
								} else {
									return ResultVO.failureResult("添加失败");
								}
							}
							return ResultVO.failureResult("手机号码或身份证号码已存在");
						}
					}
					return ResultVO.failureResult("邮箱格式错误");
				}
				return ResultVO.failureResult("身份证号码格式错误");
			}
			return ResultVO.failureResult("手机号码格式错误");
		}
		return ResultVO.failureResult("请填写必填信息！");
	}

	@GetMapping("/save/{id}")
	public String saveUser(@PathVariable Long id) throws Exception {
		List<Role> roleList = roleService.getList();
		request.setAttribute("roleList", roleList);
		return "user/user_save";
	}

	@GetMapping("/info/{id}")
	public String show(@PathVariable Long id) throws Exception {
		User user = service.getUserById(id);
		request.setAttribute("user", user);
		return "user/user_info";
	}

	@GetMapping("/change/{id}")
	public String change(@PathVariable Long id) throws Exception {
		User user = service.getUserById(id);
		request.setAttribute("infUser", user);
		return "user/changeInf";
	}
	@GetMapping("/password/{id}")
	public String changePassword(@PathVariable Long id) throws Exception {
		request.setAttribute("pId", id);
		return "user/password";
	}

	@GetMapping("forget")
	public String forwardForget() throws Exception {
		return "user/forget";
	}

	/**
	 * <b>验证身份证号码和邮箱</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/check")
	@ResponseBody
	public ResultVO check(User user) throws Exception {
		if (user.getIdCard() != null && !"".equals(user.getIdCard().trim())
				&& user.getAvatar() != null && !"".equals(user.getAvatar().trim())) {
			if (ValidationUtil.checkIdCard(user.getIdCard())) {
				if (ValidationUtil.checkEmail(user.getAvatar())) {
					User query = service.getUserByQuery(user);
					if (query != null) {
						Random random = new Random();
						String result="";
						for (int i=0;i<6;i++)
						{
							result+=random.nextInt(10);
						}
						send.send("296506895@qq.com", query.getAvatar(), "修改密码验证码", result + "有效时间5分钟");
						redisTemplate.opsForValue().set("vcode", result);
						redisTemplate.opsForValue().set("userId", query.getId().toString());
						redisTemplate.expire("vcode", 5, TimeUnit.MINUTES);
						redisTemplate.expire("userId", 5, TimeUnit.MINUTES);
						return ResultVO.successResult();
					}
					return ResultVO.failureResult("请输入正确的个人信息");
				}
				return ResultVO.failureResult("邮箱格式错误");
			}
			return ResultVO.failureResult("身份证号码格式错误!");
		}
		return ResultVO.failureResult("身份证号码和电子邮箱不能为空");
	}

	@PostMapping("/changePs")
	@ResponseBody
	public ResultVO changePs(String vcode, String password) throws Exception {
		if (vcode != null && !"".equals(vcode.trim()) && password != null
				&& !"".equals(password)) {
			String code = redisTemplate.opsForValue().get("vcode");
			if (code.equals(vcode)) {
				Long id = Long.parseLong(redisTemplate.opsForValue().get("userId"));
				User user = new User();
				user.setId(id);
				user.setPassword(password);
				boolean flag = service.update(user);
				if (flag) {
					return ResultVO.successResult("修改成功");
				}
				return ResultVO.failureResult("修改失败");
			}
			return ResultVO.failureResult("验证码错误");
		}
		return ResultVO.failureResult("验证码和密码不可为空");
	}
}
