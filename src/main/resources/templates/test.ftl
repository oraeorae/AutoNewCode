<#-- assign指令 在ftl模板中定义数据存入到root节点下 -->
<#assign name="傻子">
<#--然后就可以取出name的值-->
${name}

你好，${username}

<#--- if指令 -->
<#if password=1234>
    简单密码
<#elseif password=123456>
    一般密码
<#else>
    复杂密码
</#if>

<#-- list指令 迭代循环 -->
<#list list as abc>
    ${abc}
</#list>

