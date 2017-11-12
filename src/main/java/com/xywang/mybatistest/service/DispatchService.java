package com.xywang.mybatistest.service;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xywang.mybatistest.common.CommonDef;
import com.xywang.mybatistest.common.util.SpringUtil;

@Service
public class DispatchService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object doSql(String tableName, String methodName, String req)
			throws Exception {

		// 获取service类型

		Class<?> c = Class.forName(String.format(
				CommonDef.Mybatis.SERVICE_PACKAGE, tableName));
		// 获取方法
		Method method = c.getMethod(methodName, Object.class);
		// 获取参数类型
		Class[] paramTypes = method.getParameterTypes();
		Class paraType = paramTypes[0];
		// JSONObject entity = paraType.newInstance();
		JSONObject entity = (JSONObject) JSON.parseObject(req, paraType);
		// 分页
		Integer page = entity.getInteger("page");
		Integer rows = entity.getInteger("rows");
		if (page != null && rows != null) {
			PageHelper.startPage(page, rows);
		}
		return method.invoke(SpringUtil.getBean(c), new Object[] { entity });
	}
}
