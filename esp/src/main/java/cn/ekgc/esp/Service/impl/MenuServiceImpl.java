package cn.ekgc.esp.Service.impl;

import cn.ekgc.esp.Service.MenuService;
import cn.ekgc.esp.dao.MenuDao;
import cn.ekgc.esp.pojo.entity.Menu;
import cn.ekgc.esp.pojo.entity.Role;
import cn.ekgc.esp.pojo.vo.Node;
import cn.ekgc.esp.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>菜单模块业务层接口实现类</b>
 * @author wyh
 * @version 1.0.0 2020-08-24
 * @since 1.0.0
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	/**
	 * <b>根据角色信息获取首页面功能菜单列表</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Menu> getMenuListForIndex(Role role) throws Exception {
		// 根据角色查询一级菜单列表
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("parentId", 0L);
		queryMap.put("roleId", role.getId());
		queryMap.put("status", ConstantUtil.SYSTEM_STATUS_ENABLE);
		List<Menu> menuList = menuDao.findMenuListByParentIdAndRoleId(queryMap);
		// 循环一级菜单列表，根据一级菜单主键和角色查询二级菜单列表
		for (Menu parent: menuList) {
			queryMap.put("parentId", parent.getId());
			List<Menu> childList = menuDao.findMenuListByParentIdAndRoleId(queryMap);
			parent.setChildList(childList);
		}
		return menuList;
	}

	/**
	 * <b>根据角色主键，查询授权页面菜单列表</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Node> getListForAuth(Long id) throws Exception {
		List<Node> nodeList = new ArrayList<Node>();
		// 查询获得所有处于启用状态的菜单列表
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("status", ConstantUtil.SYSTEM_STATUS_ENABLE);
		List<Menu> menuList = menuDao.findMenuListByParentIdAndRoleId(queryMap);
		// 根据角色主键查询该角色对应的菜单列表
		queryMap.put("roleId", id);
		List<Menu> roleMenuList = menuDao.findMenuListByParentIdAndRoleId(queryMap);
		// 结合菜单列表和角色菜单列表，转换为 node 集合
		for (Menu menu : menuList) {
			// 创建 Node 对象
			Node node = new Node();
			node.setId(menu.getId());
			node.setName(menu.getText());
			if (menu.getParent() != null && menu.getParent().getId() != 0) {
				node.setpId(menu.getParent().getId());
			} else {
				node.setpId(0L);
				node.setOpen(true);
			}
			// 判断此时的菜单是否在对应的现有的角色中
			for (Menu roleMenu: roleMenuList) {
				if (roleMenu.getId() == menu.getId()) {
					node.setChecked(true);
				}
			}
			nodeList.add(node);
		}
		return nodeList;
	}
}
