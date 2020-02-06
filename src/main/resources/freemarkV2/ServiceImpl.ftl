package ${ftlVo.packageName};


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${allFtl.voFtl.packageName}.${allFtl.voFtl.className};
import ${allFtl.daoFtl.packageName}.${allFtl.daoFtl.className};
import ${allFtl.serviceFtl.packageName}.${allFtl.serviceFtl.className};

<#list ftlVo.javaFieldTypes as type><#if type = "Timestamp" >import java.sql.Timestamp;
</#if><#if type = "Time" >import java.sql.Time;
</#if><#if type = "Date" >import java.util.Date;
</#if></#list>


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
@Service
public class ${ftlVo.className} implements ${allFtl.serviceFtl.className} {

    @Autowired
    private ${allFtl.daoFtl.className} dao;

    /**
     * insert
     */
    @Override
    public boolean insert(${allFtl.voFtl.className} vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<${allFtl.voFtl.className}> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<${allFtl.voFtl.className}> queryBase(${allFtl.voFtl.className} query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(${allFtl.voFtl.className} source, ${allFtl.voFtl.className} target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(${allFtl.voFtl.className} source, ${allFtl.voFtl.className} target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(${allFtl.voFtl.className} vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }

 <#if ftlVo.primaryKeyJavaFields?? && (ftlVo.primaryKeyJavaFields?size>0) >

    /**
     * 根据PrimaryKey查询
     * <#list ftlVo.primaryKeyJavaFields as field><p>
     * ${field.name}  ${field.comment}</#list>
     */
    @Override
    public ${allFtl.voFtl.className} queryByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.type} ${field.name}<#if field_has_next>, </#if></#list>) {

        return dao.queryByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.name}<#if field_has_next>, </#if></#list>);

    }

    /**
     * 根据PrimaryKey删除
     * <#list ftlVo.primaryKeyJavaFields as field><p>
     * ${field.name} : ${field.comment}</#list>
     * <#list ftlVo.primaryKeyJavaFields as field>@param ${field.name}
     * </#list>
     */
    @Override
    public boolean deleteByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.type} ${field.name}<#if field_has_next>, </#if></#list>) {

        return dao.deleteByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.name}<#if field_has_next>, </#if></#list>) > 0 ? true : false;

    }

 </#if>
}
