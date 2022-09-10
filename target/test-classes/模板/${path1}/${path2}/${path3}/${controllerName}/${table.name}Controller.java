package ${pPackage}.${controllerName};

import ${pPackage}.${pojoName}.${table.name?cap_first};
import ${pPackage}.${serviceName}.${table.name?cap_first}Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;
import java.util.List;

/**
* @author ${author}
* @version 1.0
* @description ${table.name?cap_first}的Controller层
* @data ${.now?date}
*/
@RestController
@RequestMapping(value = "/api/${table.name?lower_case}")
public class ${table.name?cap_first}Controller {
    @Autowired
    ${table.name?cap_first}Service ${table.name?lower_case}Service;

    /**
    * @param ${table.name?lower_case} 添加的实体类
    * @return ${returnValue} 自定义响应体
    * @description 添加数据
    * @author ${author}
    * @data ${.now?date}
    */
    @PostMapping("/insert")
    @ApiOperation(value = "添加数据")
    public ${returnValue} insert${table.name?cap_first}(@Valid ${table.name?cap_first} ${table.name?lower_case}) {
        try {
            if( ${table.name?lower_case}Service.insert${table.name?cap_first}(${table.name?lower_case}) >= 1 ){
                return ${successFunction}("添加成功");
            }else{
                return ${successFunction}("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ${failFunction}("服务器内部错误：" + e.toString());
        }
    }


    /**
     * @param ${table.key?lower_case} 主键id
     * @return ${returnValue} 自定义响应体
     * @description 根据id获取单条数据
     * @author ${author}
     * @data ${.now?date}
     */
    @GetMapping("/get")
    @ApiOperation(value = "根据id获取单条数据")
    public ${returnValue} get${table.name?cap_first}By${table.key?cap_first}(@RequestParam("${table.key?lower_case}") ${keyType} ${table.key?lower_case}) {
        try {
            return ${successFunction}(${table.name?lower_case}Service.get${table.name?cap_first}By${table.key?cap_first}(${table.key?lower_case}));
        } catch (Exception e) {
            e.printStackTrace();
            return ${failFunction}(3001, "服务器内部错误：" + e.toString());
        }
    }

    /**
     * @param page 查询的页数
     * @return ${returnValue} 自定义响应体
     * @description 分页查询数据（备注：limit默认为10）
     * @author ${author}
     * @data ${.now?date}
     */
    @GetMapping("/list/page")
    @ApiOperation(value = "分页查询数据")
    public ${returnValue} list${table.name?cap_first}ByPage(@RequestParam("page") int page) {
        try {
            //limit默认为10
            return ${successFunction}(${table.name?lower_case}Service.list${table.name?cap_first}ByPage(page,10));
        } catch (Exception e) {
            e.printStackTrace();
            return ${failFunction}("服务器内部错误：" + e.toString());
        }
    }

    /**
     * @param ${table.name?lower_case} 需要修改的实体类
     * @return ${returnValue} 自定义响应体
     * @description 根据id修改数据
     * @author ${author}
     * @data ${.now?date}
     */
    @PutMapping("/update")
    @ApiOperation(value = "根据id修改数据")
    public ${returnValue} update${table.name?cap_first}By${table.key?cap_first}(@Valid ${table.name?cap_first} ${table.name?lower_case}) {
        try {
            if( ${table.name?lower_case}Service.update${table.name?cap_first}By${table.key?cap_first}(${table.name?lower_case}) >= 1 ){
                return ${successFunction}("修改成功");
            }else{
                return ${successFunction}("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ${failFunction}("服务器内部错误：" + e.toString());
        }
    }

    /**
     * @return ${returnValue} 自定义响应体
     * @description 查询所有数据（备注：不常用）
     * @author ${author}
     * @data ${.now?date}
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有数据")
    public ${returnValue} list${table.name?cap_first}() {
        try {
            return ${successFunction}(${table.name?lower_case}Service.list${table.name?cap_first}());
        } catch (Exception e) {
            e.printStackTrace();
            return ${failFunction}("服务器内部错误：" + e.toString());
        }
    }

    /**
     * @param ${table.key?lower_case} 主键id
     * @return ${returnValue} 自定义响应体
     * @description 根据id删除数据
     * @author ${author}
     * @data ${.now?date}
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除数据")
    public ${returnValue} delete${table.name?cap_first}By${table.key?cap_first}(@RequestParam("${table.key?lower_case}") ${keyType} ${table.key?lower_case}) {
        try {
            if( ${table.name?lower_case}Service.delete${table.name?cap_first}By${table.key?cap_first}(${table.key?lower_case}) >= 1 ){
                return ${successFunction}("删除成功");
            }else{
                return ${successFunction}("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ${failFunction}("服务器内部错误：" + e.toString());
        }
    }

}