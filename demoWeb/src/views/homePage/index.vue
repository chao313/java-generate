<!--<template>-->
<!--<div class="app-container">-->
<!--<div class="mt20">-->
<!--<el-form :inline="true" size="mini">-->
<!--<el-form-item label="策略ID选择">-->
<!--<el-select v-model="policyId" filterable clearable>-->
<!--<el-option v-for="(item,index) in policyIdList" :key="item.policyId" :label="item.policyId"-->
<!--:value="item.policyId">-->
<!--</el-option>-->
<!--</el-select>-->
<!--</el-form-item>-->
<!--<el-form-item label="状态">-->
<!--<el-input v-model="plateNo"></el-input>-->
<!--</el-form-item>-->
<!--<el-form-item>-->
<!--<el-button type="primary" class="el-button-search" @click="searchEvent">查询</el-button>-->
<!--</el-form-item>-->
<!--</el-form>-->
<!--</div>-->
<!--<div class="app-list">-->
<!--<div class="app-tab">-->
<!--<table>-->
<!--<thead>-->
<!--<tr>-->
<!--<th>序号</th>-->
<!--<th>执法机构</th>-->
<!--<th>车牌号码</th>-->
<!--<th>客车类型</th>-->
<!--<th>入口站</th>-->
<!--<th>入口时间</th>-->
<!--<th>入口车道</th>-->
<!--<th>出口站</th>-->
<!--<th>出口时间</th>-->
<!--<th>出口车道</th>-->
<!--<th>是否处理</th>-->
<!--<th>操作</th>-->
<!--</tr>-->
<!--</thead>-->

<!--<tbody>-->
<!--<tr v-for="(info,index) in dataList">-->
<!--<td>{{index+1}}</td>-->
<!--<td>{{info.roadOrgName}}</td>-->
<!--<td>{{info.plateNo}}</td>-->
<!--<td v-if="info.coachType == 2">客2</td>-->
<!--<td v-else-if="info.coachType == 3">客3</td>-->
<!--<td v-else>客4</td>-->
<!--<td>{{info.entryStationName}}</td>-->
<!--<td>{{info.entryTime}}</td>-->
<!--<td>{{info.entryLane}}</td>-->
<!--<td>{{info.exitStationName}}</td>-->
<!--<td>{{info.exitTime}}</td>-->
<!--<td>{{info.exitLane}}</td>-->
<!--<td v-if="info.isDeal == 0">未处理</td>-->
<!--<td v-else>已处理</td>-->
<!--<td>-->
<!--<span @click="previewResult(info.id)">预览 </span>-->
<!--<span v-if="info.isDeal == 0" @click="dogetuck(info.id)">处理 </span>-->
<!--</td>-->

<!--</tr>-->
<!--</tbody>-->
<!--</table>-->
<!--<p v-show="total == 0" class="no-data-tip">没有找到相关数据！</p>-->
<!--</div>-->
<!--<div class="mt20" v-show="total > 0">-->
<!--<el-pagination background @current-change="handleCurrentChange" :current-page.sync="currentPage"-->
<!--:page-size="pageSize" layout="total,prev, pager, next" :total="total">-->
<!--</el-pagination>-->
<!--</div>-->
<!--</div>-->
<!--<el-dialog title="问题客车详情" :visible.sync="viewDialogVisible" width="1000px" :before-close="handleClose">-->
<!--<section>-->
<!--<div class="video-detail">-->
<!--<p><label>执法机构：</label><span>{{resultDetail.roadOrgName}}</span></p>-->
<!--<p><label>车牌号码：</label><span>{{resultDetail.plateNo}}</span></p>-->
<!--<p><label>客车类型：</label>-->
<!--<span v-if="resultDetail.coachType == '2'">客2</span>-->
<!--<span v-else-if="resultDetail.coachType == '3'">客3</span>-->
<!--<span v-else>客4</span>-->
<!--</p>-->
<!--&lt;!&ndash; <p><label>路线类型：</label>-->
<!--<span v-if="resultDetail.routeType == '0'">出入口</span>-->
<!--<span v-else-if="resultDetail.routeType == '1'">入口</span>-->
<!--<span v-else>出口</span>-->
<!--</p> &ndash;&gt;-->
<!--<p><label>收费站：</label><span>{{resultDetail.tollgateNameList}}</span></p>-->
<!--<p><label>入口站：</label><span>{{resultDetail.entryStationName  }}</span></p>-->
<!--<p><label>入口时间：</label><span>{{resultDetail.entryTime}}</span></p>-->
<!--<p><label>入口车道：</label><span>{{resultDetail.entryLane}}</span></p>-->
<!--<p><label>出口站：</label><span>{{resultDetail.exitStationName}}</span></p>-->
<!--<p><label>出口时间：</label><span>{{resultDetail.exitTime}}</span></p>-->
<!--<p><label>出口车道：</label><span>{{resultDetail.exitLane}}</span></p>-->
<!--<p><label>收费金额：</label><span>{{resultDetail.amount}}元</span></p>-->
<!--<p><label>视频开始时间：</label><span>{{resultDetail.videoBeginTime}}</span></p>-->
<!--<p><label>视频结束时间：</label><span>{{resultDetail.videoEndTime}}</span></p>-->
<!--</div>-->

