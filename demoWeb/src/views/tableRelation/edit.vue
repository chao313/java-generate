<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">TableRelation 编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="主表名称：">
            <el-input v-model="postForm.masterTableName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="从表名称：">
            <el-input v-model="postForm.slaveTableName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="主表关联字段：" prop="cameraNo1">
            <el-input v-model="postForm.masterRelatedField" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="从表关联字段：">
            <el-input v-model="postForm.slaveRelatedField" maxlength="100"></el-input>
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
          masterTableName: '',
          slaveTableName: '',
          masterRelatedField: '',
          slaveRelatedField: '',
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
      self.getTableRelationById(id)
    },
    watch: {},
    methods: {
      getTableRelationById(id) {
        let self = this;
        self.$http.get(self.api.getTableRelationById, {
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
        params.append('masterTableName', self.postForm.masterTableName);
        params.append('slaveTableName', self.postForm.slaveTableName);
        params.append('masterRelatedField', self.postForm.masterRelatedField);
        params.append('slaveRelatedField', self.postForm.slaveRelatedField);
        params.append('registerId', self.postForm.registerId);
        params.append('status', self.postForm.status);

        self.$http.post(self.api.updateTableRelationById, params, {
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
                self.$router.push({
                  name: 'tableRelationList',
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
        window.open("#/tableRelationList" + "?registId=" + self.postForm.registerId, '_self');
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
