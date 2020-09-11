package cn.ekgc.esp.pojo.vo;

import java.io.Serializable;

/**
 *<b>zTree 节点对象</b>
 * @author wyh
 * @version 1.0.0 2020-08-09
 * @since 1.0.0
 */
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pId;
	private String name;
	private boolean open;
	private boolean checked;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
