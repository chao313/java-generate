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
          <el-button type="primary" class="el-button-search" @click="insertZipManagerByRegistId">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="app-list">
      <div class="app-tab">
        <table>
          <thead>
          <tr>
            <th>id</th>
            <th>zipName</th>
            <th>fileNames</th>
            <!--<th>zipContent</th>-->
            <th>uploadTime</th>
            <th>originalResult</th>
            <th>originalTime</th>
            <th>bussResult</th>
            <th>bussTime</th>
            <th>status</th>
            <th>操作</th>
          </tr>
          </thead>
          <tr>
            <th>序号</th>
            <th>zip文件名称</th>
            <th>zip中文件的名称</th>
            <!--<th>文件内容</th>-->
            <th>文件上传时间</th>
            <th>原始结构化执行结果</th>
            <th>原始结构化执行时间</th>
            <th>业务结构化执行结果</th>
            <th>业务结构化执行时间</th>
            <th>文件状态</th>
            <th>操作</th>
          </tr>

          <tbody>
          <tr v-for="(info,index) in dataList">
            <td>{{index+1}}</td>
            <td>{{info.zipName}}</td>
            <td>{{info.fileNames}}</td>
            <!--<td>{{info.zipContent}}</td>-->
            <td>{{info.uploadTime}}</td>
            <td>{{info.originalResult}}</td>
            <td>{{info.originalTime}}</td>
            <td>{{info.bussResult}}</td>
            <td>{{info.bussTime}}</td>
            <td>{{info.status}}</td>
            <td>
              <span @click="routerToZipManagerEdit(info.id)">编辑</span>
              <span @click="deleteZipManagerById(info.id)">删除</span>
              <span @click="routerToExtractorConfEdit(info.id)">下载</span>
              <span @click="getOriginalResult(info.id)">原始结构化</span>
              <span @click="getBussResult(info.id)">业务结构化</span>
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
      self.getZipManagersByRegistId(registId)
    },
    watch: {
      timeRange: {
        handler: function (newVal, oldVa) {
          var self = this;
          if (newVal != null) {
            self.startTime = self.timeRange.length > 0 ? self.timeRange[0] : '';
            self.endTime = self.timeRange.length > 0 ? self.timeRange[1] : '';
          } else {
            self.startTime = '';
            self.endTime = '';
          }

        },
      },
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
        self.getZipManagersByRegistId(registId)
        self.$http.get(self.api.insertZipManagerByRegistId, {
          params: {
            registId: registId
          }
        }, function (response) {
          self.getZipManagersByRegistId(registId)
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      getZipManagersByRegistId(registId) {
        let self = this;
        self.$http.get(self.api.getZipManagersByRegistId, {
          params: {
            registId: registId
          }
        }, function (response) {
          self.dataList = response.content;
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      getOriginalResult(id) {
        let self = this;
        self.jsonData = '';
        self.$http.get(self.api.getOriginalResult, {
          params: {
            zipId: id
          }
        }, function (response) {
          self.jsonData = response.content;
          // self.jsonData = JSON.stringify(response.content);
          // self.jsonData = JSON.parse(JSON.parse(self.jsonData));
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
      getBussResult(id) {
        let self = this;
        self.jsonData = '';
        self.$http.get(self.api.getBussResult, {
          params: {
            zipId: id
          }
        }, function (response) {
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
        window.open("#/zipManagerEdit" + "?id=" + id, '_self');
      },
      deleteZipManagerById(id) {
        var self = this;
        this.$confirm('是否删除该条数据？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true
        }).then(() => {
          self.$http.get(self.api.deleteZipManagerById, {
            params: {
              id: id
            }
          }, function (response) {
            if (response.content == true) {
              self.$message({
                type: 'success',
                message: '删除成功',
                duration: 1000
              });
              self.getZipManagersByRegistId(self.registId)
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
