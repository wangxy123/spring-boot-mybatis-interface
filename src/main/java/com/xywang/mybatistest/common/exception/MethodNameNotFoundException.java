package com.xywang.mybatistest.common.exception;

/**
 * @ClassName: MethodNameNotFoundException
 * @Description: 方法不存在异常
 * @author xywang
 * @date 2017年9月26日 下午1:46:09
 * 
 */
public class MethodNameNotFoundException extends RuntimeException{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public MethodNameNotFoundException() {
		super();
	}

	public MethodNameNotFoundException(String message) {
		super(message);
	}

	public MethodNameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodNameNotFoundException(Throwable cause) {
		super(cause);
	}

	protected MethodNameNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
