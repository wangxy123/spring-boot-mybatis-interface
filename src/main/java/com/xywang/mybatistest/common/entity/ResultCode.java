package com.xywang.mybatistest.common.entity;

/**
 * @ClassName: ResultCode
 * @Description: 统一返回码及描述
 * @author xywang
 * @date 2017年9月19日 上午10:16:12
 * 
 */
public enum ResultCode {
	SUCCESS("000000", "成功"),
	FAIL("999000", "失败"), 
	ILLEGALPARA("100001", "参数格式错误"),
	TABLENAMENOTFOUND("100002", "tableName不存在"),
	METHODNAMENOTFOUND("100003","methodName不存在"),
	BODYNOTILLEGAL("100004", "输入参数和请求方法不匹配"),
	ERRORREQTIME("100005","请求时间错误"),
	UNAUTHORIZED("400001", "鉴权失败"), 
	NOT_FOUND("400004", "接口未实现"), 
	INTERNAL_SERVER_ERROR("999999", "服务器内部错误");

	public String returncode;
	public String returndesc;

	ResultCode(String code, String msg) {
		this.returncode = code;
		this.returndesc = msg;
	}
}
