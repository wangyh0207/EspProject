package cn.ekgc.esp.pojo.entity;

import cn.ekgc.esp.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>用户信息实体类</b>
 * @author wyh
 * @version 1.0.0 2020-08-19
 * @since 1.0.0
 */
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;                        // 主键
	private String name;                    // 用户姓名
	private String cellphone;               // 联系电话
	private String password;                // 登录密码，MD5加密
	private Role role;                      // 用户角色
	private String idCard;                  // 身份证号码
	private String avatar;                  // 邮箱

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
