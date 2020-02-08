<style scoped>

</style>
<template>
	<div :id="_id" class="chart"></div>
</template>

<script>
	import echarts from 'echarts';
	export default {
		data() {
			return {
				chartItem:''
			}
		},
		props: {
			_id: String,
			_titleText: String,
			_chartData: Array,
			_type: String
		},
		watch: {
			_chartData(val) {

				this.drawBar(val, this._id, this._titleText);

			},

		},
		mounted() {
			this.drawBar(this._chartData, this._id, this._titleText);
		},
		methods: {
			drawBar(chartData, id, titleText) {
				var chart = echarts.init(document.getElementById(id))
				var xAxisData = (function(xdata) {
					var array;
					for(var kk in xdata) {
						array = xdata[kk].data;
						break;
					}
					var data = [];
					for(var k in array) {
						data.push(k);
					}
					return data;
				})(chartData);
				//获取图例和数据
				var legendData = [];
				for(var k in chartData) {
					legendData.push(chartData[k].name);
					var ydata = chartData[k].data;
					var target = [];
					for(var key in ydata) {
						target.push(ydata[key]);
					}
				}
        var myColor = ['#31b5e0', '#e7e94c', '#55d4cd','#a48cc4',"#f38b83", "#19a285","#f5c06d"];
				chart.setOption({
					backgroundColor: '#fff',
					title: {
						text: titleText,
						textStyle: {
							color: '#777',
							fontFamily: 'Microsoft YaHei',
							fontWeight: 'normal',
							　　　　fontSize: 14
						},
						top: 10,
						left: 6
					},
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						show: true,
						top: 35,
						right: 10,
						data: legendData,
						itemHeight: 10,
						itemWidth: 15,
						textStyle: {
							color: '#999',
							fontSize: 10,
						}
					},
					calculable: true,
					grid: {
						left: '2%',
						right: '3%',
						bottom: '6%',
						containLabel: true
					},
					xAxis: {
						type: 'category',
						data: xAxisData,
						axisLabel: {
							interval: 0,
							textStyle: {
								color: '#999',
								fontSize: 12
							}
						},
						label: {
					        normal: {
					            show: true,
					            position: 'top',
					            formatter: '{c}'
					        }
					    },
						axisTick: {
							show: false
						},
						axisLine: {
							show: true,
							lineStyle: {
								color: '#e1ebf9',
							}
						},
						splitLine: {
							show: true,
							lineStyle: {
								color: '#dce7ef',
								type:'dashed' ,
								width:1,
							}
						},
					},
					yAxis: {
						axisLine: {
							show: true,
							lineStyle: {
								color: '#e1ebf9',
								type:'dashed'
							}
						},
						axisTick: {
							show: false
						},
						axisLabel: {
							textStyle: {
								color: '#666',
								fontSize: 10
							}
						},
						splitLine: {
							show: true,
							lineStyle: {
								color: '#dce7ef',
								type:'dashed' ,
								width:1,
							}
						},
						//max: Math.max.apply(Math,yAxisData)
					},
					series:  [{
						name: '超限数',
						type: 'bar',
						barWidth: '20%',
						xAxisIndex: 0,
						yAxisIndex: 0,
						label: {
						normal: {
							show: true,
							position: "top",
							textStyle: {
							color: "#666",
							fontSize: 16
							}
						}
						},
						itemStyle: {
						normal: {
							color: function(params) {
							var num = myColor.length;
							return myColor[params.dataIndex % num]
							},
						}
						},
						data: target,
						zlevel: 11
					}
					]

				})
				this.chartItem = chart;
			}

		}
	}

</script>
