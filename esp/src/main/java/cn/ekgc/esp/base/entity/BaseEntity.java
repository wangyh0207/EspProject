package cn.ekgc.esp.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>基础信息实体类</b>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer status;                     // 状态
	private Date createTime;                    // 创建时间
	private Date updateTime;                    // 修改时间

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
