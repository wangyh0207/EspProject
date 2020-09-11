package cn.ekgc.esp.dao;

import cn.ekgc.esp.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>用户模块数据持久层接口</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
@Repository
public interface UserDao {
	/**
	 * <b>根据查询对象查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<User> findListByQuery(User query) throws Exception;

	/**
	 * <b>保存对象信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer save(User entity) throws Exception;

	/**
	 * <b>修改对象信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer update(User entity) throws Exception;
}
