# spring-boot-mybatis-interface
**********************************
## 一个通用的数据库访问接口
### 如何使用：
+ 修改 `config.properties` 中数据库配置
+ 运行 `mvn mybatis-generator:generate`
+ 会生成对应的 mapper.xml , model , Mapper
+ 需要手动添加相应的service层代码，例如：

@Service public class UserService extends BaseService<User>{
}

### [接口文档](https://github.com/wangxy123/spring-boot-mybatis-interface/blob/master/%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3.pdf)
