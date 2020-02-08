<template>
  <div class="app-container">
    <div class="mt20">
      <el-form :inline="true" size="mini">
        <el-form-item label="字段选择">
          <el-select v-model="policyId" filterable clearable>
            <el-option v-for="(item,index) in policyIdList" :key="item.policyId" :label="item.policyId"
                       :value="item.policyId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="status" filterable clearable>
            <el-option v-for="(item,index) in statuses" :key="item.value" :label="item.title"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="el-button-search" @click="searchEvent">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="el-button-search" @click="routerToLocationResourceaUpFile">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="app-list">
      <div class="app-tab">
        <table>
          <thead>
          <tr>
            <th>id</th>
            <th>msgId</th>
            <th>zipName</th>
            <th>zipTime</th>
            <th>originalResultName</th>
            <th>originalResultTime</th>
            <th>bussResultName</th>
            <th>bussResultTime</th>
            <th>操作</th>
          </tr>
          </thead>
          <tr>
            <th>序号</th>
            <th>msgId</th>
            <th>zip文件名称</th>
            <th>文件上传时间</th>
            <th>原始结构化结果名称</th>
            <th>原始结构化时间</th>
            <th>业务结构化结果名称</th>
            <th>业务结构化时间</th>
            <th>操作</th>
          </tr>

          <tbody>
          <tr v-for="(info,index) in dataList">
            <td>{{index+1}}</td>
            <td>{{info.msgId}}</td>
            <td>{{info.zipName}}</td>
            <td>{{info.zipTime}}</td>
            <td>{{info.originalResultName}}</td>
            <td>{{info.originalResultTime}}</td>
            <td>{{info.bussResultName}}</td>
            <td>{{info.bussResultTime}}</td>
            <td>
              <span @click="getLocationOriginalAndBussResult(info.msgId)">查看</span>
              <span @click="deleteFileByMsgIdAndRegistId(info.msgId)">删除</span>
              <span @click="downloadZip(info.msgId)">下载</span>
              <span @click="getLocationOriginalResult(info.msgId)">原始结构化</span>
              <span @click="getLocationBussResult(info.msgId)">业务结构化</span>
            </td>

          </tr>
          </tbody>
        </table>
        <p v-show="total == 0" class="no-data-tip">没有找到相关数据！</p>
        <div>
          <pre>{{jsonData}}</pre>
        </div>
      </div>
      <div class="mt20" v-show="total > 0">
        <el-pagination background @current-change="handleCurrentChange" :current-page.sync="currentPage"
                       :page-size="pageSize" layout="total,prev, pager, next" :total="total">
        </el-pagination>
      </div>

    </div>

  </div>

</template>

<script>
  import {getPreDay} from '@/utils/index.js'

  export default {
    data() {
      return {
        policyIdList: [],
        policyId: '',
        statuses: [{value: 1, title: '生效'}, {value: 2, title: '失效'}, {value: null, title: '全部'}],
        status: '',
        dataList: [],
        total: -1,
        currentPage: 1,
        pageSize: 10,
        start: '',
        end: '',
        plateNo: '',
        exitStation: '',
        exitStationList: [],
        resultDetail: {},
        viewDialogVisible: false,
        startTime: '',
        endTime: '',
        timeRange: [],
        registId: '',
        jsonData: ''
      }
    },
    mounted() {
      let self = this;
    },
    created() {
      let self = this;
      /**
       * 1.接收外界参数
       */
      const registId = this.$route.query && this.$route.query.registId;
      self.registId = registId;
      self.getLocationResourceVosByPolicyId(registId)
    },
    methods: {
      /* 获得十条数据信息  */
      getAllPolicyIdList() {
        let self = this;
        self.$http.get(self.api.getAllTConfigRegistrations, {
          params: {}
        }, function (response) {
          self.dataList = response.content;
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      insertZipManagerByRegistId() {
        let self = this;
        const registId = this.$route.query && this.$route.query.registId;
        self.getLocationZipManagersByRegistId(registId)
        self.$http.get(self.api.insertZipManagerByRegistId, {
          params: {
            registId: registId
          }
        }, function (response) {
          self.getLocationZipManagersByRegistId(registId)
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      getLocationResourceVosByPolicyId(msgId) {
        let self = this;
        self.$http.get(self.api.getLocationResourceVosByPolicyId, {
          params: {
            registId: self.registId,
            msgId: msgId
          }
        }, function (response) {
          self.dataList = response.content;
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      getLocationOriginalResult(id) {
        let self = this;
        self.jsonData = '';
        self.$http.get(self.api.getLocationOriginalResult, {
          params: {
            registId: self.registId,
            msgId: id
          }
        }, function (response) {
          //加载更新后的数据
          self.getLocationResourceVosByPolicyId(self.registId);
          self.jsonData = response.content;
          self.$message({
            type: 'success',
            message: '操作成功',
            duration: 1000
          });
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      getLocationOriginalAndBussResult(id) {
        let self = this;
        self.jsonData = '';
        self.$http.get(self.api.getLocationOriginalAndBussResult, {
          params: {
            registId: self.registId,
            msgId: id
          }
        }, function (response) {
          //加载更新后的数据
          self.jsonData = response.content;
          self.$message({
            type: 'success',
            message: '操作成功',
            duration: 1000
          });
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      downloadZip(msgId) {
        let self = this;
        window.open(self.api.downloadZip + "?registId=" + self.registId + "&msgId=" + msgId, '_self')
      }, getLocationBussResult(id) {
        let self = this;
        self.jsonData = '';
        self.$http.get(self.api.getLocationBussResult, {
          params: {
            registId: self.registId,
            msgId: id
          }
        }, function (response) {
          //加载更新后的数据
          self.getLocationResourceVosByPolicyId(self.registId);
          self.jsonData = response.content;
          self.$message({
            type: 'success',
            message: '操作成功',
            duration: 1000
          });
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      routerToZipManagerEdit(id) {
        //跳转携带参数
        window.open("#/locationZipManagerEdit" + "?id=" + id, '_self');
        // this.$router.push({name: 'locationZipManagerEdit', params: {id: id}});
      },
      deleteFileByMsgIdAndRegistId(msgId) {
        var self = this;
        this.$confirm('是否删除该条数据？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true
        }).then(() => {
          self.$http.get(self.api.deleteFileByMsgIdAndRegistId, {
            params: {
              msgId: msgId,
              registId: self.registId
            }
          }, function (response) {
            if (response.content == true) {
              //加载更新后的数据
              self.getLocationResourceVosByPolicyId(self.registId);
              self.$message({
                type: 'success',
                message: '删除成功',
                duration: 1000
              });
            } else {
              self.$message({
                type: 'warning',
                message: '删除失败',
                duration: 1000
              });
            }
          }, function (response) {
            //失败回调
          })

        })
      }
      ,
      searchEvent() {
        this.currentPage = 1;
        this.getConfigRegistrationsByPolicyId();
      },
      routerToLocationResourceaUpFile() {
        let self = this;
        //跳转携带参数
        window.open("#/locationZipManagerEdit" + "?registId=" + registId, '_self');
        // this.$router.push({name: 'locationZipManagerEdit', params: {registId: self.registId}});
      }
    },

  }
</script>

<style rel="stylesheet/scss" lang="scss">
  .video-detail {
    p {
      display: inline-block;
      width: 40%;
      vertical-align: top;
    }
    label {
      width: 120px;
      display: inline-block;
    }
    span {
      color: #888;
    }
  }
</style>
