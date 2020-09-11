package cn.ekgc.esp.pojo.vo;

import java.io.Serializable;

/**
 * <b>系统处理结果视图信息</b>
 * <p>
 *     所有异步返回信息都需要返回本对象
 * </p>
 * @author wyh
 * @version 1.0.0 2020-08-20
 * @since 1.0.0
 */
public class ResultVO<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean result;             // 处理结果：true-处理成功，false-处理失败
	private E data;                     // 相应数据

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	/**
	 * <b>获得系统处理成功视图对象</b>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static ResultVO successResult(Object data) throws Exception {
		ResultVO resultVO = new ResultVO();
		resultVO.setResult(true);
		resultVO.setData(data);
		return resultVO;
	}

	/**
	 * <b>获得系统处理成功视图对象</b>
	 * @return
	 * @throws Exception
	 */
	public static ResultVO successResult() throws Exception {
		ResultVO resultVO = new ResultVO();
		resultVO.setResult(true);
		return resultVO;
	}

	/**
	 * <b>获得系统处理失败视图对象</b>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static ResultVO failureResult(Object data) throws Exception {
		ResultVO resultVO = new ResultVO();
		resultVO.setResult(false);
		resultVO.setData(data);
		return resultVO;
	}
}
