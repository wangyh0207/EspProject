package cn.ekgc.esp.dao;

import cn.ekgc.esp.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>角色模块数据持久层接口</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
@Repository
public interface RoleDao {
	/**
	 * <b>根据查询对象查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Role> findListByQuery(Role query) throws Exception;

	/**
	 * <b>保存对象信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer save(Role entity) throws Exception;

	/**
	 * <b>修改对象信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer update(Role entity) throws Exception;

	/**
	 * <b>根据角色主键删除权限</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer deleteRoleMenuByRoleId(Long id) throws Exception;

	/**
	 * <b>保存角色权限</b>
	 * @param paramMap
	 * @return
	 */
	Integer saveRoleMenu(Map<String, Long> paramMap);
}
