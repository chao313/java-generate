<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">注册配置编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="策略id：">
            <el-input v-model="postForm.policyId" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="原始抽取配置：">
            <el-input v-model="postForm.extractorConf" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="正文标注配置：" prop="cameraNo1">
            <el-input v-model="postForm.contentSignConf" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="关键字配置：">
            <el-input v-model="postForm.keyWordConf" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="业务映射：">
            <el-input v-model="postForm.ruleConf" maxlength="50"></el-input>
          </el-form-item>
          <el-form-item label="业务表表结构映射配置：">
            <el-input v-model="postForm.fieldExtractorConf" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="业务表父子关系配置：">
            <el-input v-model="postForm.tableRelationConf" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="真实位置在config下：">
            <el-input v-model="postForm.relativePath" maxlength="100"></el-input>
          </el-form-item>
          <!--<el-form-item label="状态：">-->
          <!--<el-input v-model="postForm.status" maxlength="100"></el-input>-->
          <!--</el-form-item>-->
        </div>
      </el-form>
    </section>
    <div class="mt40 ml40">
      <el-button type="primary" @click="submitEditForm('postForm')">确定</el-button>
      <el-button @click="resetForm('postForm')">取消</el-button>
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
          policyId: '',
          extractorConf: '',
          contentSignConf: '',
          keyWordConf: '',
          ruleConf: '',
          jsonExtractorConf: '',
          fieldExtractorConf: '',
          tableRelationConf: '',
          relativePath: '',
          status: ''
        }
      };
    },
    created() {
      let self = this;
      /**
       * 1.接收外界参数
       */
      const registId = this.$route.query && this.$route.query.registId;
      self.getConfigRegistrationData(registId)
    },
    watch: {},
    methods: {
      //获取具体的配置
      getConfigRegistrationData(registId) {
        let self = this;
        self.$http.get(self.api.getRegistrationsById, {
          params: {
            registId: registId
          }
        }, function (response) {
          self.postForm = response.content;
        }, function (response) {
          //失败回调
        })

      },
      submitEditForm(formName) {
        let self = this;
        var params = new FormData()
        const registId = this.$route.query && this.$route.query.registId;
        params.append('id', registId);
        params.append('policyId', self.postForm.policyId);
        params.append('extractorConf', self.postForm.extractorConf);
        params.append('contentSignConf', self.postForm.contentSignConf);
        params.append('keyWordConf', self.postForm.keyWordConf);
        params.append('ruleConf', self.postForm.ruleConf);
        params.append('jsonExtractorConf', self.postForm.jsonExtractorConf);
        params.append('fieldExtractorConf', self.postForm.fieldExtractorConf);
        params.append('tableRelationConf', self.postForm.tableRelationConf);
        params.append('relativePath', self.postForm.relativePath);
        params.append('status', self.postForm.status);

        self.$http.post(self.api.updateRegistrationsById, params, {
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
                  path: '/configRegistrations'
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
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
