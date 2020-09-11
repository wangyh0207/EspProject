package cn.ekgc.esp.dao;

import cn.ekgc.esp.pojo.entity.PurchaseInfo;
import cn.ekgc.esp.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>角色模块数据持久层接口</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
@Repository
public interface PurchaseInfoDao {
	/**
	 * <b>根据查询对象查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<PurchaseInfo> findListByQuery(PurchaseInfo query) throws Exception;

	/**
	 * <b>保存对象信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer save(PurchaseInfo entity) throws Exception;

	/**
	 * <b>修改对象信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer update(PurchaseInfo entity) throws Exception;

	/**
	 * <b>删除信息</b>
	 * @param id
	 * @return
	 */
	Integer delete(Long id) throws Exception;
}
