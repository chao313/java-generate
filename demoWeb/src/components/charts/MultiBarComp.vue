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
          
                var  se = [];
                var leng = ["总量","30%以下","30%-50%","50%-100%","100%以上"];
	
                
                var xtest = [];
                for (let index = 0; index < chartData.length; index++) {
                    xtest.push(chartData[index].name);
                }
                
                for (let index = 0; index <5; index++) {
                    var data =[];
                    for (let j = 0; j < chartData.length; j++) {
                        if (index==0) {
                            data.push(chartData[j].count);
                        }
                        if (index==1) {
                            data.push(chartData[j].downThirty);
                        }
                        if (index==2) {
                            data.push(chartData[j].thirtyToFifty);
                        }
                        if (index==3) {
                            data.push(chartData[j].fiftyToHundred);
                        }
                        if (index==4) {
                            data.push(chartData[j].hundred);
                        }
                        
                    }
                    se.push(
                        {
                            name: leng[index],
                            type: 'bar',
                            barGap: 0,
                            data: data
                        }
                    )
                }
        var myColor = ['#31b5e0', '#e7e94c', '#ff0000','#a48cc4',"#f38b83", "#19a285","#f5c06d"];
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
                    color:myColor,
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						show: true,
						top: 35,
						right: 10,
						data: leng,
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
						data: xtest,
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
					series:  se
                })
				this.chartItem = chart;
			}

		}
	}

</script>
