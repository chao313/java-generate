var queryBase = "http://127.0.0.1:8000/demomaster/TTitleController/queryBase";
var host = "http://127.0.0.1:8000/";

/* 设置后台项目名称 */
var BASE_URL = host+ '/${ftlVo.BASE_URL}';

/*后台的URL*/
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>

/* ${type} */
    <#list ftlVo.keyToKeyToUrlsAllMap[type]?keys as key>
/*${ftlVo.keyToKeyToUrlsAllMap[type][key].comment} */
var ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey} = BASE_URL + '/${ftlVo.keyToKeyToUrlsAllMap[type][key].url}';</#list>
</#list>


var vue = new Vue({
    el: "#body",
    data: {
        alertError: "系统错误，请联系管理员！",
        alertSuccess: "操作成功",

        titles: [{
            id: '',
            title: ''
        }],

<#--存储多个结果-->
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
/*存储${type}多个结果*/
    ${type}s:[{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        ${javaField.name}: null<#if javaField_has_next>,</#if>
    </#list>
        }]
    <#if type_has_next>,</#if>
</#list>
,

<#--处理的是单个查询结果-->
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
/*存储${type}单个结果*/
    ${type}:{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        ${javaField.name}: null<#if javaField_has_next>,</#if>
    </#list>
        }
    <#if type_has_next>,</#if>
</#list>
,
<#--处理的是插入查询结果-->
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
/*存储${type}插入结果*/
    ${type}Insert:{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        ${javaField.name}: null<#if javaField_has_next>,</#if>
    </#list>
        }
    <#if type_has_next>,</#if>
</#list>
,
<#--处理的是主键更新-->
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
/*存储${type}主键更新*/
    ${type}UpdateByPrimaryKey:{
    source:{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        <#if javaField.isPRI=false>
            ${javaField.name}: null<#if javaField_has_next>,</#if>
        </#if>
    </#list>
    }
    ,
    target:{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        <#if javaField.isPRI=true>
            ${javaField.name}: null<#if javaField_has_next>,</#if>
        </#if>
    </#list>
     }}
    <#if type_has_next>,</#if>
</#list>
,
<#--处理的更新-->
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
/*存储${type}更新*/
    ${type}UpdateBase:{
    source:{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        ${javaField.name}: null<#if javaField_has_next>,</#if>
    </#list>
    }
    ,
    target:{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        ${javaField.name}: null<#if javaField_has_next>,</#if>
    </#list>
     }}
    <#if type_has_next>,</#if>
</#list>
,
<#--存储多条件查询条件-->
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
/*存储${type}多条件查询条件*/
    ${type}QueryBase:{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        ${javaField.name}: null<#if javaField_has_next>,</#if>
    </#list>
        }
    <#if type_has_next>,</#if>
</#list>


    },
    created() {
     /*初始化*/
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
    <#list ftlVo.keyToKeyToUrlsAllMap[type]?keys as key>
        <#if (key=="queryBase")>
            /*主键查询${type}*/
            this.${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}();
        </#if>
    </#list>
</#list>
    },
    methods: {
          /*处理queryByPrimaryKey*/
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
    <#list ftlVo.keyToKeyToUrlsAllMap[type]?keys as key>
        <#if (key=="queryByPrimaryKey")>
            /*主键查询${type}*/
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}(<#list ftlVo.keyToVoPrimaryKeyJavaFields[type] as javaField>${javaField.name}<#if javaField_has_next>,</#if></#list>) {
              let self = this;
              axios.get(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}, {
                  params: {
            <#list ftlVo.keyToVoPrimaryKeyJavaFields[type] as javaField>
                ${javaField.name}:${javaField.name}<#if javaField_has_next>,</#if>
            </#list>
                  }
             },function (response) {
                   let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                        self.${type} = data.content;
                        console.log(self.${type});
                    } else {
                        alert(data.msg);
                    }
              },
              function (response) {
                  //失败回调
                  alert(this.alertErrorValue)
              })},
        </#if>
        <#if (key=="queryBase")>
            /*多条件查询${type}*/
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}() {
              let self = this;
              axios.post(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}, self.${type}QueryBase
              ,function (response) {
                   let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                        self.${type}s = data.content;
                        console.log(self.${type});
                    } else {
                        alert(data.msg);
                    }
              },
              function (response) {
                  //失败回调
                  alert(this.alertErrorValue)
              })},
        </#if>
        <#if (key=="insert")>
            /*插入${type}*/
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}() {
              let self = this;
              axios.post(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}, self.${type}Insert
              ,function (response) {
                   let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                         if(data.content == true)
                         alert("insert 成功");
                    } else {
                         alert(data.msg);
                    }
              },
              function (response) {
                  //失败回调
                  alert(this.alertErrorValue)
              })},
        </#if>
        <#if (key=="deleteByPrimaryKey")>
            /*主键删除${type}*/
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}(<#list ftlVo.keyToVoPrimaryKeyJavaFields[type] as javaField>${javaField.name}<#if javaField_has_next>,</#if></#list>) {
              let self = this;
              axios.post(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey},  {
                  params: {
            <#list ftlVo.keyToVoPrimaryKeyJavaFields[type] as javaField>
                ${javaField.name}:${javaField.name}<#if javaField_has_next>,</#if>
            </#list>
                  }
             },function (response) {
                   let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                         if(data.content == true)
                         alert("delete 成功");
                    } else {
                         alert(data.msg);
                    }
              },
              function (response) {
                  //失败回调
                  alert(this.alertErrorValue)
              })},
        </#if>
        <#if (key=="updateByPrimaryKey")>
            /*主键更新${type}*/
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}() {
              let self = this;
              axios.post(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}, self.${type}UpdateByPrimaryKey ,function (response) {
                   let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                         if(data.content == true)
                         alert("delete 成功");
                    } else {
                         alert(data.msg);
                    }
              },
              function (response) {
                  //失败回调
                  alert(this.alertErrorValue)
              })},
        </#if>
        <#if (key=="updateBase")>
            /*多条件更新${type}*/
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}() {
              let self = this;
              axios.post(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}, self.${type}UpdateBase ,function (response) {
                   let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                         if(data.content == true)
                         alert("delete 成功");
                    } else {
                         alert(data.msg);
                    }
              },
              function (response) {
                  //失败回调
                  alert(this.alertErrorValue)
              })},
        </#if>
    <#--<#if type_has_next>,</#if>-->
    </#list>
</#list>

    }
});