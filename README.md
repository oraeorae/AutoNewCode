# AutoNewCode
SpringBoot集成FreeMarker实现自动生成增删改查代码

(2022.9.10)
# 1.0版本
版本特点：当前只能单元测试类在生成，交互性还不足

## 项目结构
```
├─autoNewCode
│  ├─pojo
│  │      Column.java
│  │      DataBase.java
│  │      Settings.java
│  │      Table.java
│  │
│  ├─test
│  │      autoNewCode.java
│  │      dbTset.java
│  │
│  └─utils
│          ConvertUtils.java
│          DataBaseUtils.java
│          FileUtils.java
│          Generator.java
│          GeneratorFacade.java
│          PropertiesUtils.java

```

## 生成
Setting中有部分默认的东西，有需要可以先改一下
项目结构
```
  └─模板
│  │          └─${path1}
│  │              └─${path2}
│  │                  └─${path3}
│  │                      ├─${controllerName}
│  │                      │      ${table.name}Controller.java
│  │                      │
│  │                      ├─${daoName}
│  │                      │      ${table.name}Mapper.java
│  │                      │
│  │                      ├─${pojoName}
│  │                      │      ${table.name}.java
│  │                      │
│  │                      └─${serviceName}
│  │                          │  ${table.name}Service.java
│  │                          │
│  │                          └─${serviceImplName}
│  │                                  ${table.name}ServiceImpl.java
```
将该模板文件放到test-class中然后运行test

## 后续
需要自己导入controller响应体的类以及导入
需要的依赖
```xml
<!-- ApiOperation的依赖 -->
<dependency>  
    <groupId>io.springfox</groupId>  
    <artifactId>springfox-swagger2</artifactId>  
    <version>2.9.2</version>  
</dependency>

<!-- Valid的依赖 -->
<dependency>  
   <groupId>javax.validation</groupId>  
   <artifactId>validation-api</artifactId>  
</dependency>
```
## 参考链接：https://blog.51cto.com/u_15459458/4831631
