package ${ftlVo.packageName};


import java.util.List;

import ${allFtl.voFtl.packageName}.${allFtl.voFtl.className};
import ${allFtl.serviceFtl.packageName}.${allFtl.serviceFtl.className};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/${ftlVo.className}")
public class ${ftlVo.className} {

    @Autowired
    private ${allFtl.serviceFtl.className} service;

    @PostMapping(value = "/insert")
    public Boolean insert(@RequestBody ${allFtl.voFtl.className} vo) {
        return service.insert(vo);
    }

    @PostMapping(value = "/inserts")
    public Boolean insert(@RequestBody List<${allFtl.voFtl.className}> vos) {
        return service.insert(vos);
    }

    @PostMapping(value = "/queryBase")
    public List<${allFtl.voFtl.className}> queryBase(@RequestBody ${allFtl.voFtl.className} query) {
        return service.queryBase(query);
    }

    @PostMapping(value = "/updateBase")
    public Boolean updateBase(@RequestBody ${allFtl.voFtl.className} source, @RequestBody ${allFtl.voFtl.className} target) {
        return service.updateBase(source, target);
    }

    @PostMapping(value = "/updateBaseIncludeNull")
    public Boolean updateBaseIncludeNull(@RequestBody ${allFtl.voFtl.className} source, @RequestBody ${allFtl.voFtl.className} target) {
        return service.updateBaseIncludeNull(source, target);
    }

    @PostMapping(value = "/deleteBase")
    public Boolean deleteBase(@RequestBody ${allFtl.voFtl.className} vo) {
        return service.deleteBase(vo);
    }

<#if ftlVo.primaryKeyJavaFields?? && (ftlVo.primaryKeyJavaFields?size>0) >

    @PostMapping(value = "/queryByPrimaryKey")
    public ${allFtl.voFtl.className} queryByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.type} ${field.name}<#if field_has_next>, </#if></#list>) {
       return service.queryByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.name}<#if field_has_next>, </#if></#list>);
    }

    @PostMapping(value = "/deleteByPrimaryKey")
    public boolean deleteByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.type} ${field.name}<#if field_has_next>, </#if></#list>) {
       return service.deleteByPrimaryKey(<#list ftlVo.primaryKeyJavaFields as field>${field.name}<#if field_has_next>, </#if></#list>);
    }
</#if>
}
