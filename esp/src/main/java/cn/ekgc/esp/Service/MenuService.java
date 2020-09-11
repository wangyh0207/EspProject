package cn.ekgc.esp.Service;

import cn.ekgc.esp.pojo.entity.Menu;
import cn.ekgc.esp.pojo.entity.Role;
import cn.ekgc.esp.pojo.vo.Node;

import java.util.List;

/**
 * <b>菜单模块业务层接口实现类</b>
 * @author wyh
 * @version 1.0.0 2020-08-26
 * @since 1.0.0
 */
public interface MenuService {

	/**
	 * <b>根据角色信息获取首页面功能菜单列表</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Menu> getMenuListForIndex(Role role) throws Exception;

	/**
	 * <b>根据角色主键，查询授权页面菜单列表</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Node> getListForAuth(Long id) throws Exception;
}
