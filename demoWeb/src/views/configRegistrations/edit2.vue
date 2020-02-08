<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">注册配置编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="策略id：" prop="lane">
            <el-input v-model="postForm.policyId" maxlength="100" :disabled="formDisable"></el-input>
          </el-form-item>
          <el-form-item label="车牌识别仪类型：" prop="lprType">
            <el-select v-model="postForm.lprType">
              <el-option label="信路威" value="1"></el-option>
              <el-option label="沈阳聚德" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="车牌识别仪IP：" prop="lprApparatus">
            <el-input v-model="postForm.lprApparatus" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="球机类型：" prop="cameraType">
            <el-select v-model="postForm.cameraType">
              <el-option label="宇视" value="1"></el-option>
              <el-option label="海康" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="前置球机IP：" prop="cameraNo1">
            <el-input v-model="postForm.cameraNo1" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="后置球机IP：" prop="cameraNo2">
            <el-input v-model="postForm.cameraNo2" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="球机账号：" prop="account">
            <el-input v-model="postForm.account" maxlength="50"></el-input>
          </el-form-item>
          <el-form-item label="球机密码：" prop="password">
            <el-input v-model="postForm.password" maxlength="100"></el-input>
          </el-form-item>


        </div>
      </el-form>
    </section>
    <div class="mt40 ml40">
      <el-button type="primary" v-show="!isEdit" @click="submitAddForm('postForm')">确定</el-button>
      <el-button type="primary" v-show="isEdit" @click="submitEditForm('postForm')">确定</el-button>
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
          tollgateId: '',
          lane: '',
          lprApparatus: '',
          cameraNo1: '',
          cameraNo2: '',
          account: '',
          password: '',
          cameraType: '',
          lprType: ''
        },
        formDisable: false,
        tollgateList: [],
        rules: {
          tollgateId: [{
            trigger: 'change',
            required: true,
            message: '请选择所属收费站',
          }],
          lane: [{
            trigger: 'blur',
            required: true,
            message: '请输入车道编号',
          }],
          lprApparatus: [{
            trigger: 'blur',
            required: true,
            message: '请输入信路威车牌识别仪IP',
          }],
          cameraNo1: [{
            trigger: 'blur',
            required: true,
            message: '请输入前置球机IP',
          }],
          cameraNo2: [{
            trigger: 'blur',
            required: true,
            message: '请输入后置球机IP',
          }],
          account: [{
            trigger: 'blur',
            required: true,
            message: '请输入球机账号',
          }],
          password: [{
            trigger: 'blur',
            required: true,
            message: '请输入球机密码',
          }],
          cameraType: [{
            trigger: 'change',
            required: true,
            message: '请选择球机类型',
          }],
          lprType: [{
            trigger: 'change',
            required: true,
            message: '请选择车牌识别仪类型',
          }],

        },
      };
    },
    created() {
      let self = this;
      /**
       * 1.接收外界参数
       */
      const registId = this.$route.query && this.$route.query.registId;
      self.getConfigRegistrationData(registId)
      /* 收费站 */
      self.$http.get(self.api.getTollgateList, {
        params: {
          filter: {"start": 1, "end": 1000, "tollgateName": "", "tollgateType": "", "unitId": "", "prealTollgate": ""}
        }
      }, function (response) {
        self.tollgateList = response;
      }, function (response) {
        //失败回调
      })
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
          self.postForm = response;
        }, function (response) {
          //失败回调
        })

      },
      submitAddForm(formName) {
        // let self = this;
        // this.$refs[formName].validate((valid) => {
        //   if(valid) {
        //     var params = new FormData()
        //     params.append('tollgateId', self.postForm.tollgateId);
        //     params.append('lane', self.postForm.lane);
        //     params.append('lprApparatus', self.postForm.lprApparatus);
        //     params.append('cameraNo1', self.postForm.cameraNo1);
        //     params.append('cameraNo2', self.postForm.cameraNo2);
        //     params.append('account', self.postForm.account);
        //     params.append('password', self.postForm.password);
        //     params.append('cameraType', self.postForm.cameraType);
        //     params.append('lprType', self.postForm.lprType);
        //
        //     self.$http.post(self.api.getLaneInfoAdd, params, {
        //       headers: {
        //         "Content-Type": "multipart/form-data"
        //       },
        //     }, function(response) {
        //       if(response.result) {
        //         self.$message({
        //           type: 'success',
        //           message: '新增成功，页面即将跳转至列表页',
        //           duration: 500,
        //           onClose: function() {
        //             self.$router.push({
        //               path: '/tollgateDevice/list'
        //             })
        //           }
        //         });
        //
        //       } else {
        //         self.$message({
        //           type: 'error',
        //           message: '新增失败',
        //           duration: 1000
        //         });
        //       }
        //     }, function(response) {
        //       //失败回调
        //     })
        //
        //   }
        // });
      },
      submitEditForm(formName) {
        //   let self = this;
        //   this.$refs[formName].validate((valid) => {
        //     if(valid) {
        //       var params = new FormData()
        //       params.append('tollgateId', self.postForm.tollgateId);
        //       params.append('lane', self.postForm.lane);
        //       params.append('lprApparatus', self.postForm.lprApparatus);
        //       params.append('cameraNo1', self.postForm.cameraNo1);
        //       params.append('cameraNo2', self.postForm.cameraNo2);
        //       params.append('account', self.postForm.account);
        //       params.append('password', self.postForm.password);
        //       params.append('cameraType', self.postForm.cameraType);
        //       params.append('lprType', self.postForm.lprType);
        //       self.$http.post(self.api.getLaneInfoUpdate, params, {
        //         headers: {
        //           "Content-Type": "multipart/form-data"
        //         },
        //       }, function(response) {
        //         if(response.result) {
        //           self.$message({
        //             type: 'success',
        //             message: '修改成功，页面即将跳转至列表页',
        //             duration: 500,
        //             onClose: function() {
        //               self.$router.push({
        //                 path: '/tollgateDevice/list'
        //               })
        //             }
        //           });
        //
        //         } else {
        //           self.$message({
        //             type: 'warning',
        //             message: '修改失败',
        //             duration: 1000
        //           });
        //         }
        //       }, function(response) {
        //         //失败回调
        //       })
        //     }
        //   });
      },
      resetForm(formName) {
        this.$router.push({
          path: '/tollgateDevice/list'
        })
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
