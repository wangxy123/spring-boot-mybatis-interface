package com.xywang.mybatistest.common.exception;

/**
 * @ClassName: FailException
 * @Description: 失败时异常
 * @author xywang
 * @date 2017年9月26日 下午1:46:09
 * 
 */
public class FailException extends RuntimeException {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public FailException() {
		super();
	}

	public FailException(String message) {
		super(message);
	}

	public FailException(String message, Throwable cause) {
		super(message, cause);
	}

	public FailException(Throwable cause) {
		super(cause);
	}

	protected FailException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
