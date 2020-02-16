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
                    downloadUrl: ''
                },
            };
        },
        created() {
            let self = this;
            /**
             * 1.接收外界参数
             */
            const fileName = this.$route.query && this.$route.query.fileName;
            this.getResourceFileDetailByFileName(fileName);
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

            },
            downloadByFileName(url) {
                window.open(url, '_blank');
            },
            routerToList() {
                window.open("#/ResourceManagerList", '_self');
            }
        }
    }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
