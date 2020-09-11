package cn.ekgc.esp.dao;

import cn.ekgc.esp.pojo.entity.Menu;
import cn.ekgc.esp.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>菜单模块数据持久层接口</b>
 * @author wyh
 * @version 1.0.0 2020-08-26
 * @since 1.0.0
 */
@Repository
public interface MenuDao {
	/**
	 * <b>根绝父级菜单主键和角色主键查询菜单列表</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	List<Menu> findMenuListByParentIdAndRoleId(Map<String, Object> queryMap) throws Exception;
}
