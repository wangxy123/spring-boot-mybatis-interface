# 通用数据库操作接口

## 通用接口
1. **类型**：http+post
2.  **地址**：http://ip:port/openapi/v1/default/{tableName}/{methodNanme}
3. **说明**：``tablename``为数据库表名转大驼峰格式。
					``methodName``详见下面说明
4. **body**： 为方法参数的json格式，数据库字段名转小驼峰格式。
5. **header**：鉴权字段，暂无。
6.  **返回**：  
|编号|字段名|类型|描述|
|:-:|:-:|:-:|:-:|
|1|returncode|String|返回码|
|2|returndesc|String|返回描述|
|3|data|String|返回内容，方法返回对象的json格式|
7. **返回码**：  
|编号|returncode|returndesc|描述|
|:-:|:-:|:-:|:-:|
|1|000000|成功||
|2|999000|失败|正常失败|
|3|999999|服务器内部错误|服务器未知异常情况|
|4|100001|参数格式错误||
|5|100002|请求表名不存在|tableName不存在|
|6|100003|请求方法名不存在|methodName不存在|
|8|100005|请求时间错误|暂无，预留|
|9|400001|鉴权失败|暂无，预留|
|10|400004|接口未实现|暂无，预留|



## methodName列表：
### Select

**方法**：```List<T> select(T record);```  
**说明**：根据实体中的属性值进行查询，查询条件使用等号.  
**selectAll**：查询全部结果可以使用select(null)  
**分页**：如需分页，可在请求参数中增加：  
```{
"page":1,
"rows":10
}
```


**方法**：```int selectCount(T record);```  
**说明**：根据实体中的属性查询总数，查询条件使用等号  

**方法**：```T selectOne(T record);```  
**说明**：根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号  


### Insert

**方法**：```int insertList(List<T> recordList);```  
**说明**：批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等。  
**mysql使用**：*除主键，时间列外其他列需要不为null。*  

**方法**：```int insertSelective(T record);```  
**说明**：保存一个实体，null的属性不会保存，会使用数据库默认值


### Update


**方法**：```int updateByPrimaryKeySelective(T record);```  
**说明**：根据主键更新属性不为null的值，



### Delete

**方法**：```int delete(T record);```  
**说明**：根据实体属性作为条件进行删除，查询条件使用等号  
**注意：慎用，如果参数为null则会删除所有数据！！！**

## 特殊查询接口

1. **类型**：http+post  
2.  **地址**：http://ip:port/openapi/v1/default/{tableName}/{methodNanme}  
3. **说明**：  
``tablename``为数据库表名转大驼峰格式。  
					``methodName``详见下面说明  
					
4. **body**：  
|编号|字段名|类型|描述|  
|:-:|:-:|:-:|:-:|  
|1|数据库字段转小驼峰格式|String|要查询的字段值，sql语句里使用=拼接|  
|2|...|...|...|  
|3|between|Between|between条件参数|  
|4|orderBy|OrderBy|orderBy条件参数|  
|5|paging|Paging|分页条件参数|

5. **Between**：  
|编号|字段名|类型|描述|
|:-:|:-:|:-:|:-:|
|1|key|String|要查询字段转小驼峰格式|
|2|minValue|String|between条件左边|
|3|maxValue|String|between条件右边|
6. **OrderBy**：  
|编号|字段名|类型|描述|
|:-:|:-:|:-:|:-:|
|1|key|String|要查询字段转小驼峰格式|
|2|sortOrder|String|排序方式{desc/asc}|
7. **Paging**：  
|编号|字段名|类型|描述|
|:-:|:-:|:-:|:-:|
|1|page|String|页码|
|2|rows|String|每页行数|  
8. **header**：鉴权字段，暂无。  
9.  **返回**：同通用接口  
10. **返回码**：同通用接口  


## methodName列表：

**方法**：```List<T> selectByExample(Object o);```

**方法**：```int selectCountByExample(Object o)```
