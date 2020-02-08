<template>
  <div class="app-form mt20 ml40">
    <h5 class="form-tit">extractorConf编辑</h5>
    <section>
      <el-form :model="postForm" :rules="rules" ref="postForm" label-width="180px" size="mini" :inline-message="true">
        <div class="mt30 ml15">
          <!--<el-form-item label="文件名称：">-->
          <!--<el-input v-model="postForm.fileNameRegex" maxlength="100"></el-input>-->
          <!--</el-form-item>-->
          <el-form-item label="文件名称：">
            <el-select v-model="postForm.fileNameRegex" filterable clearable>
              <el-option v-for="(item,index) in fileNames" :label="item"
                         :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="字段名称：">
            <el-input v-model="postForm.fieldName" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="等级：" prop="cameraNo1">
            <el-input v-model="postForm.level" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="优先级：">
            <el-input v-model="postForm.superior" maxlength="100"></el-input>
          </el-form-item>
          <el-form-item label="输出类型：">
            <el-select v-model="postForm.outputType" clearable filterable>
              <el-option label="TOSTRING" value="TOSTRING"></el-option>
              <el-option label="TEXT" value="TEXT"></el-option>
              <el-option label="OWNTEXT" value="OWNTEXT"></el-option>
              <el-option label="HTML" value="HTML"></el-option>
              <el-option label="SIMPLETABLE" value="SIMPLETABLE"></el-option>
              <el-option label="COMOLEXTABLE" value="COMOLEXTABLE"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="提取类型：">
            <el-select v-model="postForm.extractType" clearable filterable>
              <el-option label="SELECTOR" value="SELECTOR"></el-option>
              <el-option label="TRAVERSALSELECTOR" value="TRAVERSALSELECTOR"></el-option>
              <el-option label="XPATH" value="XPATH"></el-option>
              <el-option label="REGEX_TEXT" value="REGEX_TEXT"></el-option>
              <el-option label="REGEX_HTML" value="REGEX_HTML"></el-option>
              <el-option label="FIXED" value="FIXED"></el-option>
              <el-option label="PDF" value="PDF"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="提取规则：">
            <el-input v-if="postForm.extractType=='REGEX_TEXT' || postForm.extractType=='REGEX_HTML'"
                      v-model="postForm.extractValue" maxlength="100" :disabled=true></el-input>
            <el-input v-else
                      v-model="postForm.extractValue" maxlength="100"></el-input>
          </el-form-item>
          <el-row v-if="postForm.extractType=='REGEX_TEXT' || postForm.extractType=='REGEX_HTML'">
            <el-col :span="12">
              <el-form-item label="辅助生成工具Regex">
                <el-input v-model="extractValuePluginRegex" maxlength="100"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="1">
              &nbsp;
            </el-col>
            <el-col :span="4">
              <el-form-item label="group" label-width="50px">
                <el-input v-model="extractValuePluginGroup"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="1">
              <el-form-item>
                <el-button @click="cancelPluginUtil()">取消</el-button>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="清洗类型：">
            <el-select v-model="postForm.washType" clearable filterable>
              <el-option label="SELECTOR" value="SELECTOR"></el-option>
              <el-option label="XPATH" value="XPATH"></el-option>
              <el-option label="REGEX_TEXT" value="REGEX_TEXT"></el-option>
              <el-option label="REGEX_HTML" value="REGEX_HTML"></el-option>
              <el-option label="CLEANATTRIBUTE" value="CLEANATTRIBUTE"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="清洗值：">
            <el-input v-model="postForm.washValue" maxlength="100"></el-input>
          </el-form-item>

          <el-form-item label="是否单值" class="mt20">
            <el-radio-group v-model="postForm.isSingle">
              <el-radio v-for="(info,index)  in status" :key="info.value" :label="info.value">{{info.title}}</el-radio>
            </el-radio-group>
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
      <el-button @click="batchOriginalResult()">批量测试</el-button>
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
          fieldName: '',
          level: '',
          superior: '',
          outputType: '',
          extractType: '',
          extractValue: '',
          washType: '',
          washValue: '',
          isSingle: '',
          registerId: '',
          status: ''
        },
        id: '',
        batchJsonData: '',
        status: [{value: 'TRUE', title: 'TRUE'}, {value: 'FALSE', title: 'FALSE'}],
        extractValuePluginRegex: '',
        extractValuePluginGroup: '',
        extractValueOld: '',
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
      self.getExtractorConfById(id);
      self.getFileNamesByExtractorConfId(id);
    },
    watch: {
      extractValuePluginRegex: {
        handler: function (newVal, oldVa) {
          var self = this;
          self.postForm.extractValue = "{" + JSON.stringify(self.extractValuePluginRegex) + ":" + JSON.stringify(self.extractValuePluginGroup) + "}";
        }
      },
      extractValuePluginGroup: {
        handler: function (newVal, oldVa) {
          var self = this;
          self.postForm.extractValue = "{" + JSON.stringify(self.extractValuePluginRegex) + ":" + JSON.stringify(self.extractValuePluginGroup) + "}";
        }
      }
    }
    ,
    methods: {
      getExtractorConfById(id) {
        let self = this;
        self.$http.get(self.api.getExtractorConfById, {
          params: {
            id: id
          }
        }, function (response) {
          self.postForm = response.content;
          self.extractValueOld = self.postForm.extractValue;//留作取消操作
          let extractValueJson = JSON.parse(self.postForm.extractValue);//将json字符串转换成json对象
          for (var key in extractValueJson) {
            self.extractValuePluginRegex = key;
            self.extractValuePluginGroup = extractValueJson[key];
          }
        }, function (response) {
          //失败回调
        });
      },
      getFileNamesByExtractorConfId(id) {
        let self = this;
        self.$http.get(self.api.getFileNamesByExtractorConfId, {
          params: {
            extractorConfId: id
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
      ,
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
      }
      ,
      resetForm(formName) {
        let self = this;
        window.open("#/extractorConfList" + "?registId=" + self.postForm.registerId, '_self');
      }
      ,
      back() {
        let self = this;
        window.open("#/extractorConfList" + "?registId=" + self.postForm.registerId, '_self');
      }
      ,
      cancelPluginUtil() {
        let self = this;
        self.postForm.extractValue = self.extractValueOld;
      }
      ,
      batchOriginalResult() {
        let self = this;
        self.$http.get(self.api.batchOriginalResult, {
          params: {
            extractorConfId: self.id
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
            type: 'error',
            message: '请求发生异常',
            duration: 500,
          });
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">

</style>
