<template>
    <div class="app-container">
        <div class="mt20">
            <el-form :inline="true" size="mini">
                <!--<el-form-item label="字段选择">-->
                <!--<el-select v-model="policyId" filterable clearable>-->
                <!--<el-option v-for="(item,index) in policyIdList" :key="item.policyId" :label="item.policyId"-->
                <!--:value="item.policyId">-->
                <!--</el-option>-->
                <!--</el-select>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="状态">-->
                <!--<el-select v-model="status" filterable clearable>-->
                <!--<el-option v-for="(item,index) in statuses" :key="item.value" :label="item.title"-->
                <!--:value="item.value">-->
                <!--</el-option>-->
                <!--</el-select>-->
                <!--</el-form-item>-->
                <!--<el-form-item>-->
                <!--<el-button type="primary" class="el-button-search" @click="searchEvent">查询</el-button>-->
                <!--</el-form-item>-->
                <el-form-item>
                    <el-button type="primary" class="el-button-search" @click="routerToAdd()">添加
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
                        <th>fileName</th>
                        <th>type</th>
                        <th>createTime</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tr>
                        <th>序号</th>
                        <th>文件名称</th>
                        <th>文件类型</th>
                        <th>创建的时间</th>
                        <th>操作</th>
                    </tr>

                    <tbody>
                    <tr v-for="(info,index) in dataList">
                        <td>{{index+1}}</td>
                        <td>{{info.fileName}}</td>
                        <td>{{info.type}}</td>
                        <td>{{info.createTime}}</td>
                        <td>
                            <span @click="deleteFileByName(info.fileName)">删除</span>
                            <span @click="preByFileName(info.preRelationViewUrl)">预览</span>
                            <span @click="downloadByFileName(info.downloadUrl)">下载</span>
                            <span @click="routerToView(info.fileName)">查看</span>
                            <span @click="copy(info.preRelationViewUrl)">复制路径</span>
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
                dataList: [],
                jsonData: '',
                search: {
                    fileName: '',
                    type: '',
                    preRelationViewUrl: '',
                    preAbsolutionViewUrl: '',
                    createTime: ''
                }
            }
        },
        mounted() {
            let self = this;
        },
        created() {
            this.getResourceFileDetails();
        },
        watch: {},
        methods: {
            /* 获得资源文件  */
            getResourceFileDetails() {
                let self = this;
                self.$http.post(self.api.getResourceFileDetails,
                    self.search
                    , {}
                    , function (response) {
                        self.dataList = response.content;
                    }, function (response) {
                        //失败回调
                        self.$message({type: 'error', message: '请求发生异常', duration: 500,});
                    })
            },
            preByFileName(url) {
                window.open(url, '_blank');
            },
            downloadByFileName(url) {
                window.open(url, '_self');
            },
            deleteFileByName(fileName) {
                let self = this;
                self.$http.get(self.api.deleteResourceFileByName, {
                    params: {
                        fileName: fileName
                    }
                }, function (response) {
                    if (response.code == 0) {
                        self.postForm = response.content;
                        self.$message({
                            type: 'success',
                            message: '删除成功',
                            duration: 2000
                        });
                        self.getResourceFileDetails();
                    } else {
                        self.$message({
                            type: 'error',
                            message: response.msg,
                            duration: 2000
                        });
                    }
                }, function (response) {
                    //失败回调
                    self.$message({
                        type: 'warning',
                        message: '请求异常',
                        duration: 1000
                    });
                })

            },
            routerToAdd() {
                window.open("#/ResourceManagerAdd", '_self');
            },
            routerToView(fileName) {
                /**
                 * 跳转到View
                 */
                window.open("#/ResourceManagerView?fileName=" + fileName, '_self');
            },
            copy(preRelationViewUrl) {
                let self = this;
                let oInput = document.createElement('input') // 生成一个节点
                oInput.value = preRelationViewUrl;  // 你要复制的文本
                document.body.appendChild(oInput) // 插入文档
                oInput.select() // 选择对象
                document.execCommand("Copy") // 执行浏览器复制命令
                document.body.removeChild(oInput) //移除标签

                self.$message({
                    type: 'success',
                    message: '复制成功',
                    duration: 1000
                });
            }
        },

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
