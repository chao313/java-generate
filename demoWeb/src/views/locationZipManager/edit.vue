<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">locationResources 添加</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="zip文件名称：">
            <el-input v-model="postForm.zipName" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-upload
            class="upload-file"
            drag
            :action="doUploadUrl"
            :data="doUploadData">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
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
        registId: '',
        status: [{value: 'TRUE', title: 'TRUE'}, {value: 'FALSE', title: 'FALSE'}]
        , doUploadUrl: ''
        , doUploadData: {
          registId: ''
        }
      };
    },
    created() {
      let self = this;
      /**
       * 1.接收外界参数
       */
      const registId = this.$route.query && this.$route.query.registId;
      self.registId = registId;
      self.doUploadUrl = self.api.upFile;
      self.doUploadData.registId = registId;
    },
    watch: {},
    methods: {
      fileChange(event, chooseId) {
        console.log(this.$refs.file)
        console.log(this.$refs.file)
        // file = this.$refs.file[0];
        // console.log(file.name)

      },
      resetForm(formName) {
        this.$router.push({
          path: '/configRegistrations'
        })
      },
      back() {
        let self = this;
        window.open("#/locationZipManagerList" + "?registId=" + self.registerId, '_self');
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
