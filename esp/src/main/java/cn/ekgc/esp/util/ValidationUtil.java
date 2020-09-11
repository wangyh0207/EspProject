package cn.ekgc.esp.util;

import cn.ekgc.esp.pojo.vo.Page;

/**
 * <b>信息验证工具类</b>
 * @author wyh
 * @version 1.0.0 2020-07-21
 * @since 1.0.0
 */
public class ValidationUtil {
	private static String idCardReg = "^[0-9]{17}[\\d|x|X]$";
	private static String cellphoneReg = "^1[3|5|6|7|8|9][0-9]{9}$";
	private static String emailReg = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	/**
	 * 对于多个信息进行非空校验
	 * @param args
	 * @return
	 */
	public static boolean checkNull(String ... args) {
		// 进行非空判断
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null || "".equals(args[i].trim())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 身份证格式验证
	 * @param idCard
	 * @return
	 */
	public static boolean checkIdCard(String idCard) {
		if (idCard != null && !"".equals(idCard.trim())) {
				return idCard.matches(idCardReg);
		}
		return false;
	}

	/**
	 * 通过身份证号码获取性别
	 * @param idCard
	 * @return
	 */
	public static Integer getGenderByIdCard(String idCard) {
		int gender = idCard.charAt(16) - '0';
		return gender % 2;
	}
	
	/**
	 * 手机号码格式验证
	 * @param cellphone
	 * @return
	 */
	public static boolean checkCellphone(String cellphone) {
		if (cellphone != null && !"".equals(cellphone.trim())) {
				return cellphone.matches(cellphoneReg);
		}
		return false;
	}

	/**
	 * <b>密码格式有效性验证</b>
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(String password) {
		if (password != null && password.trim().length() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * <b>判断字符串是否全为数字</b>
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <b>判断邮箱格式是否正确</b>
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		if (email != null && !"".equals(email.trim())) {
			return email.matches(emailReg);
		}
		return false;
	}
}
