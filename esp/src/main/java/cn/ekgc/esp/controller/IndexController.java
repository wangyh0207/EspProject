package cn.ekgc.esp.controller;

import cn.ekgc.esp.Service.MenuService;
import cn.ekgc.esp.Service.UserService;
import cn.ekgc.esp.base.controller.BaseController;
import cn.ekgc.esp.pojo.entity.Menu;
import cn.ekgc.esp.pojo.entity.Role;
import cn.ekgc.esp.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <b>首页面模块控制层</b>
 * @author wyh
 * @version 1.0.0 2020-08-24
 * @since 1.0.0
 */
@Controller("indexController")
public class IndexController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * <b>加载首页面</b>
	 * @param token
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/index/{token}")
	public String index(@PathVariable("token") String token, ModelMap map) throws Exception {
		// 通过用户登录所给定的 Token，在 Redis 中获取信息
		String idStr = redisTemplate.opsForValue().get(token);
		if (idStr != null) {
			// 用户已经登陆，提交了合法的 token
			// 根据当前登录用户主键查询登录用户信息
			User user = userService.getUserById(Long.parseLong(idStr));
			// 根据登录用户信息，查询对应角色功能
			Role role = user.getRole();
			List<Menu> menuList = menuService.getMenuListForIndex(role);
			// 将功能列表转发到页面
			map.put("menuList", menuList);
			map.put("login", user);
			return "index";
		}
			return "redirect:/user/login";
	}

	@GetMapping("/signOut")
	@ResponseBody
	public boolean signOut(String token) throws Exception {
		redisTemplate.delete(token);
		return true;
	}
}
