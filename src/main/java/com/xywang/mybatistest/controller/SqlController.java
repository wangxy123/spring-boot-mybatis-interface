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
	@RequestMapping(value = "/{tablename}/{methodNanme}", method = RequestMethod.POST)
	public Result<?> doSql(@PathVariable("tablename") String tableName,
			@PathVariable("methodNanme") String methodNanme,
			@RequestBody String req) throws Exception {

		// List<SystemPara> res = systemParaService.select(entity);
		// userService.save(user);
		return new Result<>(dispatchService.doSql(tableName, methodNanme, req));

	}
}
