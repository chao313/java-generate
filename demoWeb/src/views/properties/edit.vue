<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">properties配置编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="kakfa的组：">
            <el-input v-model="postForm.groupId" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="kakfa的topic：">
            <el-input v-model="postForm.topic" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="服务的中文名称：" prop="cameraNo1">
            <el-input v-model="postForm.serviceNameChina" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="服务的英文名称：">
            <el-input v-model="postForm.serviceNameEnglish" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="服务的运行服务器IP：">
            <el-input v-model="postForm.clientIp" maxlength="50"></el-input>
          </el-form-item>
          <el-form-item label="原始结构化配置文件地址：">
            <el-input v-model="postForm.originalConfPath" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="业务结构化配置文件地址：">
            <el-input v-model="postForm.structConfPath" maxlength="100"></el-input>
          </el-form-item>
          <!--<el-form-item label="关联注册id：">-->
          <!--<el-input v-model="postForm.registerId" maxlength="100" :disabled=true></el-input>-->
          <!--</el-form-item>-->
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
          id: '',
          groupId: '',
          topic: '',
          serviceNameChina: '',
          serviceNameEnglish: '',
          clientIp: '',
          originalConfPath: '',
          structConfPath: '',
          registerId: '',
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
      self.getPropertiesData(registId)
    },
    watch: {},
    methods: {
      //获取具体的配置
      getPropertiesData(registId) {
        let self = this;
        self.$http.get(self.api.getPropertiesByRegistId, {
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
        params.append('id', self.postForm.id);
        params.append('groupId', self.postForm.groupId);
        params.append('topic', self.postForm.topic);
        params.append('serviceNameChina', self.postForm.serviceNameChina);
        params.append('serviceNameEnglish', self.postForm.serviceNameEnglish);
        params.append('clientIp', self.postForm.clientIp);
        params.append('originalConfPath', self.postForm.originalConfPath);
        params.append('structConfPath', self.postForm.structConfPath);
        params.append('registerId', self.postForm.registerId);
        params.append('status', self.postForm.status);

        self.$http.post(self.api.updatePropertiesById, params, {
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
