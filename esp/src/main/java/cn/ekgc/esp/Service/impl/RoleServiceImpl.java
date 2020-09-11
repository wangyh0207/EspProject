package cn.ekgc.esp.Service.impl;

import cn.ekgc.esp.Service.RoleService;
import cn.ekgc.esp.dao.RoleDao;
import cn.ekgc.esp.pojo.entity.Role;
import cn.ekgc.esp.pojo.vo.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>角色模块业务层接口实现类</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao dao;

	/**
	 * <b>分页查询信息列表</b>
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<Role> getPage(Page<Role> page, Role query) throws Exception {
		// 设定 PageHelper 分页过滤器
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Role> list = dao.findListByQuery(query);
		// 获得分页信息
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		// 切换数据
		page.parseFromPageInfo(pageInfo);
		return page;
	}

	/**
	 * <b>按照 id 查询信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Role getById(Long id) throws Exception {
		// 封装查询对象
		Role query = new Role();
		query.setId(id);
		List<Role> list = dao.findListByQuery(query);
		if (list != null && list.size() > 0) {
			return list.get(0);
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
	public boolean save(Role entity) throws Exception {
		Integer count = dao.save(entity);
		if (count != null && count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>修改信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean update(Role entity) throws Exception {
		Integer count = dao.update(entity);
		if (count != null && count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Role> getList() throws Exception {
		return dao.findListByQuery(null);
	}

	/**
	 * <b>角色授权</b>
	 * @param id
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean auth(Long id, String[] ids) throws Exception {
		// 通过主键删除原来的权限
		int count = dao.deleteRoleMenuByRoleId(id);
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("roleId", id);
			// 循环遍历
		for (String idStr : ids) {
			Long menuId = Long.parseLong(idStr);
			paramMap.put("menuId", menuId);
			dao.saveRoleMenu(paramMap);
		}
		return true;
	}
}
