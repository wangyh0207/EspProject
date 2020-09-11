package cn.ekgc.esp.pojo.entity;

import cn.ekgc.esp.base.entity.BaseEntity;

import java.util.List;

/**
 * <b>角色信息实体类</b>
 * @author wyh
 * @version 1.0.0 2020-08-19
 * @since 1.0.0
 */
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;                            // 主键
	private String name;                        // 姓名
	private List<Menu> menuList;                // 该角色所拥有的菜单功能

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

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
}
