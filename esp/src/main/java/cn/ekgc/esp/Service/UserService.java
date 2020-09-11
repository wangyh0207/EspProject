package cn.ekgc.esp.Service;

import cn.ekgc.esp.pojo.entity.User;
import cn.ekgc.esp.pojo.vo.Page;

/**
 * <b>用户模块业务层接口</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
public interface UserService {
	/**
	 * <b>使用手机号码查询用户信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	User getUserByCellphone(String cellphone) throws Exception;

	/**
	 * <b>保存信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean save(User entity) throws Exception;

	/**
	 * <b>使用 id 查询信息</b>
	 * @param id
	 * @return
	 */
	User getUserById(Long id) throws Exception;

	/**
	 * <b>修改信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean update(User entity) throws Exception;

	/**
	 * <b>分页查询</b>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page<User> getPage(Page<User> page) throws Exception;

	User getUserByIdCard(String idCard) throws Exception;

	User getUserByQuery(User user) throws Exception;
}
