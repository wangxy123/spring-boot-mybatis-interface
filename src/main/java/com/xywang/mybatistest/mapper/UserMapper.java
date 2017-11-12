package com.xywang.mybatistest.mapper;

import com.xywang.mybatistest.model.User;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends ConditionMapper<User>, MySqlMapper<User>, Mapper<User> {
}