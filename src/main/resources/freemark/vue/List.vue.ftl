<template>
    <div class="app-container">
        <div class="mt20">
            <el-form :inline="true" size="mini">
                <el-form-item label="字段选择">
                    <el-select v-model="policyId" filterable clearable>
                        <el-option v-for="(item,index) in policyIdList" :key="item.policyId" :label="item.policyId"
                                   :value="item.policyId">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="status" filterable clearable>
                        <el-option v-for="(item,index) in statuses" :key="item.value" :label="item.title"
                                   :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" class="el-button-search" @click="searchEvent">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" class="el-button-search" @click="insertZipManagerByRegistId">添加
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="app-list">
            <div class="app-tab">
                <table>
                    <thead>
                    <tr>
                        <th>id</th>
                        /*显示的字段 - 英文*/
                        <#list allVueFtl.allJavaFtl.voFtl.javaFields as javaField>
                        <th>${javaField.name}</th>
                        </#list>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tr>
                        <th>序号</th>
                        /*显示的字段 - 中文*/
                        <#list allVueFtl.allJavaFtl.voFtl.javaFields as javaField>
                        <th>${javaField.comment}</th>
                        </#list>
                        <th>操作</th>
                    </tr>

                    <tbody>
                    <tr v-for="(info,index) in dataList">
                        <td>{{index+1}}</td>
                        /*显示的字段 - 具体数据*/
                        <#list allVueFtl.allJavaFtl.voFtl.javaFields as javaField>
                        <td>{{info.${javaField.name}}}</td>
                        </#list>
                        <td>
                            <span @click="routerToView(<#list allVueFtl.allJavaFtl.voFtl.primaryKeyJavaFields as javaField>info.${javaField.name}<#if javaField_has_next>,</#if></#list>)">查看</span>
                            <span @click="routerToEdit(<#list allVueFtl.allJavaFtl.voFtl.primaryKeyJavaFields as javaField>info.${javaField.name}<#if javaField_has_next>,</#if></#list>)">编辑</span>
                            <span @click="deleteByPrimaryKey(<#list allVueFtl.allJavaFtl.voFtl.primaryKeyJavaFields as javaField>info.${javaField.name}<#if javaField_has_next>,</#if></#list>)">删除</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <p v-show="total == 0" class="no-data-tip">没有找到相关数据！</p>
                <div>
                    <pre>{{jsonData}}</pre>
                </div>
            </div>
            <div class="mt20" v-show="total > 0">
                <el-pagination background @current-change="handleCurrentChange" :current-page.sync="currentPage"
                               :page-size="pageSize" layout="total,prev, pager, next" :total="total">
                </el-pagination>
            </div>

        </div>

    </div>

</template>

<script>
    import {getPreDay} from '@/utils/index.js'

    export default {
        data() {
            return {
                policyIdList: [],
                policyId: '',
                statuses: [{value: 1, title: '生效'}, {value: 2, title: '失效'}, {value: null, title: '全部'}],
                status: '',
                dataList: [],
                total: -1,
                currentPage: 1,
                pageSize: 10,
                start: '',
                end: '',
                plateNo: '',
                exitStation: '',
                exitStationList: [],
                resultDetail: {},
                viewDialogVisible: false,
                startTime: '',
                endTime: '',
                timeRange: [],
                registId: '',
                jsonData: ''
            }
        },
        mounted() {
            let self = this;
        },
        created() {
            let self = this;
            /**
             * 1.接收外界参数
             */
            const registId = this.$route.query && this.$route.query.registId;
            self.registId = registId;
            self.getZipManagersByRegistId(registId)
        },
        watch: {
            timeRange: {
                handler: function (newVal, oldVa) {
                    var self = this;
                    if (newVal != null) {
                        self.startTime = self.timeRange.length > 0 ? self.timeRange[0] : '';
                        self.endTime = self.timeRange.length > 0 ? self.timeRange[1] : '';
                    } else {
                        self.startTime = '';
                        self.endTime = '';
                    }

                },
            },
        },
        methods: {
         deleteByPrimaryKey(<#list allVueFtl.allJavaFtl.voFtl.primaryKeyJavaFields as javaField>${javaField.name}<#if javaField_has_next>,</#if></#list>) {
             var self = this;
             this.$confirm('是否删除该条数据？', '提示', {
                 confirmButtonText: '确定',
                 cancelButtonText: '取消',
                 center: true
             }).then(() = > {
                 self.$http.get("${allVueFtl.apiJsFtl.keyToUrls[deleteByPrimaryKey]}", {
                 params: {
                   <#list allVueFtl.allJavaFtl.voFtl.primaryKeyJavaFields as javaField>
                       ${javaField.name}:${javaField.name} <#if javaField_has_next>,</#if>
                   </#list>
                 }
         }, function (response) {
                 if (response.content == true) {
                     self.$message({
                         type: 'success',
                         message: '删除成功',
                         duration: 1000
                     });
                     // self.getZipManagersByRegistId(self.registId)
                 } else {
                     self.$message({
                         type: 'warning',
                         message: '删除失败',
                         duration: 1000
                     });
                 }
             }, function (response) {
                 //失败回调
             })

         })
         },
         routerToView(id) {
             //跳转携带参数
             window.open("#/zipManagerEdit" + "?id=" + id, '_self');
         },
         routerToEdit(id) {
             //跳转携带参数
             window.open("#/zipManagerEdit" + "?id=" + id, '_self');
         }

        }

    }
</script>

<style rel="stylesheet/scss" lang="scss">
    .video-detail {

    p {
        display: inline-block;
        width: 40%;
        vertical-align: top;
    }

    label {
        width: 120px;
        display: inline-block;
    }

    span {
        color: #888;
    }

    }
</style>
