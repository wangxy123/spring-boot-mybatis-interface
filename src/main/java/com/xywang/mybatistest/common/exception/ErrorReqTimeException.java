package com.xywang.mybatistest.common.exception;

/**
 * @ClassName: ErrorReqTimeException
 * @Description: 请求时间错误异常
 * @author xywang
 * @date 2017年9月26日 下午1:34:45
 * 
 */
public class ErrorReqTimeException extends RuntimeException {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ErrorReqTimeException() {
		super();
	}

	public ErrorReqTimeException(String message) {
		super(message);
	}

	public ErrorReqTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorReqTimeException(Throwable cause) {
		super(cause);
	}

	protected ErrorReqTimeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
