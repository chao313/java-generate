<template>
  <div class="app-container">
    <div class="mt20">
      <el-form :inline="true" size="mini">
        <el-form-item label="策略ID选择">
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
      </el-form>
    </div>
    <div class="app-list">
      <div class="app-tab">
        <table>
          <thead>
          <tr>
            <th>序号</th>
            <th>policyId</th>
            <th>extractorConf</th>
            <th>contentSignConf</th>
            <th>keyWordConf</th>
            <th>ruleConf</th>
            <th>jsonExtractorConf</th>
            <th>fieldExtractorConf</th>
            <th>tableRelationConf</th>
            <th>relativePath</th>
            <th>status</th>
            <!--<th>createTime</th>-->
            <!--<th>updateTime</th>-->
            <th>操作</th>
          </tr>
          </thead>
          <tr>
            <th>序号</th>
            <th>策略id</th>
            <th>原始抽取配置</th>
            <th>正文标注配置</th>
            <th>关键字配置</th>
            <th>业务映射</th>
            <th>json抽取配置</th>
            <th>业务表表结构映射配置</th>
            <th>业务表父子关系配置</th>
            <th>真实位置在config下</th>
            <th>状态</th>
            <!--<th>创建时间</th>-->
            <!--<th>更新时间</th>-->
            <th>操作</th>
          </tr>

          <tbody>
          <tr v-for="(info,index) in dataList">
            <td>{{index+1}}</td>
            <td>{{info.policyId}}</td>
            <td>{{info.extractorConf}}</td>
            <td>{{info.contentSignConf}}</td>
            <td>{{info.keyWordConf}}</td>
            <td>{{info.ruleConf}}</td>
            <td>{{info.jsonExtractorConf}}</td>
            <td>{{info.fieldExtractorConf}}</td>
            <td>{{info.tableRelationConf}}</td>
            <td>{{info.relativePath}}</td>
            <td>{{info.status}}</td>
            <!--<td>{{info.createTime}}</td>-->
            <!--<td>{{info.updateTime}}</td>-->
            <td>
              <span @click="download(info.id)">download</span>
              <span @click="routerToZipManager(info.id)">zipManager</span>
              <span @click="routerToLocationZipManager(info.id)">LocalZipManager</span>
              <span @click="routerToConfigRegistrations(info.id)">registration</span>
              <br>
              <span @click="routerToProperties(info.id)">properties</span>
              <span @click="routerToKeyWordConf(info.id)">KeyWordConf</span>
              <span @click="routerToOriginalConf(info.id)">originalConf</span>
              <span @click="routerToExtractorList(info.id)">extractorConf</span>
              <br>
              <span @click="routerToStructConfEdit(info.id)">structConf</span>
              <span @click="routerToTableRelationList(info.id)">tableRelation</span>
              <span @click="routerToFieldExtractorConfList(info.id)">fieldExtractorConf</span>
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
        timeRange: []
      }
    },
    mounted() {
      let self = this;
      //self.InitPage();
      //self.DoLogin();
    },
    created() {
      let self = this;
      self.getAllPolicyIdList();
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
      getConfigRegistrationsByPolicyId() {
        let self = this;
        self.$http.get(self.api.getConfigRegistrationsByPolicyId, {
          params: {
            policyId: self.policyId,
            status: self.status
          }
        }, function (response) {
          self.dataList = response.content;
        }, function (response) {
          //失败回调
          self.$message({type: 'error', message: '请求发生异常', duration: 500,});
        })
      },
      download(registId) {
        let self = this;
        window.open(self.api.generateAndFileDownLoad + "?registId=" + registId, '_self')
      },
      routerToZipManager(registId) {
        //跳转携带参数
        window.open("#/zipManagerList" + "?registId=" + registId, '_self');
      },
      routerToLocationZipManager(registId) {
        //跳转携带参数
        window.open("#/locationZipManagerList" + "?registId=" + registId, '_self')
      },
      routerToConfigRegistrations(registId) {
        //跳转携带参数
        window.open("#/configRegistrationsEdit" + "?registId=" + registId, '_self')
      }
      ,
      routerToProperties(registId) {
        //跳转携带参数
        window.open("#/propertiesEdit" + "?registId=" + registId, '_self');
      }
      , routerToKeyWordConf(registId) {
        //跳转携带参数
        window.open("#/keyWordConfList" + "?registId=" + registId, '_self');
      }
      ,
      routerToOriginalConf(registId) {
        //跳转携带参数
        window.open("#/originalConfEdit" + "?registId=" + registId, '_self');
      },
      routerToExtractorList(registId) {
        //跳转携带参数
        window.open("#/extractorConfList" + "?registId=" + registId, '_self');
      },
      routerToStructConfEdit(registId) {
        //跳转携带参数
        window.open("#/structConfEdit" + "?registId=" + registId, '_self');
      },
      routerToTableRelationList(registId) {
        //跳转携带参数
        window.open("#/tableRelationList" + "?registId=" + registId, '_self');
      },
      routerToFieldExtractorConfList(registId) {
        //跳转携带参数
        window.open("#/fieldExtractorConfList" + "?registId=" + registId, '_self');
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
