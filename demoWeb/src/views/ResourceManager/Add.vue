<template>
    <div class="app-form mt20 ml40">
        <h5 class="form-tit">资源添加</h5>
        <section>
            <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini"
                     :inline-message="true">
                <div class="mt30 ml15">
                    <el-form-item label="文件名称：">
                        <el-input v-model="postForm.fileName" maxlength="100" :disabled=true></el-input>
                    </el-form-item>
                    <el-form-item label="文件类型：">
                        <el-input v-model="postForm.type" maxlength="100" :disabled=true></el-input>
                    </el-form-item>
                    <el-upload
                            class="upload-file"
                            drag
                            :limit=1
                            :action="doUploadUrl"
                            :data="doUploadData"
                            :before-upload="beforeUpload"
                            :on-success="onSuccess"
                    >
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    </el-upload>
                    <el-form-item label="文件时间：">
                        <el-input v-model="postForm.createTime" maxlength="100" :disabled=true></el-input>
                    </el-form-item>
                    <el-form-item label="文件预览(目前支持pdf,png,jpg)：">
                        <el-input v-model="postForm.preRelationViewUrl" maxlength="100" :disabled=true></el-input>
                        <el-button type="primary" @click="downloadByFileName(postForm.downloadUrl)">下载</el-button>
                        <!--处理图片-->
                        <iframe v-if="postForm.type=='.jpg' || postForm.type=='.png'"
                                :src="postForm.preRelationViewUrl"
                                frameborder="0"
                                width="100%"
                                id="iFrame1"
                                name="iFrame1"
                                onload="this.height=iFrame1.document.body.scrollHeight">
                            <p>Your browser does not support iframes.</p>
                        </iframe>
                        <!--处理pdf-->
                        <iframe v-else-if="postForm.type=='.pdf' "
                                :src="postForm.preRelationViewUrl"
                                frameborder="0"
                                width="100%"
                                id="iFrame2"
                                name="iFrame2"
                                height="800px">
                            <p>Your browser does not support iframes.</p>
                        </iframe>
                    </el-form-item>
                </div>
            </el-form>
        </section>
        <div class="mt40 ml40">
            <!--<el-button type="primary" @click="submitEditForm('postForm')">确定</el-button>-->
            <!--<el-button @click="resetForm('postForm')">取消</el-button>-->
            <el-button @click="routerToList()">返回list</el-button>
        </div>
    </div>

</template>
<script>
    import axios from 'axios';

    export default {
        name: 'moduleDetail',
        props: {
            isEdit: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                postForm: {
                    fileName: '',
                    type: '',
                    createTime: '',
                    preRelationViewUrl: '',
                },
                status: [{value: 'TRUE', title: 'TRUE'}, {value: 'FALSE', title: 'FALSE'}]
                , doUploadUrl: ''
                , doUploadData: {
                    id: ''
                }
            };
        },
        created() {
            let self = this;
            /**
             * 1.接收外界参数
             */
            self.doUploadUrl = self.api.addResourceFile;
            // self.doUploadData.id = id;
            // self.getResourceFileDetailByFileName(fileName)
        },
        watch: {},
        methods: {
            getResourceFileDetailByFileName(fileName) {
                let self = this;
                self.$http.get(self.api.getResourceFileDetailByFileName, {
                    params: {
                        fileName: fileName
                    }
                }, function (response) {
                    self.postForm = response.content;
                }, function (response) {
                    //失败回调
                })

            }
            ,
            async beforeUpload(file) {
                let fileName = file.name;
                let self = this;
                let result = true;

                await axios.get(self.api.existResourceFileByName, {
                    params: {
                        fileName: fileName
                    }
                }).then(function (response) {
                    let data = response.data;
                    console.log(data);
                    if (data.code == 0) {
                        if (data.content == true) {
                            result = false;
                            console.log("文件已存在");
                            //失败回调
                            self.$message({
                                type: 'warning',
                                message: '文件已经存在，请先删除',
                                duration: 2000
                            });
                        } else {
                            console.log("文件不存在");

                        }
                    } else {
                        alert(data.msg);
                    }
                }).catch(
                    function (response) {
                        //失败回调
                        self.$message({
                            type: 'warning',
                            message: '请求异常',
                            duration: 1000
                        });
                    })

                return new Promise((resolve, reject) => {
                    if (result == true) {
                        return resolve(true);
                    } else {
                        return reject(false)
                    }

                });

            }
            ,
            routerToList() {
                window.open("#/ResourceManagerList", '_self');
            }
            ,
            existResourceFileByName(fileName) {
                let self = this;
                self.$http.get(self.api.existResourceFileByName, {
                    params: {
                        fileName: fileName
                    }
                }, function (response) {
                    if (response.code == 0) {
                        return response.content;
                    } else {
                        //失败回调
                        self.$message({
                            type: 'warning',
                            message: '请求异常',
                            duration: 1000
                        });
                    }
                }, function (response) {
                    //失败回调
                })
            },
            onSuccess(response, file, fileList) {
                let self = this;

                if (response.code == 0) {
                    if (response.content == true) {
                        self.$message({
                            type: 'success',
                            message: '上传成功',
                            duration: 500,
                        });
                    } else {
                        self.$message({
                            type: 'warning',
                            message: '请求异常',
                            duration: 500,
                        });
                    }
                } else {
                    self.$message({
                        type: 'warning',
                        message: '请求异常',
                        duration: 500,
                    });
                }
                /**
                 * 获取上传之后的数据信息
                 */
                let fileName = file.name;
                this.getResourceFileDetailByFileName(fileName);
            }
        }
    }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
