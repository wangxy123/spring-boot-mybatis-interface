package com.xywang.mybatistest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xywang.mybatistest.common.entity.Result;
import com.xywang.mybatistest.service.DispatchService;

@RestController
@RequestMapping(value = "/openapi/v1")
public class SqlController {

	@Autowired
	DispatchService dispatchService;

	@ResponseBody
	@RequestMapping(value = "/default/{tableName}/{methodName}", method = RequestMethod.POST)
	public Result<?> doSql(@PathVariable("tableName") String tableName,
			@PathVariable("methodName") String methodName,
			@RequestBody String req) throws Exception {

		return new Result<>(dispatchService.doSql(tableName, methodName, req));

	}

	@ResponseBody
	@RequestMapping(value = "/example/{tableName}/{methodName}",method = RequestMethod.POST)
	public Result<?> doExampleSql(@PathVariable("tableName") String tableName,
								 @PathVariable("methodName") String methodName,
								 @RequestBody String req)throws Exception{
		return new Result<>(dispatchService.doExampleSql(tableName, methodName, req));
	}

}
