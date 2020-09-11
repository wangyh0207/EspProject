package cn.ekgc.esp.Service;

import cn.ekgc.esp.pojo.entity.Role;
import cn.ekgc.esp.pojo.vo.Page;

import java.util.List;

/**
 * <b>角色模块业务层接口</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
public interface RoleService {
	/**
	 * <b>分页查询信息列表</b>
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Page<Role> getPage(Page<Role> page, Role query) throws Exception;

	/**
	 * <b>按照 id 查询信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role getById(Long id) throws Exception;

	/**
	 * <b>保存信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean save(Role entity) throws Exception;

	/**
	 * <b>修改信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean update(Role entity) throws Exception;

	List<Role> getList() throws Exception;

	/**
	 * <b>角色授权</b>
	 * @param id
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	boolean auth(Long id, String[] ids) throws Exception;
}
