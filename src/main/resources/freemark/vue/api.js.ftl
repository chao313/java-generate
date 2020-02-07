/* 设置后台项目名称 */
const BASE_URL = '/demomaster';
export default {

   /*后台的URL*/
  <#list allVueFtl.allJavaFtls as allJavaFtl>
      /* 遍历api */

      /* ${allJavaFtl.controllerFtl.className} */
      <#list ftlVo.apis as api>
       "${allJavaFtl.controllerFtl.className?lower_case}_${api}": BASE_URL + '/${allJavaFtl.controllerFtl.className}/${api}'<#if field_has_next>, </#if>
      </#list>
  </#list>

}
