package com.xywang.mybatistest.common.util;

public class ClassUtil {

	@SuppressWarnings("unchecked")
	public static <T> T as(Object o, Class<T> tClass) {
	     return tClass.isInstance(o) ? (T) o : null;
	}
}
