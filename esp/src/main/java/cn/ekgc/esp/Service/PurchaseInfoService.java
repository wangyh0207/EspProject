package cn.ekgc.esp.Service;

import cn.ekgc.esp.pojo.entity.PurchaseInfo;
import cn.ekgc.esp.pojo.vo.Page;

/**
 * <b>采购信息模块业务层接口</b>
 * @author wyh
 * @version 1.0.0 2020-08-28
 * @since 1.0.0
 */
public interface PurchaseInfoService {
	/**
	 * <b>根据查询条件分页查询</b>
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Page<PurchaseInfo> getPageByQuery(Page<PurchaseInfo> page, PurchaseInfo query) throws Exception;

	/**
	 * <b>新增采购信息</b>
	 * @param purchaseInfo
	 * @return
	 * @throws Exception
	 */
	boolean save(PurchaseInfo purchaseInfo) throws Exception;

	/**
	 * <b>更改采购信息</b>
	 * @param purchaseInfo
	 * @return
	 * @throws Exception
	 */
	boolean update(PurchaseInfo purchaseInfo) throws Exception;

	/**
	 * <b>根据主键查找信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PurchaseInfo getPurchaseInfoById(Long id) throws Exception;

	/**
	 * <b>删除信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(Long id) throws Exception;
}
