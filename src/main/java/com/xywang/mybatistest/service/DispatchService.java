package com.xywang.mybatistest.service;

import java.lang.reflect.Method;
import java.util.Map;

import com.xywang.mybatistest.common.entity.Between;
import com.xywang.mybatistest.common.entity.OrderBy;
import com.xywang.mybatistest.common.entity.Paging;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xywang.mybatistest.common.CommonDef;
import com.xywang.mybatistest.common.util.SpringUtil;
import tk.mybatis.mapper.entity.Example;

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
		JSONObject entity = (JSONObject) JSON.parseObject(req, paraType);
		// 分页
		Integer page = entity.getInteger("page");
		Integer rows = entity.getInteger("rows");
		if (page != null && rows != null) {
			PageHelper.startPage(page, rows);
		}
		return method.invoke(SpringUtil.getBean(c), new Object[] { entity });
	}

	public Object doExampleSql(String tableName, String methodName, String req)throws Exception{
		// 获取service类型
		Class<?> c = Class.forName(String.format(
				CommonDef.Mybatis.SERVICE_PACKAGE, tableName));
		// 获取方法
		Method method = c.getMethod(methodName, Object.class);
		// 获取mode类型
		Class<?> m = Class.forName(String.format(CommonDef.Mybatis.MODE_PACKAGE, tableName));
		JSONObject entity =  JSON.parseObject(req,JSONObject.class);
		Between between = null;
		if(entity.containsKey(CommonDef.ParaKey.BETWEEN)){
			between = entity.getJSONObject(CommonDef.ParaKey.BETWEEN).toJavaObject(Between.class);
			entity.remove(CommonDef.ParaKey.BETWEEN);
		}
		OrderBy orderBy = null;
		if(entity.containsKey(CommonDef.ParaKey.ORDERBY)){
			orderBy = entity.getJSONObject(CommonDef.ParaKey.ORDERBY).toJavaObject(OrderBy.class);
			entity.remove(CommonDef.ParaKey.ORDERBY);
		}

		Paging paging = null;
		if(entity.containsKey(CommonDef.ParaKey.PAGING)){
			paging = entity.getJSONObject(CommonDef.ParaKey.PAGING).toJavaObject(Paging.class);
			entity.remove(CommonDef.ParaKey.PAGING);
		}

		Example example = new Example(m);
		Example.Criteria criteria = example.createCriteria();

		if(orderBy!=null&&orderBy.getKey()!=null&&orderBy.getSortOrder()!=null){
			if(CommonDef.Para.DESC.equals(orderBy.getSortOrder())){
				example.orderBy(orderBy.getKey()).desc();
			}
			else{
				example.orderBy(orderBy.getKey()).asc();
			}
		}

		for(Map.Entry<String,Object> prop : entity.entrySet()){
			if(StringUtils.isNotBlank(String.valueOf(prop.getValue()))){
				criteria.andEqualTo(prop.getKey(),prop.getValue());
			}
		}

		if(between!=null&&between.getKey()!=null&&between.getMaxValue()!=null&&between.getMinValue()!=null){
			criteria.andBetween(between.getKey(),between.getMinValue(),between.getMaxValue());
		}

		if(paging!=null&&paging.getPage()!=null&&paging.getRows()!=null){
			PageHelper.startPage(Integer.parseInt(paging.getPage(),Integer.parseInt(paging.getRows())));
		}

		return method.invoke(SpringUtil.getBean(c), new Object[] { entity });
	}
}
