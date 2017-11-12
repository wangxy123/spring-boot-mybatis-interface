package com.xywang.mybatistest.common.exception;

/**
 * @ClassName: UnAuthorizedException
 * @Description: 鉴权失败异常
 * @author xywang
 * @date 2017年9月26日 下午1:36:22
 * 
 */
public class UnAuthorizedException extends RuntimeException {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthorizedException() {
		super();
	}

	public UnAuthorizedException(String message) {
		super(message);
	}

	public UnAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnAuthorizedException(Throwable cause) {
		super(cause);
	}

	protected UnAuthorizedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
