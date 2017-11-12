package com.xywang.mybatistest.common.aop;

import static com.xywang.mybatistest.common.util.WebLogUtil.getLog;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.xywang.mybatistest.common.entity.Operation;
import com.xywang.mybatistest.common.entity.Result;
import com.xywang.mybatistest.common.entity.ResultCode;
import com.xywang.mybatistest.common.entity.WebLog;
import com.xywang.mybatistest.common.exception.FailException;
import com.xywang.mybatistest.common.exception.ParaIllegalException;

/**
 * @ClassName: WebLogAspect
 * @Description: 所有外部请求统一日志记录
 * @author xywang
 * @date 2017年6月29日 下午4:26:35
 * 
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {

	private static final Logger LOGGINFO = LoggerFactory
			.getLogger("LOGGERINFO");

	@Pointcut("execution(public com.xywang.mybatistest.common.entity.Result *(..))")
	public void webLog() {
	}

	@Around("webLog()")
	public Object process(ProceedingJoinPoint point) throws Throwable {

		WebLog webLog = getLog();

		webLog.getOperates().add(new Operation("WebLogAspect.process.in"));
		Long start = System.currentTimeMillis();
		Result<?> returnValue = null;

		// 访问目标方法的参数：
		Object[] args = point.getArgs();
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attributes
				.getRequest();

		webLog.setUrl(request.getRequestURL().toString());
		webLog.setIp(getIpAddress(request));
		webLog.setHttp_method(request.getMethod());
		webLog.setClass_method(point.getSignature().getDeclaringTypeName()
				+ "." + point.getSignature().getName());
		if (args.length > 0)
			webLog.setWebargs(JSON.toJSONString(args[0]));
		else {
			webLog.setWebargs("空");
		}

		try {
			returnValue = (Result<?>) point.proceed(args);
			webLog.setRet(JSON.toJSONString(returnValue));
		} catch (Throwable e) {
			returnValue = handlerException(point, e);
		}

		webLog.setTime(System.currentTimeMillis() - start + "ms");
		LOGGINFO.info("【接口日志】 :" + JSON.toJSONString(webLog));
		return returnValue;
	}

	@SuppressWarnings("rawtypes")
	private Result<?> handlerException(ProceedingJoinPoint point, Throwable e) {
		Result<?> result = new Result();
		// 参数错误异常
		// TODO 统一异常处理，可自行扩展
		if (e instanceof ParaIllegalException) {
			// result.setReturndesc(e.getLocalizedMessage());
			result.setReturndesc(ResultCode.ILLEGALPARA.returndesc);
			result.setReturncode(ResultCode.ILLEGALPARA.returncode);
		} else if (e instanceof ClassNotFoundException) {
			result.setReturndesc(ResultCode.TABLENAMENOTFOUND.returndesc);
			result.setReturncode(ResultCode.TABLENAMENOTFOUND.returncode);
		} else if (e instanceof NoSuchMethodException) {
			result.setReturncode(ResultCode.METHODNAMENOTFOUND.returncode);
			result.setReturndesc(ResultCode.METHODNAMENOTFOUND.returndesc);
		} else if (e instanceof FailException) {
			result.setReturncode(ResultCode.FAIL.returncode);
			result.setReturndesc(ResultCode.FAIL.returndesc);
		} else if (e instanceof JSONException) {
			result.setReturncode(ResultCode.ILLEGALPARA.returncode);
			result.setReturndesc(ResultCode.ILLEGALPARA.returndesc);
		}
		else {
			LOGGINFO.error(point.getSignature() + " error ", e);
			result.setReturndesc(e.toString());
			// result.setReturndesc(ResultCode.INTERNAL_SERVER_ERROR.returndesc);
			result.setReturncode(ResultCode.INTERNAL_SERVER_ERROR.returncode);
		}
		getLog().setError(e.getMessage());
		return result;
	}

	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 如果是多级代理，那么取第一个ip为客户端ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(0, ip.indexOf(",")).trim();
		}
		return ip;
	}
}
