package ${pPackage}.${serviceName}.${serviceImplName};

import java.util.List;
import ${pPackage}.${pojoName}.${table.name?cap_first};
import ${pPackage}.${serviceName}.${table.name?cap_first}Service;
import ${pPackage}.${daoName}.${table.name?cap_first}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author ${author}
* @version 1.0
* @description: 用于实现${table.name?cap_first}Service接口中的函数
* @date ${.now?date}
*/
@Service
public class ${table.name?cap_first}ServiceImpl implements ${table.name?cap_first}Service {
    @Autowired
    ${table.name?cap_first}Mapper ${table.name?lower_case}Mapper;

    /**
     * @return 以列表形式返回实体类对象
     * @description 查询所有数据
     * @author ${author}
     * @date ${.now?date}
     */
    @Override
    public List<${table.name?cap_first}> list${table.name?cap_first}(){
        return ${table.name?lower_case}Mapper.list${table.name?cap_first}();
    }

    /**
     * @param ${table.key?lower_case} 主键id
     * @return 实体类对象
     * @description 根据id获取单条数据
     * @author ${author}
     * @date ${.now?date}
     */
    @Override
    public ${table.name?cap_first} get${table.name?cap_first}By${table.key?cap_first}(${keyType} ${table.key?lower_case}){
        return ${table.name?lower_case}Mapper.get${table.name?cap_first}By${table.key?cap_first}(${table.key?lower_case});
    }

    /**
     * @param page 页数
     * @param limit 每页限制数据量
     * @return 以列表形式返回实体类对象
     * @description 分页查询数据
     * @author ${author}
     * @date ${.now?date}
     */
    @Override
    public List<${table.name?cap_first}> list${table.name?cap_first}ByPage(int page, int limit){
        int first = (page - 1) * limit;
        int second = limit;
        return ${table.name?lower_case}Mapper.list${table.name?cap_first}ByPage(first,second);
    }

    /**
     * @param ${table.name?lower_case} 要添加的实体类
     * @return 大于等于1则插入成功
     * @description 插入数据
     * @author ${author}
     * @date ${.now?date}
     */
    @Override
    public int insert${table.name?cap_first}(${table.name?cap_first} ${table.name?lower_case}){
        return ${table.name?lower_case}Mapper.insert${table.name?cap_first}(${table.name?lower_case});
    }

    /**
     * @param ${table.name?lower_case} 要修改的实体类
     * @return 大于等于1则修改成功
     * @description 根据id修改数据
     * @author ${author}
     * @date ${.now?date}
     */
    @Override
    public int update${table.name?cap_first}By${table.key?cap_first}(${table.name?cap_first} ${table.name?lower_case}){
        return ${table.name?lower_case}Mapper.update${table.name?cap_first}By${table.key?cap_first}(${table.name?lower_case});
    }

    /**
     * @param ${table.key?lower_case} 主键id
     * @return 大于等于1则删除成功
     * @description 根据id删除数据
     * @author ${author}
     * @date ${.now?date}
     */
    @Override
    public int delete${table.name?cap_first}By${table.key?cap_first}(${keyType} ${table.key?lower_case}){
        return ${table.name?lower_case}Mapper.delete${table.name?cap_first}By${table.key?cap_first}(${table.key?lower_case});
    }
}