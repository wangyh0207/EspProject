package cn.ekgc.esp.controller;

import cn.ekgc.esp.Service.PurchaseInfoService;
import cn.ekgc.esp.Service.UserService;
import cn.ekgc.esp.base.controller.BaseController;
import cn.ekgc.esp.pojo.entity.PurchaseInfo;
import cn.ekgc.esp.pojo.entity.User;
import cn.ekgc.esp.pojo.vo.Page;
import cn.ekgc.esp.pojo.vo.ResultVO;
import cn.ekgc.esp.util.ConstantUtil;
import cn.ekgc.esp.util.NoGeneratorUtil;
import cn.ekgc.esp.util.ValidationUtil;
import com.alibaba.druid.sql.visitor.functions.Trim;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <b>采购信息模块控制层</b>
 * @author wyh
 * @version 1.0.0 2020-08-28
 * @since 1.0.0
 */
@Controller("purchaseInfoController")
@RequestMapping("/purchase")
public class PurchaseInfoController extends BaseController {
	@Autowired
	private PurchaseInfoService purchaseInfoService;
	@Autowired
	private UserService userService;
	/**
	 * <b>根据不同功能编号转发到相应页面</b>
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/index/{code}/{id}")
	public String forwardToPage(@PathVariable Integer code, @PathVariable Long id) throws Exception {
		String path = "";
		switch (code) {
			case 1:
				// 采购申请
				path = "purchase/purchase_apply_index";
				request.setAttribute("id", id);
				break;
			case 2:
				// 采购审核
				path = "purchase/purchase_check_index";
				request.setAttribute("id", id);
				break;
			case 3:
				// 物资采购
				path = "purchase/purchase_buy_index";
				request.setAttribute("id", id);
				break;
			case 4:
				// 物资入库
				path = "purchase/purchase_store_index";
				request.setAttribute("id", id);
				break;
			case 5:
				// 物资领取
				path = "purchase/purchase_draw_index";
				request.setAttribute("id", id);
				break;
			case 6:
				// 历史记录
				path = "purchase/purchase_history_index";
				break;
		}
		return path;
	}

	/**
	 * <b>根据状态进行分页查询信息</b>
	 * @param pageNum
	 * @param pageSize
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/page")
	@ResponseBody
	public Page<PurchaseInfo> getPage(Integer pageNum, Integer pageSize, Integer draw, Integer code) throws Exception {
		// 封装 Page 对象
		Page<PurchaseInfo> page = new Page<PurchaseInfo>(pageNum, pageSize, draw);
		PurchaseInfo query = new PurchaseInfo();
		switch (code) {
			case 1:
				// 采购申请
				query.setStatus(ConstantUtil.PURCHASE_STATUS_APPLY);
				break;
			case 2:
				// 采购审核
				query.setStatus(ConstantUtil.PURCHASE_STATUS_APPLY);
				break;
			case 3:
				// 物资采购
				query.setStatus(ConstantUtil.PURCHASE_STATUS_CHECKED);
				break;
			case 4:
				// 物资入库
				query.setStatus(ConstantUtil.PURCHASE_STATUS_BUY);
				break;
			case 5:
				// 物资领取
				query.setStatus(ConstantUtil.PURCHASE_STATUS_STORE);
				break;
			case 6:
				// 历史记录
				break;
		}
		// 进行查询
		page = purchaseInfoService.getPageByQuery(page, query);
		return page;
	}

	/**
	 * <b>转发到采购申请页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/save/{id}")
	public String forwardSaveIndex(@PathVariable Long id) throws Exception {
		request.setAttribute("id", id);
		return "purchase/save";
	}

	@PostMapping("/save")
	@ResponseBody
	public ResultVO<PurchaseInfo> savePurchase(PurchaseInfo purchaseInfo, String num, Long id) throws Exception {
		if (purchaseInfo.getApplyName() != null && !"".equals(purchaseInfo.getApplyName().trim())
				&& num != null && !"".equals(num.trim())) {
			if (ValidationUtil.isNumeric(num)) {
				User user = userService.getUserById(id);
				purchaseInfo.setApplyUser(user);
				purchaseInfo.setNo(NoGeneratorUtil.generateNo());
				purchaseInfo.setApplyTime(new Date());
				Double applyQuantity = Double.parseDouble(num);
				purchaseInfo.setApplyQuantity(applyQuantity);
				boolean flag = purchaseInfoService.save(purchaseInfo);
				if (flag) {
					return ResultVO.successResult(user.getId());
				}
			}
			return ResultVO.failureResult("请填写合法信息！");
		}
		return ResultVO.failureResult("申请失败，请检查必填信息!");
	}

	/**
	 * <b>审核</b>
	 * @param purchaseInfo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/update")
	@ResponseBody
	public ResultVO<PurchaseInfo> updateStatus(PurchaseInfo purchaseInfo, Long userId) throws Exception {
		User user = userService.getUserById(userId);
		purchaseInfo.setCheckUser(user);
		purchaseInfo.setCheckTime(new Date());
		boolean flag = purchaseInfoService.update(purchaseInfo);
		if (flag) {
			return ResultVO.successResult("审核成功！");
		}
		return ResultVO.failureResult("审核失败!");
	}

	@GetMapping("/buy/{id}")
	public String forwordBuy(@PathVariable Long id) throws Exception {
		request.setAttribute("purId", id);
		return "purchase/buy";
	}

	/**
	 * <b>购买信息插入</b>
	 * @param purchaseInfo
	 * @param num
	 * @param pre
	 * @param dis
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/buy")
	@ResponseBody
	public ResultVO<PurchaseInfo> buyPurchase(PurchaseInfo purchaseInfo, String num, String pre, String dis)throws Exception {
		if (purchaseInfo != null && num != null && !"".equals(num.trim()) && pre != null & !"".equals(pre.trim())
			&& dis != null && !"".equals(dis.trim())) {
			if (ValidationUtil.isNumeric(num) && ValidationUtil.isNumeric(pre)
					&& ValidationUtil.isNumeric(dis)) {
				Double goodsQuantity = Double.parseDouble(num);
				Double goodsPrePrice = Double.parseDouble(pre);
				Double goodsDiscount = Double.parseDouble(dis);
				Double goodsTotalAmount = goodsPrePrice * goodsQuantity - goodsDiscount;
				purchaseInfo.setGoodsQuantity(goodsQuantity);
				purchaseInfo.setGoodsPrePrice(goodsPrePrice);
				purchaseInfo.setGoodsDiscount(goodsDiscount);
				purchaseInfo.setGoodsTotalAmount(goodsTotalAmount);
				boolean flag = purchaseInfoService.update(purchaseInfo);
				if (flag) {
					return ResultVO.successResult("信息填写成功！");
				}
			}
			return ResultVO.failureResult("请填写合法信息！");
		}
		return ResultVO.failureResult("添加失败，请检查必填信息!");
	}

	/**
	 * <b>购买人及时间插入</b>
	 * @param purchaseInfo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/buyStatus")
	@ResponseBody
	public ResultVO<PurchaseInfo> changeBuy(PurchaseInfo purchaseInfo, Long userId)throws Exception {
		User user = userService.getUserById(userId);
		purchaseInfo.setPurchaseUser(user);
		purchaseInfo.setPurchaseTime(new Date());
		boolean flag = purchaseInfoService.update(purchaseInfo);
		if (flag) {
			return ResultVO.successResult("购买成功！");
		}
		return ResultVO.failureResult("购买失败!");
	}

	@PostMapping("/store")
	@ResponseBody
	public ResultVO<PurchaseInfo> storeApply(Long id, Integer status, Long userId) throws Exception {
		PurchaseInfo purchaseInfo = new PurchaseInfo();
		User user = userService.getUserById(userId);
		purchaseInfo.setId(id);
		purchaseInfo.setStatus(status);
		purchaseInfo.setStoreUser(user);
		purchaseInfo.setStoreTime(new Date());
		boolean flag = purchaseInfoService.update(purchaseInfo);
		if (flag) {
			return ResultVO.successResult("入库成功！");
		}
		return ResultVO.failureResult("入库失败!");
	}

	@PostMapping("/draw")
	@ResponseBody
	public ResultVO<PurchaseInfo> drawApply(Long id, Integer status, Long userId) throws Exception {
		PurchaseInfo purchaseInfo = new PurchaseInfo();
		User user = userService.getUserById(userId);
		purchaseInfo.setId(id);
		purchaseInfo.setStatus(status);
		purchaseInfo.setDrawUser(user);
		purchaseInfo.setDrawTime(new Date());
		boolean flag = purchaseInfoService.update(purchaseInfo);
		if (flag) {
			return ResultVO.successResult("领取成功！");
		}
		return ResultVO.failureResult("领取失败!");
	}

	@GetMapping("/detail/{id}")
	public String forwardDetail(@PathVariable Long id, ModelMap map) throws Exception {
		PurchaseInfo purchaseInfo = purchaseInfoService.getPurchaseInfoById(id);
		map.put("detail",purchaseInfo);
		return "purchase/detail";
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResultVO<PurchaseInfo> deleteApply(Long id) throws Exception {
		PurchaseInfo purchaseInfo = purchaseInfoService.getPurchaseInfoById(id);
		if (purchaseInfo.getStatus() == 1) {
			boolean flag = purchaseInfoService.delete(id);
			if (flag) {
				return ResultVO.successResult("撤回成功！");
			}
		}
		return ResultVO.failureResult("撤回失败！");
	}
}
