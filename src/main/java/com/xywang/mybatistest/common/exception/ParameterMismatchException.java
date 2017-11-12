package com.xywang.mybatistest.common.exception;

/**
 * @ClassName: ParameterMismatchException
 * @Description: 输入body参数和方法不匹配异常
 * @author xywang
 * @date 2017年9月26日 下午1:46:09
 * 
 */
public class ParameterMismatchException extends RuntimeException{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public ParameterMismatchException() {
		super();
	}

	public ParameterMismatchException(String message) {
		super(message);
	}

	public ParameterMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParameterMismatchException(Throwable cause) {
		super(cause);
	}

	protected ParameterMismatchException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
