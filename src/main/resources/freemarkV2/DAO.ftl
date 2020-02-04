package ${ftlVo.basePackage};


import java.util.List;

import ${allFtl.voFtl.packageName}.${allFtl.voFtl.className};
<#list ftlVo.javaFieldTypes as type><#if type = "Timestamp" >import java.sql.Timestamp;
</#if><#if type = "Time" >import java.sql.Time;
</#if><#if type = "Date" >import java.util.Date;
</#if></#list>

import org.apache.ibatis.annotations.Param;

/**
 * 表名称      :${ftlVo.table.tableName}
 * 表类型      :${ftlVo.table.tableType}
 * 表引擎      :${ftlVo.table.engine}
 * 表版本      :${ftlVo.table.version}
 * 行格式      :${ftlVo.table.rowFormat}
 * 表创建      :${ftlVo.table.createTime}
 * 字符集      :${ftlVo.table.tableCollation}
 * 表注释      :${ftlVo.table.tableComment}
 */
public interface ${ftlVo.className} {

    /**
     * insert
     */
    int insert(${allFtl.voFtl.className} vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<${allFtl.voFtl.className}> vos);

    /**
     * 查询base
     */
    List<${allFtl.voFtl.className}> queryBase(${allFtl.voFtl.className} query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") ${allFtl.voFtl.className} source, @Param(value = "target") ${jallFtl.voFtl.className} target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") ${allFtl.voFtl.className} source, @Param(value = "target") ${allFtl.voFtl.className} target);

    /**
     * 删除base
     */
    int deleteBase(${allFtl.voFtl.className} vo);

 <#if ftlVo.primaryKeyJavaFields?? && (ftlVo.primaryKeyJavaFields?size>0) >

    /**
     * 根据PrimaryKey查询
     * <#list ftlVo.primaryKeyJavaFields as field><p>
     * ${field.name} : ${field.comment}</#list>
     */
    ${allFtl.voFtl.className} queryByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.type} ${field.name}<#if field_has_next>,</#if></#list>);

    /**
     * 根据PrimaryKey删除
     * <#list ftlVo.primaryKeyJavaFields as field><p>
     * ${field.name} : ${field.comment}</#list>
     */
    int deleteByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.type} ${field.name}<#if field_has_next>,</#if></#list>);

 </#if>
}
