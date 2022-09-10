package ${pPackage}.${pojoName};

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ${author}
 * @version 1.0
 * @description ${table.name?cap_first}的实体类
 * @date ${.now?date}
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${table.name?cap_first} {
<#list table.columnList as t>
    private ${t.columnType} ${t.columnName};
</#list>
}
