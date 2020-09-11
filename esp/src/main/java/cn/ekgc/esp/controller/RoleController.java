package cn.ekgc.esp.controller;

import cn.ekgc.esp.Service.RoleService;
import cn.ekgc.esp.base.controller.BaseController;
import cn.ekgc.esp.pojo.entity.Role;
import cn.ekgc.esp.pojo.vo.Page;
import cn.ekgc.esp.pojo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <b>角色模块控制层</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
@Controller("roleController")
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/index/{id}")
	public String forwardRoleIndex() throws Exception {
		return "role/role_index";
	}

	@GetMapping("/index")
	public String roleIndex() throws Exception {
		return "role/role_index";
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
	public Page<Role> queryPage(Integer pageNum, Integer pageSize, Integer draw) throws Exception {
		Page<Role> page = new Page<Role>(pageNum, pageSize, draw);
		// 进行数据查询
		page = roleService.getPage(page, null);
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
		Role role = new Role();
		role.setId(id);
		role.setStatus(status);
		boolean flag = roleService.update(role);
		if (flag) {
			return ResultVO.successResult("修改成功！");
		}
		return ResultVO.failureResult("修改失败！");
	}

	@GetMapping("/auth/{id}")
	public String forwardAuthIndex(@PathVariable Long id) throws Exception {
		request.setAttribute("id", id);
		return "role/role_auth";
	}

	@PostMapping("/auth/{id}")
	@ResponseBody
	public ResultVO authRole(@PathVariable Long id, String nodeIds) throws Exception {
		// 解析菜单主键，变为数组
		String[] ids = nodeIds.split("-");
		boolean flag = roleService.auth(id, ids);
		if (flag) {
			return ResultVO.successResult("授权成功");
		} else {
			return ResultVO.failureResult("授权失败");
		}
	}
}
