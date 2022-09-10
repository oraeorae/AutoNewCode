package ${pPackage}.${serviceName};

import ${pPackage}.${pojoName}.${table.name?cap_first};
import java.util.List;

/**
 * @author ${author}
 * @version 1.0
 * @description: 用于${table.name?cap_first}的Service提供接口
 * @date ${.now?date}
 */
public interface ${table.name?cap_first}Service {

    /**
     * @description 查询所有数据
     * @author ${author}
     * @date ${.now?date}
     */
    List<${table.name?cap_first}> list${table.name?cap_first}();

    /**
     * @description 根据id获取单条数据
     * @author ${author}
     * @date ${.now?date}
     */
    ${table.name?cap_first} get${table.name?cap_first}By${table.key?cap_first}(${keyType} ${table.key?lower_case});

    /**
     * @description 分页查询数据
     * @author ${author}
     * @date ${.now?date}
     */
    List<${table.name?cap_first}> list${table.name?cap_first}ByPage(int page, int limit);

    /**
     * @description 插入数据
     * @author ${author}
     * @date ${.now?date}
     */
    int insert${table.name?cap_first}(${table.name?cap_first} ${table.name?lower_case});

    /**
     * @description 根据id修改数据
     * @author ${author}
     * @date ${.now?date}
     */
    int update${table.name?cap_first}By${table.key?cap_first}(${table.name?cap_first} ${table.name?lower_case});

    /**
     * @description 根据id删除数据
     * @author ${author}
     * @date ${.now?date}
     */
    int delete${table.name?cap_first}By${table.key?cap_first}(${keyType} ${table.key?lower_case});
}