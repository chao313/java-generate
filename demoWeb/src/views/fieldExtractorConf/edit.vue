<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">fieldExtractorConf 编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <el-form-item label="文件名称：">
            <el-select v-model="postForm.fileNameRegex" filterable clearable>
              <el-option v-for="(item,index) in fileNames" :label="item"
                         :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="表名：">
            <el-input v-model="postForm.tableName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="字段名：" prop="cameraNo1">
            <el-input v-model="postForm.fieldName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="提取类型：">
            <el-select v-model="postForm.extractType" clearable filterable>
              <el-option label="JSONPATH" value="JSONPATH"></el-option>
              <el-option label="FIXED" value="FIXED"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="取出值：">
            <el-input v-model="postForm.extractValue" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="输出类型：">
            <el-select v-model="postForm.outType" clearable filterable>
              <el-option label="DATE" value="DATE"></el-option>
              <el-option label="TIMESTAMP" value="TIMESTAMP"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="是否单值" class="mt20">
            <el-radio-group v-model="postForm.isSingle">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="是否是附件配置" class="mt20">
            <el-radio-group v-model="postForm.isAttachmentConfig">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="是否允许为空" class="mt20">
            <el-radio-group v-model="postForm.allowEmpty">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="是否是需要关联关系" class="mt20">
            <el-radio-group v-model="postForm.needRelateCode">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="清洗类型：">
            <el-select v-model="postForm.washType" clearable filterable>
              <el-option v-for="(info,index)  in washTypeEnum" :label="info.value" :value="info.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="清洗值：">
            <el-input v-model="postForm.washValue" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="配置分类值：">
            <el-input v-model="postForm.classifyValue" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="时间源格式：">
            <el-input v-model="postForm.inputDateFormat" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="细分类型：">
            <el-input v-model="postForm.relateCodeType" maxlength="100"></el-input>
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
      <el-button @click="batchBussResultJson()">批量测试</el-button>
    </div>
    <div>
      <pre>{{batchJsonData}}</pre>
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
          tableName: '',
          fieldName: '',
          extractType: '',
          extractValue: '',
          outType: '',
          isSingle: '',
          isAttachmentConfig: '',
          allowEmpty: '',
          needRelateCode: '',
          washType: '',
          washValue: '',
          classifyValue: '',
          inputDateFormat: '',
          relateCodeType: '',
          registerId: '',
          status: ''
        },
        status: [{value: 'TRUE', title: 'TRUE'}, {value: 'FALSE', title: 'FALSE'}],
        washTypeEnum: [
          {value: 'STRINGSPLIT', title: 'STRINGSPLIT'},
          {value: 'SPRINGREPLACE', title: 'SPRINGREPLACE'},
          {value: 'SPRINGREPLACEALL', title: 'SPRINGREPLACEALL'},
          {value: 'REGEX', title: 'REGEX'}],
        batchJsonData: '',
        id: '',
        fileNames: ''
      };
    },
    created() {
      let self = this;
      /**
       * 1.接收外界参数
       */
      const id = this.$route.query && this.$route.query.id;
      self.id = id;
      self.getFieldExtractorConfById(id);
      self.getFileNamesByFieldExtractorConfId(id);
    },
    watch: {},
    methods: {
      getFieldExtractorConfById(id) {
        let self = this;
        self.$http.get(self.api.getFieldExtractorConfById, {
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
        params.append('tableName', self.postForm.tableName);
        params.append('fieldName', self.postForm.fieldName);
        params.append('extractType', self.postForm.extractType);
        params.append('extractValue', self.postForm.extractValue);
        params.append('outType', self.postForm.outType);
        params.append('isSingle', self.postForm.isSingle);
        params.append('isAttachmentConfig', self.postForm.isAttachmentConfig);
        params.append('allowEmpty', self.postForm.allowEmpty);
        params.append('needRelateCode', self.postForm.needRelateCode);
        params.append('washType', self.postForm.washType);
        params.append('washValue', self.postForm.washValue);
        params.append('classifyValue', self.postForm.classifyValue);
        params.append('inputDateFormat', self.postForm.inputDateFormat);
        params.append('relateCodeType', self.postForm.relateCodeType);
        params.append('registerId', self.postForm.registerId);
        params.append('status', self.postForm.status);

        self.$http.post(self.api.updateFieldExtractorConfById, params, {
          headers: {
            "Content-Type": "multipart/form-data"
          },
        }, function (response) {
          if (response.content == true) {
            self.$message({
              type: 'success',
              message: '修改成功，页面即将跳转至列表页',
              duration: 500,
              // 可能多次修改，
              // onClose: function () {
              //   self.$router.push({
              //     name: 'fieldExtractorConfList',
              //     params: {registId: self.postForm.registerId}
              //   })
              // }
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
        let self = this;
        window.open("#/fieldExtractorConfList" + "?registId=" + self.postForm.registerId, '_self');
      },
      back() {
        let self = this;
        window.open("#/fieldExtractorConfList" + "?registId=" + self.postForm.registerId, '_self');
      },
      batchBussResultJson() {
        let self = this;
        self.$http.get(self.api.batchBussResultJson, {
          params: {
            fieldExtractorConfId: self.id
          }
        }, function (response) {
          if (response.code == 0) {
            self.$message({
              type: 'success',
              message: '批量获取成功',
              duration: 500,
            });
            self.batchJsonData = response.content;
          } else {
            self.$message({
              type: 'success',
              message: '批量获取失败' + response.msg,
              duration: 500,
            });
          }
        }, function (response) {
          //失败回调
          self.$message({
            type: 'success',
            message: '请求异常，请检查网络',
            duration: 500,
          });
        })
      }, getFileNamesByFieldExtractorConfId(id) {
        let self = this;
        self.$http.get(self.api.getFileNamesByFieldExtractorConfId, {
          params: {
            fieldExtractorConfId: id
          }
        }, function (response) {
          if (response.code == 0) {
            self.fileNames = response.content;
          } else {
            self.$message({
              type: 'warning',
              message: '获取失败',
              duration: 1000
            });
          }
        }, function (response) {
          self.$message({
            type: 'error',
            message: '请求error',
            duration: 1000
          });
        });
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
