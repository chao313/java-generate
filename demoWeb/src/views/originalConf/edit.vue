<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">originalConf配置编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="策略Id：">
            <el-input v-model="postForm.policyId" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="结构化类型：">
            <el-input v-model="postForm.structureType" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="文件结构化路径：" prop="cameraNo1">
            <el-input v-model="postForm.filePath" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="邮件名称：">
            <el-input v-model="postForm.mailName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="插件的beanName：">
            <el-input v-model="postForm.beanName" maxlength="50"></el-input>
          </el-form-item>
          <el-form-item label="消费的Topic：">
            <el-input v-model="postForm.consumerTopic" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="原始结构化入库topic：">
            <el-input v-model="postForm.topic" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="唯一性设置类型：">
            <el-select v-model="postForm.uniqueKeyType" clearable filterable>
              <el-option label="field" value="field"></el-option>
              <el-option label="content" value="content"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="唯一性字段：">
            <el-input v-model="postForm.uniqueKey" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="唯一性输出类型：">
            <el-select v-model="postForm.uniqueOut" clearable filterable>
              <el-option label="String" value="String"></el-option>
              <el-option label="MD5" value="MD5"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="版本号：">
            <el-input v-model="postForm.verNum" maxlength="100" :disabled=true></el-input>
          </el-form-item>
          <el-form-item label="结构化原网页名称列表：">
            <el-input v-model="postForm.fileName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="上传的附件格式：">
            <el-input v-model="postForm.attchFileType" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="上传的hbase表名：">
            <el-input v-model="postForm.hbaseTableName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="是否接入校验组件" class="mt20">
            <el-radio-group v-model="postForm.validcom">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="是否保存结构化结果文件" class="mt20">
            <el-radio-group v-model="postForm.saveResult">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="多实体结果" class="mt20">
            <el-radio-group v-model="postForm.mulitEntity">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
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
          policyId: '',
          structureType: '',
          filePath: '',
          mailName: '',
          beanName: '',
          consumerTopic: '',
          topic: '',
          uniqueKeyType: '',
          uniqueKey: '',
          uniqueOut: '',
          verNum: '',
          fileName: '',
          attchFileType: '',
          hbaseTableName: '',
          validcom: '',
          saveResult: '',
          mulitEntity: '',
          registerId: '',
          status: ''
        },
        status: [{value: 'true', title: 'true'}, {value: 'false', title: 'false'}]
      };
    },
    created() {
      let self = this;
      /**
       * 1.接收外界参数
       */
      const registId = this.$route.query && this.$route.query.registId;
      self.getOriginalConfByRegistId(registId)
    },
    watch: {},
    methods: {
      //获取具体的配置
      getOriginalConfByRegistId(registId) {
        let self = this;
        self.$http.get(self.api.getOriginalConfByRegistId, {
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
        var params = new FormData();
        params.append('id', self.postForm.id);
        params.append('policyId', self.postForm.policyId);
        params.append('structureType', self.postForm.structureType);
        params.append('filePath', self.postForm.filePath);
        params.append('mailName', self.postForm.mailName);
        params.append('beanName', self.postForm.beanName);
        params.append('consumerTopic', self.postForm.consumerTopic);
        params.append('topic', self.postForm.topic);
        params.append('uniqueKeyType', self.postForm.uniqueKeyType);
        params.append('uniqueKey', self.postForm.uniqueKey);
        params.append('uniqueOut', self.postForm.uniqueOut);
        params.append('verNum', self.postForm.verNum);
        params.append('fileName', self.postForm.fileName);
        params.append('attchFileType', self.postForm.attchFileType);
        params.append('hbaseTableName', self.postForm.hbaseTableName);
        params.append('validcom', self.postForm.validcom);
        params.append('saveResult', self.postForm.saveResult);
        params.append('mulitEntity', self.postForm.mulitEntity);
        params.append('registerId', self.postForm.registerId);
        params.append('status', self.postForm.status);

        self.$http.post(self.api.updateOriginalConfById, params, {
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