<!--</section>-->
<!--<span slot="footer" class="dialog-footer">-->

<!--<el-button type="primary" @click="playVideo"> 播放  </el-button>-->

<!--<el-button type="primary" @click="exportReport">导出告知函</el-button>-->
<!--<el-button type="primary" @click="exportVehicleInfo">导出明细</el-button>-->
<!--<el-button @click="handleClose()">取 消</el-button>-->
<!--</span>-->
<!--</el-dialog>-->
<!--</div>-->
<!--</template>-->

<!--<script>-->
<!--import {getPreDay} from '@/utils/index.js'-->

<!--export default {-->
<!--data() {-->
<!--return {-->
<!--policyIdList:[],-->
<!--policyId:'',-->
<!--dataList: [],-->
<!--total: -1,-->
<!--currentPage: 1,-->
<!--pageSize: 10,-->
<!--start: '',-->
<!--end: '',-->
<!--plateNo: '',-->
<!--exitStation: '',-->
<!--exitStationList: [],-->
<!--resultDetail: {},-->
<!--viewDialogVisible: false,-->
<!--startTime: '',-->
<!--endTime: '',-->
<!--timeRange: []-->
<!--}-->
<!--},-->
<!--mounted() {-->
<!--let self = this;-->
<!--//self.InitPage();-->
<!--//self.DoLogin();-->
<!--},-->
<!--created() {-->
<!--let self = this;-->

<!--//出口收费站-->
<!--self.$http.get(self.api.getAreaList, {-->
<!--params: {-->
<!--start: 1,-->
<!--end: 200,-->
<!--'roadOrg.roadOrgId': '',-->
<!--}-->
<!--}, function (response) {-->
<!--self.exitStationList = response;-->
<!--}, function (response) {-->
<!--//失败回调-->
<!--})-->
<!--self.getPolicyIdList();-->
<!--self.getDataList();-->
<!--self.getListCount();-->
<!--},-->
<!--watch: {-->
<!--timeRange: {-->
<!--handler: function (newVal, oldVa) {-->
<!--var self = this;-->
<!--if (newVal != null) {-->
<!--self.startTime = self.timeRange.length > 0 ? self.timeRange[0] : '';-->
<!--self.endTime = self.timeRange.length > 0 ? self.timeRange[1] : '';-->
<!--} else {-->
<!--self.startTime = '';-->
<!--self.endTime = '';-->
<!--}-->

<!--},-->
<!--},-->
<!--},-->
<!--methods: {-->
<!--/* 获得十条数据信息  */-->
<!--getPolicyIdList() {-->
<!--let self = this;-->
<!--self.start = self.pageSize * (self.currentPage - 1) + 1;-->
<!--self.end = self.currentPage * self.pageSize;-->
<!--self.$http.get(self.api.getAllTConfigRegistrations, {-->
<!--params: {-->
<!--}-->
<!--}, function (response) {-->
<!--self.policyIdList = response.content;-->
<!--}, function (response) {-->
<!--//失败回调-->
<!--console.error("请求发生异常")-->
<!--})-->
<!--},-->
<!--/* 获得十条数据信息  */-->
<!--getDataList() {-->
<!--let self = this;-->
<!--self.start = self.pageSize * (self.currentPage - 1) + 1;-->
<!--self.end = self.currentPage * self.pageSize;-->
<!--self.$http.get(self.api.getCoachProblemList, {-->
<!--params: {-->
<!--start: self.start,-->
<!--end: self.end,-->
<!--startTime: self.startTime,-->
<!--endTime: self.endTime,-->
<!--exitStation: self.exitStation,-->
<!--plateNo: self.plateNo-->
<!--}-->
<!--}, function (response) {-->
<!--self.dataList = response;-->
<!--}, function (response) {-->
<!--//失败回调-->
<!--})-->
<!--},-->
<!--/* 查询总条数  */-->
<!--getListCount() {-->
<!--let self = this;-->
<!--self.$http.get(self.api.getCoachProblemCount, {-->
<!--params: {-->
<!--startTime: self.startTime,-->
<!--endTime: self.endTime,-->
<!--exitStation: self.exitStation,-->
<!--plateNo: self.plateNo-->
<!--}-->
<!--}, function (response) {-->
<!--self.dataCount = response;-->
<!--self.total = parseInt(response.count);-->
<!--}, function (response) {-->
<!--})-->
<!--},-->
<!--handleCurrentChange(val) {-->
<!--this.currentPage = val;-->
<!--this.getDataList();-->
<!--},-->
<!--searchEvent() {-->
<!--this.currentPage = 1;-->
<!--this.getDataList()-->
<!--this.getListCount();-->
<!--},-->
<!--previewResult(id) {-->
<!--let self = this;-->
<!--self.$http.get(self.api.getCoachProblemInfo, {-->
<!--params: {-->
<!--id: id,-->
<!--}-->
<!--}, function (response) {-->
<!--self.resultDetail = response;-->
<!--self.viewDialogVisible = true;-->
<!--setTimeout(function () {-->
<!--self.$refs.videoPre.initVideoSource();-->
<!--}, 300);-->
<!--}, function (response) {-->

<!--})-->
<!--},-->
<!--dogetuck(id) {-->
<!--let self = this;-->
<!--self.$confirm('是否继续处理?', '提示', {-->
<!--confirmButtonText: '确定',-->
<!--cancelButtonText: '取消',-->
<!--type: 'warning'-->
<!--}).then(() => {-->
<!--self.$http.get(self.api.dealCoach, {-->
<!--params: {-->
<!--id: id,-->
<!--}-->
<!--}, function (response) {-->
<!--self.getDataList();-->
<!--self.getListCount();-->
<!--self.$message({-->
<!--type: 'success',-->
<!--message: '处理成功!'-->
<!--});-->
<!--}, function (response) {-->
<!--})-->
<!--}).catch(() => {-->

<!--});-->


<!--},-->
<!--playVideo() {-->
<!--this.$refs.videoPre.playVideo();-->
<!--},-->
<!--handleClose() {-->
<!--this.viewDialogVisible = false;-->
<!--this.$refs.videoPre.DoStopPlayRecord(26);-->
<!--this.$refs.videoPre.DoStopPlayRecord(27);-->

<!--},-->
<!--exportFiel() {-->
<!--let self = this-->
<!--let url = self.api.exportCoachFile + "?coachId=" + self.resultDetail.coachId;-->
<!--window.open(url)-->
<!--},-->
<!--exportReport() {-->
<!--let self = this-->
<!--let url = self.api.exportCoach + "?coachId=" + self.resultDetail.coachId;-->
<!--window.open(url)-->
<!--},-->
<!--exportVehicleInfo() {-->
<!--let self = this-->
<!--let url = self.api.exportCoachVehicle + "?coachId=" + self.resultDetail.coachId;-->
<!--window.open(url)-->
<!--},-->
<!--},-->

<!--}-->
<!--</script>-->

<!--<style rel="stylesheet/scss" lang="scss">-->
<!--.video-detail {-->
<!--p {-->
<!--display: inline-block;-->
<!--width: 40%;-->
<!--vertical-align: top;-->
<!--}-->
<!--label {-->
<!--width: 120px;-->
<!--display: inline-block;-->
<!--}-->
<!--span {-->
<!--color: #888;-->
<!--}-->
<!--}-->
<!--</style>-->
