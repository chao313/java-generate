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
          <el-button type="primary" class="el-button-search" @click="inertTableRelationByRegistId">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="app-list">
      <div class="app-tab">
        <table>
          <thead>
          <tr>
            <th>序号</th>
            <th>masterTableName</th>
            <th>slaveTableName</th>
            <th>masterRelatedField</th>
            <th>slaveRelatedField</th>
            <th>status</th>
            <th>操作</th>
          </tr>
          </thead>
          <tr>
            <th>序号</th>
            <th>主表名称</th>
            <th>从表名称</th>
            <th>主表关联字段</th>
            <th>从表关联字段</th>
            <th>状态</th>
            <th>操作</th>
          </tr>

          <tbody>
          <tr v-for="(info,index) in dataList">
            <td>{{index+1}}</td>
            <td>{{info.masterTableName}}</td>
            <td>{{info.slaveTableName}}</td>
            <td>{{info.masterRelatedField}}</td>
            <td>{{info.slaveRelatedField}}</td>
            <td>{{info.status}}</td>
            <td>
              <span @click="copyRecord(info.id)">复制</span>
              <span @click="routerTotableRelationEdit(info.id)">编辑</span>
              <span @click="deleteTableRelationById(info.id)">删除</span>
            </td>

          </tr>
          </tbody>
        </table>
        <p v-show="total == 0" class="no-data-tip">没有找到相关数据！</p>
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
        registId: ''
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
      console.log(registId)
      this.registId = registId;
      self.getTableRelationsByRegistId(registId)
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
      getTableRelationsByRegistId(registId) {
        let self = this;
        self.$http.get(self.api.getTableRelationsByRegistId, {
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
      inertTableRelationByRegistId() {
        let self = this;
        const registId = this.$route.query && this.$route.query.registId;
        self.$http.get(self.api.inertTableRelationByRegistId, {
          params: {
            registId: registId
          }
        }, function (response) {
          self.getTableRelationsByRegistId(registId)
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      routerTotableRelationEdit(id) {
        //跳转携带参数
        window.open("#/tableRelationEdit" + "?id=" + id, '_self');
      },
      deleteTableRelationById(id) {
        var self = this;
        this.$confirm('是否删除该条数据？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true
        }).then(() => {
          self.$http.get(self.api.deleteTableRelationById, {
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
              self.getTableRelationsByRegistId(self.registId)
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
      copyRecord(id) {
        let self = this;
        self.$http.get(self.api.copyRecord, {
          params: {
            id: id,
            type: "tableRelation"
          }
        }, function (response) {
          if (response.content == true) {
            self.$message({
              type: 'success',
              message: '复制成功',
              duration: 1000
            });
            self.getTableRelationsByRegistId(self.registId)
          } else {
            self.$message({
              type: 'warning',
              message: '复制失败' + response.msg,
              duration: 1000
            });
          }

        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      }
    }

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
