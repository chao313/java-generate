<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">KeyWordConf 编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="文件名称：">
            <el-input v-model="postForm.fileNameRegex" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="表字段：">
            <el-input v-model="postForm.tableField" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="正则类型：">
            <el-input v-model="postForm.regexType" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="正则value：">
            <el-input v-model="postForm.regexValue" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="关联id：">
            <el-input v-model="postForm.registerId" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="状态：">
            <el-input v-model="postForm.status" maxlength="100"></el-input>
          </el-form-item>
        </div>
      </el-form>
    </section>
    <div class="mt40 ml40">
      <el-button type="primary" @click="submitEditForm('postForm')">确定</el-button>
      <el-button @click="resetForm('postForm')">取消</el-button>
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
          fileNameRegex: '',
          tableField: '',
          regexType: '',
          regexValue: '',
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
      const id = this.$route.query && this.$route.query.id;
      self.getKeyWordConfById(id)
    },
    watch: {},
    methods: {
      getKeyWordConfById(id) {
        let self = this;
        self.$http.get(self.api.getKeyWordConfById, {
          params: {
            id: id
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
        params.append('fileNameRegex', self.postForm.fileNameRegex);
        params.append('tableField', self.postForm.tableField);
        params.append('regexType', self.postForm.regexType);
        params.append('regexValue', self.postForm.regexValue);
        params.append('registerId', self.postForm.registerId);
        params.append('status', self.postForm.status);

        self.$http.post(self.api.updateKeyWordConfById, params, {
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
                console.log(self.postForm.registerId)
                // self.$router.push({
                //   name: 'keyWordConfList',
                //   params: {registId: self.postForm.registerId}
                // })
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
        window.open("#/locationZipManagerEdit" + "?registId=" + self.postForm.registerId, '_self');
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
