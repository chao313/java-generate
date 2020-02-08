<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">extractorConf编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="zip文件名称：">
            <el-input v-model="postForm.zipName" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="zip中文件的名称：">
            <el-input v-model="postForm.fileNames" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-upload
            class="upload-file"
            drag
            limit="1"
            :action="doUploadUrl"
            :data="doUploadData">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
          <el-form-item label="zip中的内容：">
            <el-input v-model="postForm.zipContent" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="状态：">
            <el-input v-model="postForm.status" maxlength="100"></el-input>
          </el-form-item>
        </div>
      </el-form>
    </section>
    <div class="mt40 ml40">
      <!--<el-button type="primary" @click="submitEditForm('postForm')">确定</el-button>-->
      <!--<el-button @click="resetForm('postForm')">取消</el-button>-->
      <el-button @click="back()">返回list</el-button>
    </div>
  </div>

</template>
<script>
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
          id: '',
          zipName: '',
          fileNames: '',
          zipContent: '',
          uploadTime: '',
          originalResult: '',
          originalTime: '',
          bussResult: '',
          bussTime: '',
          status: '',
          registerId: '',
          status: ''
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
      const id = this.$route.query && this.$route.query.id;
      self.doUploadUrl = self.api.upFileAndId;
      self.doUploadData.id = id;
      self.getZipManagerById(id)
    },
    watch: {},
    methods: {
      getZipManagerById(id) {
        let self = this;
        self.$http.get(self.api.getZipManagerById, {
          params: {
            id: id
          }
        }, function (response) {
          self.postForm = response.content;
        }, function (response) {
          //失败回调
        })

      }
      ,
      fileChange(event, chooseId) {
        console.log(this.$refs.file)
        console.log(this.$refs.file)
        // file = this.$refs.file[0];
        // console.log(file.name)

      },
      submitEditForm(formName) {
        let self = this;
        var params = new FormData()
        params.append('id', self.postForm.id);
        params.append('fileNameRegex', self.postForm.fileNameRegex);
        params.append('fieldName', self.postForm.fieldName);
        params.append('level', self.postForm.level);
        params.append('superior', self.postForm.superior);
        params.append('outputType', self.postForm.outputType);
        params.append('extractType', self.postForm.extractType);
        params.append('extractValue', self.postForm.extractValue);
        params.append('washType', self.postForm.washType);
        params.append('washValue', self.postForm.washValue);
        params.append('isSingle', self.postForm.isSingle);
        params.append('registerId', self.postForm.registerId);
        params.append('status', self.postForm.status);

        self.$http.post(self.api.updateExtractorConfById, params, {
          headers: {
            "Content-Type": "multipart/form-data"
          },
        }, function (response) {
          if (response.content == true) {
            self.$message({
              type: 'success',
              message: '修改成功，页面即将跳转至列表页',
              duration: 500,
              onClose: function () {
                self.$router.push({
                  name: 'extractorConfList',
                  params: {registId: self.postForm.registerId}
                })
              }
            });

          } else {
            self.$message({
              type: 'warning',
              message: '修改失败',
              duration: 1000
            });
          }
        }, function (response) {
          //失败回调
        })
      },
      resetForm(formName) {
        this.$router.push({
          path: '/configRegistrations'
        })
      },
      back() {
        let self = this;
        window.open("#/zipManagerList" + "?registId=" + self.postForm.registerId, '_self');
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
