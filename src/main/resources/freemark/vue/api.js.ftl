/* 设置后台项目名称 */
const BASE_URL = '/${ftlVo.BASE_URL}';
export default {

   /*后台的URL*/
   /* ${ftlVo.baseName} */
<#list ftlVo.keyToKeyToUrls?keys as key>
   /* ${ftlVo.keyToKeyToUrls[key].comment} */
   "${ftlVo.keyToKeyToUrls[key].vueKey}": BASE_URL + '/${ftlVo.keyToKeyToUrls[key].url}'<#if key_has_next>,</#if>
</#list>

}
