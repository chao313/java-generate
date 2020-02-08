/* 设置后台项目名称 */
const BASE_URL = '/juan';
export default {

  "getAllTConfigRegistrations": BASE_URL + '/wdGenerate/getAllTConfigRegistrations',
  "getConfigRegistrationsByPolicyId": BASE_URL + '/wdGenerate/getConfigRegistrationsByPolicyId',
  "getRegistrationsById": BASE_URL + '/wdGenerate/getRegistrationsById',
  "updateRegistrationsById": BASE_URL + '/wdGenerate/updateRegistrationsById',

  //zipManager
  "insertZipManagerByRegistId": BASE_URL + '/wdGenerate/insertZipManagerByRegistId',
  "updateZipManagerById": BASE_URL + '/wdGenerate/updateZipManagerById',
  "deleteZipManagerById": BASE_URL + '/wdGenerate/deleteZipManagerById',
  "getZipManagerById": BASE_URL + '/wdGenerate/getZipManagerById',
  "getZipManagersByRegistId": BASE_URL + '/wdGenerate/getZipManagersByRegistId',

  //LocationResources
  "getLocationResourceVosByPolicyId": BASE_URL + '/wdLocationResources/getLocationResourceVosByPolicyId',
  "getLocationOriginalResult": BASE_URL + '/wdLocationResources/getLocationOriginalResult',
  "getLocationBussResult": BASE_URL + '/wdLocationResources/getLocationBussResult',
  "getLocationOriginalAndBussResult": BASE_URL + '/wdLocationResources/getLocationOriginalAndBussResult',
  "upFile": BASE_URL + '/wdLocationResources/upFile',
  "deleteFileByMsgIdAndRegistId": BASE_URL + '/wdLocationResources/deleteFileByMsgIdAndRegistId',
  "downloadZip": BASE_URL + '/wdLocationResources/downloadZip',


  //OriginalConf
  "getOriginalConfByRegistId": BASE_URL + '/wdGenerate/getOriginalConfByRegistId',
  "updateOriginalConfById": BASE_URL + '/wdGenerate/updateOriginalConfById',


  //Properties
  "getPropertiesByRegistId": BASE_URL + '/wdGenerate/getPropertiesByRegistId',

  //用作复制使用
  "copyRecord": BASE_URL + '/wdGenerate/copyRecord',
  //批量结构化
  "batchOriginalResult": BASE_URL + '/wdLocationResources/batchOriginalResult',
  "batchBussResultJson": BASE_URL + '/wdLocationResources/batchBussResultJson',


  "updatePropertiesById": BASE_URL + '/wdGenerate/updatePropertiesById',
  //ExtractorConf
  "getExtractorConfsByRegistId": BASE_URL + '/wdGenerate/getExtractorConfsByRegistId',
  "getExtractorConfById": BASE_URL + '/wdGenerate/getExtractorConfById',
  "updateExtractorConfById": BASE_URL + '/wdGenerate/updateExtractorConfById',
  "insertExtractorConfByRegistId": BASE_URL + '/wdGenerate/insertExtractorConfByRegistId',
  "deleteExtractorConfById": BASE_URL + '/wdGenerate/deleteExtractorConfById',

  //StructConf
  "getStructConfByRegistId": BASE_URL + '/wdGenerate/getStructConfByRegistId',
  "updateStructConfById": BASE_URL + '/wdGenerate/updateStructConfById',

  //table
  "getTableRelationsByRegistId": BASE_URL + '/wdGenerate/getTableRelationsByRegistId',
  "getTableRelationById": BASE_URL + '/wdGenerate/getTableRelationById',
  "updateTableRelationById": BASE_URL + '/wdGenerate/updateTableRelationById',
  "inertTableRelationByRegistId": BASE_URL + '/wdGenerate/inertTableRelationByRegistId',
  "deleteTableRelationById": BASE_URL + '/wdGenerate/deleteTableRelationById',

  //FieldExtractorConf
  "getFieldExtractorConfsByRegistId": BASE_URL + '/wdGenerate/getFieldExtractorConfsByRegistId',
  "getFieldExtractorConfById": BASE_URL + '/wdGenerate/getFieldExtractorConfById',
  "updateFieldExtractorConfById": BASE_URL + '/wdGenerate/updateFieldExtractorConfById',
  "insertFieldExtractorConfByRegistId": BASE_URL + '/wdGenerate/insertFieldExtractorConfByRegistId',
  "deleteFieldExtractorConfById": BASE_URL + '/wdGenerate/deleteFieldExtractorConfById',

  //下载
  "generateAndFileDownLoad": BASE_URL + '/wdGenerate/generateAndFileDownLoad',


  //workOut
  "upFileAndId": BASE_URL + '/wdOut/upFileAndId',
  "getOriginalResult": BASE_URL + '/WDController/getOriginalResult',
  "getBussResult": BASE_URL + '/WDController/getBussResult',


  "TokenCsrfGetNotReplace": BASE_URL + '/Token/TokenCsrfGetNotReplace',

  //登录
  "userLogin": BASE_URL + '/user/login',
  "getMenu": BASE_URL + '/system/getMenu',
  "getUserInfo": BASE_URL + '/user/getUser',
  "userLogoff": BASE_URL + '/user/logoff',

  //首页
  "getMemorySpace": BASE_URL + '/system/getMemorySpace',
  "getOverrunDataPerDay": BASE_URL + '/homepage/getOverrunDataPerDay',
  "getLastMonthOverrunCount": BASE_URL + '/homepage/getLastMonthOverrunCount',
  "getProblemVehicleCount": BASE_URL + '/homepage/getProblemVehicleCount',


  //用户管理
  "getUserCount": BASE_URL + '/user/getUserCount',
  "getUserList": BASE_URL + '/user/getUserList',
  "addUser": BASE_URL + '/user/addUser',
  "updateUser": BASE_URL + '/user/updateUser',
  "deleteUser": BASE_URL + '/user/deleteUser',
  "getUserMgtInfo": BASE_URL + '/user/getUserInfo',
  "resetPassword": BASE_URL + '/user/resetPassword',

  //角色管理
  "getRoleList": BASE_URL + '/role/getRoleList',
  "getRoleInfo": BASE_URL + '/role/getRoleInfo',
  "addRole": BASE_URL + '/role/addRole',
  "updateRole": BASE_URL + '/role/updateRole',
  "deleteRole": BASE_URL + '/role/deleteRole',

  //keyWordConf
  "getKeyWordConfsByRegistId": BASE_URL + '/wdGenerate/getKeyWordConfsByRegistId',
  "getKeyWordConfById": BASE_URL + '/wdGenerate/getKeyWordConfById',
  "updateKeyWordConfById": BASE_URL + '/wdGenerate/updateKeyWordConfById',
  "inertKeyWordConfByRegistId": BASE_URL + '/wdGenerate/inertKeyWordConfByRegistId',
  "deleteKeyWordConfById": BASE_URL + '/wdGenerate/deleteKeyWordConfById',

  //获取文件名称
  "getFileNamesByExtractorConfId": BASE_URL + '/wdGenerate/getFileNamesByExtractorConfId',
  "getFileNamesByFieldExtractorConfId": BASE_URL + '/wdGenerate/getFileNamesByFieldExtractorConfId',

}
