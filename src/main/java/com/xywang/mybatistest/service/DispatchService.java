package com.xywang.mybatistest.service;

import java.lang.reflect.Method;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.xywang.mybatistest.common.entity.Between;
import com.xywang.mybatistest.common.entity.OrderBy;
import com.xywang.mybatistest.common.entity.Paging;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xywang.mybatistest.common.CommonDef;
import com.xywang.mybatistest.common.util.SpringUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 1
 */
@Service
public class DispatchService {

	private static final String LIST_METHOD="insertList";
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object doSql(String tableName, String methodName, String req)
			throws Exception {
		boolean isList = LIST_METHOD.equals(methodName);
		// 获取service类型
		Class<?> c = Class.forName(String.format(
				CommonDef.Mybatis.SERVICE_PACKAGE, tableName));
		// 获取方法
		Method method = null;
		if(isList){
			method = c.getMethod(methodName, List.class);
		}else{
			method = c.getMethod(methodName, Object.class);
		}
		//获取mode类型
		Class<?> m = Class.forName(String.format(CommonDef.Mybatis.MODE_PACKAGE,tableName));

		JSONObject entity = JSON.parseObject(req);

		Paging paging = null;
		if(entity.containsKey(CommonDef.ParaKey.PAGING)){
			paging = entity.getJSONObject(CommonDef.ParaKey.PAGING).toJavaObject(Paging.class);
			entity.remove(CommonDef.ParaKey.PAGING);
		}

		Object para = null;
		if(entity.containsKey(CommonDef.ParaKey.PARA) && entity.getJSONObject(CommonDef.ParaKey.PARA)!=null){
			if(isList){
				para = JSON.parseArray(entity.getString(CommonDef.ParaKey.PARA),m);
			}else{
				para = entity.getJSONObject(CommonDef.ParaKey.PARA).toJavaObject(m);
			}
			entity.remove(CommonDef.ParaKey.PARA);
		}

		// 分页
		if (paging != null && paging.getPage() != null && paging.getRows()!=null) {
			PageHelper.startPage(Integer.parseInt(paging.getPage()), Integer.parseInt(paging.getRows()));
		}
		return method.invoke(SpringUtil.getBean(c), new Object[] { para });
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
		Object para = null;
		if(entity.containsKey(CommonDef.ParaKey.PARA)&&entity.getJSONObject(CommonDef.ParaKey.PARA)!=null){
			para = entity.getJSONObject(CommonDef.ParaKey.PARA).toJavaObject(m);
			entity.remove(CommonDef.ParaKey.PARA);
		}
		criteria.andEqualTo(para);
		JSONArray likes = null;
		if(entity.containsKey(CommonDef.ParaKey.LIKE)){
			likes = entity.getJSONArray(CommonDef.ParaKey.LIKE);
			entity.remove(CommonDef.ParaKey.LIKE);
		}
		JSONObject like = null;
		if(likes!=null&&likes.size()>0){
			for(int i=0;i<likes.size();i++){
				like = likes.getJSONObject(i);
				if(like!=null&&like.containsKey(CommonDef.Para.KEY)&&like.containsKey(CommonDef.Para.VALUE)){
					criteria.andLike(like.getString(CommonDef.Para.KEY),like.getString(CommonDef.Para.VALUE));
				}
			}
		}

		if(between!=null&&between.getKey()!=null&&between.getMaxValue()!=null&&between.getMinValue()!=null){
			criteria.andBetween(between.getKey(),between.getMinValue(),between.getMaxValue());
		}

		if(paging!=null&&paging.getPage()!=null&&paging.getRows()!=null){
			PageHelper.startPage(Integer.parseInt(paging.getPage(),Integer.parseInt(paging.getRows())));
		}

		return method.invoke(SpringUtil.getBean(c), new Object[] { example });
	}
}
