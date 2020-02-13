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
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
    ${type}:[{
    <#list ftlVo.keyToVoJavaFields[type] as javaField>
        ${javaField.name}: ''<#if javaField_has_next>,</#if>
    </#list>
        }]
    <#if type_has_next>,</#if>
</#list>

    },
    created() {
     /*后台的URL*/
     this.queryBase();
    },
    methods: {
        queryBase() {
            let self = this;
            axios.post(queryBase, {})
                .then(function (response) {
                    let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                        self.titles = data.content;
                        console.log(self.titles);
                    } else {
                        alert(data.msg)
                    }
                })
                .catch(function (error) {
                    alert(this.alertErrorValue)
                });

        },

          /*处理queryByPrimaryKey*/
<#list ftlVo.keyToKeyToUrlsAllMap?keys as type>
           /* ${type} -> 用于取出model */
    <#list ftlVo.keyToKeyToUrlsAllMap[type]?keys as key>
        <#if (key=="queryByPrimaryKey")>
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}(<#list ftlVo.keyToVoPrimaryKeyJavaFields[type] as javaField>${javaField.name}<#if javaField_has_next>,</#if></#list>) {
              let self = this;
              self.$http.get(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}, {
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
            ${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}() {
              let self = this;
              self.$http.post(${ftlVo.keyToKeyToUrlsAllMap[type][key].vueKey}, {}
              ,function (response) {
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
    <#--<#if type_has_next>,</#if>-->
    </#list>
</#list>

    }
});