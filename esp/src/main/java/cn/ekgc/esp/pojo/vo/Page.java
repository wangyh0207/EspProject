package cn.ekgc.esp.pojo.vo;

import cn.ekgc.esp.util.ConstantUtil;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * <b>分页信息视图</b>
 * @author wyh
 * @version 1.0.0 2020-08-19
 * @since 1.0.0
 */
public class Page<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pageNum;                    // 当前页数
	private Integer pageSize;                   // 每页显示数量
	private Integer draw;
	private List<E> list;                       // 分页集合
	private Long totalCount;                    // 总数量
	private Long totalPage;                     // 总页数

	public Page() {}

	public Page(Integer pageNum, Integer pageSize, Integer draw) {
		if (pageNum != null && pageNum > 0) {
			this.pageNum = pageNum;
		} else {
			this.pageNum = ConstantUtil.PAGE_NUM;
		}

		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = ConstantUtil.PAGE_SIZE;
		}

		this.draw = draw;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public void parseFromPageInfo(PageInfo<E> pageInfo) {
		this.list = pageInfo.getList();
		this.totalCount = pageInfo.getTotal();
		this.totalPage = (long) pageInfo.getPages();
	}
}
