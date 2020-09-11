package cn.ekgc.esp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>采购编号生成工具类</b>
 * @author wyh
 * @version 1.0.0 2020-08-27
 * @since 1.0.0
 */
public class NoGeneratorUtil {

	/**
	 * <b>形成系统唯一采购编号</b>
	 * <p>
	 *     整个编号采用前缀 + 时间生成
	 * </p>
	 * @return
	 */
	public static String generateNo(){
		// 时间格式 yyyyMMddHHmmssSSSS
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String no = "ES" + dateFormat.format(new Date());
		return no;
	}

	public static void main(String[] args) {
		System.out.println(generateNo());
	}
}
