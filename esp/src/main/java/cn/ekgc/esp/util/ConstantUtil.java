package cn.ekgc.esp.util;

import java.util.Properties;

/**
 * <b>系统常量信息工具类</b>
 * @author wyh
 * @version 1.0.0 2020-08-19
 * @since 1.0.0
 */
public class ConstantUtil {
	private static Properties props = new Properties();

	static {
		try {
			// 加载解析位于 src 下的配置文件 esp.properties
			props.load(ConstantUtil.class.getClassLoader().getResourceAsStream("esp.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>分页信息：当前页码</b>
	 */
	public static final Integer PAGE_NUM = Integer.parseInt(props.getProperty("page.num"));

	/**
	 * <b>分页信息：每页显示数量</b>
	 */
	public static final Integer PAGE_SIZE = Integer.parseInt(props.getProperty("page.size"));

	/**
	 * <b>系统状态：启用</b>
	 */
	public static final Integer SYSTEM_STATUS_ENABLE = Integer.parseInt(props.getProperty("system.status.enable"));

	/**
	 * <b>系统状态：禁用</b>
	 */
	public static final Integer SYSTEM_STATUS_DISABLE = Integer.parseInt(props.getProperty("system.status.disable"));

	/**
	 * <b>采购状态：已提交，待审核</b>
	 */
	public static final Integer PURCHASE_STATUS_APPLY = Integer.parseInt(props.getProperty("purchase.status.apply"));

	/**
	 * <b>采购状态：已审核、待购买</b>
	 */
	public static final Integer PURCHASE_STATUS_CHECKED = Integer.parseInt(props.getProperty("purchase.status.checked"));

	/**
	 * <b>采购状态：已购买、待入库</b>
	 */
	public static final Integer PURCHASE_STATUS_BUY = Integer.parseInt(props.getProperty("purchase.status.buy"));

	/**
	 * <b>采购状态：已入库、待领取</b>
	 */
	public static final Integer PURCHASE_STATUS_STORE = Integer.parseInt(props.getProperty("purchase.status.store"));

	/**
	 * <b>采购状态：已领取</b>
	 */
	public static final Integer PURCHASE_STATUS_DRAW = Integer.parseInt(props.getProperty("purchase.status.draw"));

	/**
	 * <b>采购状态：申请已撤回</b>
	 */
	public static final Integer PURCHASE_STATUS_CANCEL= Integer.parseInt(props.getProperty("purchase.status.cancel"));

	/**
	 * <b>采购状态：申请已撤回</b>
	 */
	public static final Integer PURCHASE_STATUS_BACK= Integer.parseInt(props.getProperty("purchase.status.back"));

	/**
	 * <b>秘钥</b>
	 */
	public static final String SECRET_KEY= props.getProperty("secret.key");
}
