package cn.ekgc.esp.controller;

import cn.ekgc.esp.Service.MenuService;
import cn.ekgc.esp.base.controller.BaseController;
import cn.ekgc.esp.pojo.vo.Node;
import cn.ekgc.esp.pojo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <b>菜单模块控制层</b>
 * @author wyh
 * @version 1.0.0 2020-08-31
 * @since 1.0.0
 */
@Controller("menuController")
@RequestMapping("/menu")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menuService;

	/**
	 * <b>获得授权菜单页面</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/auth/{id}")
	@ResponseBody
	public ResultVO queryMenuForAuth(@PathVariable Long id)throws Exception {
		// 根据角色主键为授权查询菜单列表
		List<Node> nodeList = menuService.getListForAuth(id);
		return ResultVO.successResult(nodeList);
	}
}
