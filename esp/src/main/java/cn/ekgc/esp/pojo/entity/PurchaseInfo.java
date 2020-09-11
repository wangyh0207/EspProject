package cn.ekgc.esp.pojo.entity;

import cn.ekgc.esp.base.entity.BaseEntity;

import java.util.Date;


/**
 * <b>物资信息实体类</b>
 * @author wyh
 * @version 1.0.0 2020-08-19
 * @since 1.0.0
 */
public class PurchaseInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;                            // 主键
	private String no;                          // 采购编号
	private String applyName;                   // 申请物资名称
	private Double applyQuantity;               // 申请物资数量
	private String applyUnit;                   // 物资单位
	private String applyRemark;                 // 物资备注
	private User applyUser;                     // 申请人
	private Date applyTime;                     // 申请时间
	private String checkSuggestion;             // 审核意见
	private User checkUser;                     // 审核人
	private Date checkTime;                     // 审核时间
	private String goodsName;                   // 物资名称
	private Double goodsQuantity;               // 物资数量
	private String goodsUnit;                   // 物资单位
	private Double goodsPrePrice;               // 购买单价
	private Double goodsDiscount;               // 购买优惠
	private Double goodsTotalAmount;            // 购买总额
	private String goodsCompany;                // 生产厂家
	private String purchaseRemake;              // 采购备注
	private User purchaseUser;                  // 采购人
	private Date purchaseTime;                  // 采购时间
	private User storeUser;                     // 入库人
	private Date storeTime;                     // 入库时间
	private User drawUser;                      // 领取人
	private Date drawTime;                      // 领取时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public Double getApplyQuantity() {
		return applyQuantity;
	}

	public void setApplyQuantity(Double applyQuantity) {
		this.applyQuantity = applyQuantity;
	}

	public String getApplyUnit() {
		return applyUnit;
	}

	public void setApplyUnit(String applyUnit) {
		this.applyUnit = applyUnit;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public User getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(User applyUser) {
		this.applyUser = applyUser;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getCheckSuggestion() {
		return checkSuggestion;
	}

	public void setCheckSuggestion(String checkSuggestion) {
		this.checkSuggestion = checkSuggestion;
	}

	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(Double goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public Double getGoodsPrePrice() {
		return goodsPrePrice;
	}

	public void setGoodsPrePrice(Double goodsPrePrice) {
		this.goodsPrePrice = goodsPrePrice;
	}

	public Double getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(Double goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	public Double getGoodsTotalAmount() {
		return goodsTotalAmount;
	}

	public void setGoodsTotalAmount(Double goodsTotalAmount) {
		this.goodsTotalAmount = goodsTotalAmount;
	}

	public String getGoodsCompany() {
		return goodsCompany;
	}

	public void setGoodsCompany(String goodsCompany) {
		this.goodsCompany = goodsCompany;
	}

	public String getPurchaseRemake() {
		return purchaseRemake;
	}

	public void setPurchaseRemake(String purchaseRemake) {
		this.purchaseRemake = purchaseRemake;
	}

	public User getPurchaseUser() {
		return purchaseUser;
	}

	public void setPurchaseUser(User purchaseUser) {
		this.purchaseUser = purchaseUser;
	}

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public User getStoreUser() {
		return storeUser;
	}

	public void setStoreUser(User storeUser) {
		this.storeUser = storeUser;
	}

	public Date getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}

	public User getDrawUser() {
		return drawUser;
	}

	public void setDrawUser(User drawUser) {
		this.drawUser = drawUser;
	}

	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}
}
