/* 设置后台项目名称 */
const BASE_URL = '/${ftlVo.BASE_URL}';
export default {

      /*后台的URL*/
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>

      /* ${type} */
   <#list ftlVo.keyToKeyToUrlsAllMap[type]?keys as key>
      /* ${ftlVo.keyToKeyToUrlsAllMap[type][key].comment} */
      "${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}": BASE_URL + '/${ftlVo.keyToKeyToUrlsAllMap[type][key].url}'<#if key_has_next>,</#if>
      </#list><#if type_has_next>,</#if>
</#list>


}
