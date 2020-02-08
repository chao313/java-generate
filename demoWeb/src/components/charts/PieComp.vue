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
				chartItem: ''
			}
		},
		props: {
			_id: String,
			_chartData: Array,
			_colorList: Array,
			_titleText: String,
		},
		watch: {
			_chartData(val) {
				this.drawPie(val, this._id, this._colorList, this._titleText);
			},

		},
		mounted() {
			this.drawPie(this._chartData, this._id, this._colorList, this._titleText);
		},
		methods: {
			drawPie(chartData, id, colorList, titleText) {
				var chart = echarts.init(document.getElementById(id))
				var legendData = [];
				for(var k in chartData) {
					legendData.push(chartData[k].name);
					var ydata = chartData[k].data;
				}
				var scale = 1;
				var pieData = [];
				var rich = {
					yellow: {
						/*color: "#47bde3",*/
						fontSize: 14 * scale,
						align: 'center',
						padding: [3, 3]
					},
					white: {
						color: "#666",
						align: 'center',
						fontSize: 12 * scale,
						padding: [3, 3],
						fontWeight: 'bold'
					},
					blue: {
						color: '#2A3F54',
						fontSize: 14 * scale,
						align: 'center',
						padding: [3, 3]
					},
					hr: {
						width: '100%',
						height:0,
						padding: [3, 3],
						borderWidth: 0.5
					},
					per: {
						color: '#000',
						backgroundColor: '#334455',
						padding: [3,3],
						borderRadius: 5
					}
				}
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
						trigger: 'item',
						formatter: "{a} <br/>{b}: {c} ({d}%)"
					},
					legend: {
						show: false,
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
					series: [{
						name: '',
						type: 'pie',
						radius: ['35%', '55%'],
						center: ['50%', '50%'],
						color: colorList,
						label: {
							normal: {
								show: true,
								formatter: function(params, ticket, callback) {
									var total = 0;
									var percent = 0;
									chartData.forEach(function(value, index, array) {
										total += parseInt(value.value);
									});
									if(total == 0){
                    percent = 0
                  }else{
                    percent = ((params.value / total) * 100).toFixed(1);
                  }

									return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '次} {blue|' + percent + '%}';
								},
								backgroundColor: '#f7f7f7',
								borderColor: '#e1ebf9',
								borderWidth: 1,
								borderRadius: 2,
								rich: rich
							},
						},
						labelLine: {
							normal: {
								length: 30 * scale,
								length2: 0,
								lineStyle: {
									color: '#fff'
								}
							}
						},
						itemStyle: {
							normal: {
								borderWidth: 2,
								borderColor: '#fff',
							},
							emphasis: {
								borderWidth: 0,
								shadowBlur: 10,
								shadowOffsetX: 5,
								shadowColor: 'rgba(0, 0, 0, 0.4)'
							}
						},
						data: chartData
					}]
				})
        this.chartItem = chart;
			}
		}
	}
</script>
