package com.xywang.mybatistest.common.exception;

/**
 * @ClassName: TableNameNotFoundException
 * @Description: TableName不正确异常
 * @author xywang
 * @date 2017年9月26日 下午1:46:09
 * 
 */
public class TableNameNotFoundException extends RuntimeException {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public TableNameNotFoundException() {
		super();
	}

	public TableNameNotFoundException(String message) {
		super(message);
	}

	public TableNameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TableNameNotFoundException(Throwable cause) {
		super(cause);
	}

	protected TableNameNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
