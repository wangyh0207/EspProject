package cn.ekgc.esp.Service.impl;

import cn.ekgc.esp.Service.UserService;
import cn.ekgc.esp.dao.UserDao;
import cn.ekgc.esp.pojo.entity.User;
import cn.ekgc.esp.pojo.vo.Page;
import cn.ekgc.esp.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <b>用户模块业务层接口实现类</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;

	/**
	 * <b>使用手机号码查询用户信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	@Override
	public User getUserByCellphone(String cellphone) throws Exception {
		User query = new User();
		query.setCellphone(cellphone);
		// 使用数据持久层进行查询
		List<User> userList = dao.findListByQuery(query);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	/**
	 * <b>保存信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean save(User entity) throws Exception {
		String password = entity.getPassword();
		if (password != null && !"".equals(password.trim())) {
			password = MD5Util.encrypt(password);
			entity.setPassword(password);
			int count = dao.save(entity);
			if (count > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <b>使用 id 查询信息</b>
	 * @param id
	 * @return
	 */
	@Override
	public User getUserById(Long id) throws Exception {
		User query = new User();
		query.setId(id);
		// 使用数据持久层进行查询
		List<User> userList = dao.findListByQuery(query);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	/**
	 * <b>修改信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean update(User entity) throws Exception {
		if (entity.getPassword() != null && !"".equals(entity.getPassword())) {
			// 此时是修改密码
			if (entity.getPassword().length() > 4) {
				String password = entity.getPassword();
				password = MD5Util.encrypt(password);
				entity.setPassword(password);
				int count = dao.update(entity);
				if (count > 0) {
					return true;
				}
			}
			return false;
		}
		int count = dao.update(entity);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>分页查询</b>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<User> getPage(Page<User> page) throws Exception {
		// 使用 PageHlper 进行分页查询
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<User> list = dao.findListByQuery(null);
		// 获得 PageHelper 分页信息
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		page.parseFromPageInfo(pageInfo);
		return page;
	}

	@Override
	public User getUserByIdCard(String idCard) throws Exception {
		User query = new User();
		query.setIdCard(idCard);
		// 使用数据持久层进行查询
		List<User> userList = dao.findListByQuery(query);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public User getUserByQuery(User user) throws Exception {
		List<User> userList = dao.findListByQuery(user);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
}
