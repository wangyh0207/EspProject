package cn.ekgc.esp.Service.impl;

import cn.ekgc.esp.Service.PurchaseInfoService;
import cn.ekgc.esp.dao.PurchaseInfoDao;
import cn.ekgc.esp.pojo.entity.PurchaseInfo;
import cn.ekgc.esp.pojo.vo.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <b>采购信息模块业务层接口实现类</b>
 * @author wyh
 * @version 1.0.0 2020-08-28
 * @since 1.0.0
 */
@Service("purchaseInfoService")
@Transactional
public class PurchaseInfoServiceImpl implements PurchaseInfoService {
	@Autowired
	private PurchaseInfoDao purchaseInfoDao;
	/**
	 * <b>根据查询条件分页查询</b>
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<PurchaseInfo> getPageByQuery(Page<PurchaseInfo> page, PurchaseInfo query) throws Exception {
		// 创建 PageHelper 分页对象
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		// 查询集合
		List<PurchaseInfo> purchaseInfoList = purchaseInfoDao.findListByQuery(query);
		// 转换信息
		PageInfo<PurchaseInfo> pageInfo = new PageInfo<PurchaseInfo>(purchaseInfoList);
		page.parseFromPageInfo(pageInfo);
		return page;
	}

	/**
	 * <b>新增采购信息</b>
	 * @param purchaseInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean save(PurchaseInfo purchaseInfo) throws Exception {
		int count = purchaseInfoDao.save(purchaseInfo);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>更改采购信息</b>
	 * @param purchaseInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean update(PurchaseInfo purchaseInfo) throws Exception {
		int count = purchaseInfoDao.update(purchaseInfo);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>根据主键查找信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public PurchaseInfo getPurchaseInfoById(Long id) throws Exception {
		PurchaseInfo purchaseInfo = new PurchaseInfo();
		purchaseInfo.setId(id);
		List<PurchaseInfo> list = purchaseInfoDao.findListByQuery(purchaseInfo);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * <b>删除信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean delete(Long id) throws Exception {
		int count = purchaseInfoDao.delete(id);
		if (count > 0) {
			return true;
		}
		return false;
	}
}
