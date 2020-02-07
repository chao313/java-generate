package ${ftlVo.packageName};

<#list ftlVo.javaFieldTypes as type><#if type = "Timestamp" >import java.sql.Timestamp;
</#if><#if type = "Time" >import java.sql.Time;
</#if><#if type = "Date" >import java.util.Date;
</#if></#list>

/**
 * 这里属性是非主键字段
 *
 * 表名称      :${ftlVo.table.tableName}
 * 表类型      :${ftlVo.table.tableType}
 * 表引擎      :${ftlVo.table.engine}
 * 表版本      :${ftlVo.table.version}
 * 行格式      :${ftlVo.table.rowFormat}
 * 表创建      :${ftlVo.table.createTime}
 * 字符集      :${ftlVo.table.tableCollation}
 * 表注释      :${ftlVo.table.tableComment}
 */
public class ${ftlVo.className} {

<#list ftlVo.javaFields as field>
    <#if field.isPRI=false>
    private ${field.type} ${field.name}; <#if field.comment?? && field.comment !=""> // ${field.comment} </#if>
    </#if>
</#list>


<#list ftlVo.javaFields as field>
    <#if field.isPRI=false>
    public ${field.type} get${field.name?cap_first}() {

        return ${field.name};

    }

    public void set${field.name?cap_first}(${field.type} ${field.name}) {

        this.${field.name} = ${field.name};

    }
    </#if>
</#list>

    @Override
    public String toString() {
        return "${ftlVo.className}{" +
<#list ftlVo.javaFields as field>
    <#if field.isPRI=false>
                ", ${field.name} '" + ${field.name} +<#if field.type = 'String' > '\'' +</#if>
    </#if>
</#list>
                '}';
    }

}
