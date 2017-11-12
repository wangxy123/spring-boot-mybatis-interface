package com.xywang.mybatistest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Service
public abstract class BaseService<T> {
	@Autowired
	protected Mapper<T> mapper;

	@Autowired
	protected MySqlMapper<T> mySqlMapper;

	public int insert(T entity) {
		return mapper.insert(entity);
	}

	public int insertList(List<T> paramList) {
		return mySqlMapper.insertList(paramList);
	}

	public List<T> select(T entity) {
		return mapper.select(entity);
	}

	public int deleteByPrimaryKey(T entity) {

		return mapper.deleteByPrimaryKey(entity);
	}

	public int insertSelective(T entity) {

		return mapper.insertSelective(entity);
	}

	public int delete(T entity) {
		return mapper.delete(entity);
	}
}
