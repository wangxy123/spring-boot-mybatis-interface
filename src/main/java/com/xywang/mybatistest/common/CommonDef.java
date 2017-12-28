package com.xywang.mybatistest.common;

public class CommonDef {

	public class Mybatis {
		public static final String MAPPER_PACKAGE = "com.xywang.mybatistest.mapper";
		public static final String SERVICE_PACKAGE = "com.xywang.mybatistest.service.%sService";
		public static final String MODE_PACKAGE = "com.xywang.mybatistest.model.%s";
		public static final String CURRENCY_MAPPERS = "tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.MySqlMapper,tk.mybatis.mapper.common.ConditionMapper";
	}

	public class ParaKey {
		public static final String LIKE = "like";
		public static final String PARA = "para";
		public static final String BETWEEN = "between";
		public static final String ORDERBY = "orderBy";
		public static final String PAGING = "paging";
	}

	public class Para {
		public static final String KEY = "key";
		public static final String VALUE = "value";
		public static final String DESC = "desc";
		public static final String ASC = "asc";
	}
}
