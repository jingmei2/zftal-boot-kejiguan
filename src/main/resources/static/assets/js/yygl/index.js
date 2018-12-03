jQuery(function($) {
	
	
	$("#navbar-content").find(">li").removeClass("active");
	$("#navbar-content").find(">li:eq(1)").addClass("active");
	
	// 安全预警echarts实例
	
	/*
	var myChart = echarts.init(document.getElementById('first-echart'));
	option = {
		tooltip: {
			trigger: 'axis'
		},
		legend: {
			data: ['日', '月', '年']
		},
		grid: {
			left: '2%',
			right: '2%',
			bottom: '2%',
			containLabel: true
		},
		toolbox: {
			feature: {
				saveAsImage: {}
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: ['1', '2', '3', '4', '5']
		},
		yAxis: [{
			type: 'value',
			axisLabel: {
				show: true,
				interval: 'auto',
				formatter: '{value} %'
			},
			show: true
		}],

		series: [{
				name: '日',
				type: 'line',
				stack: '总量',
				color: '#6a8ee3',
				itemStyle: {
					normal: {
						lineStyle: {
							color: "#6a8ee3"
						}
					}
				},
				data: [20, 32, 11, 14, 30]
			},
			{
				name: '月',
				type: 'line',
				stack: '总量',
				color: '#48cea6',
				itemStyle: {
					normal: {
						lineStyle: {
							color: "#48cea6"
						}
					}
				},
				data: [20, 82, 19, 24, 26]
			},
			{
				name: '年',
				type: 'line',
				stack: '总量',
				color: '#fad567',
				itemStyle: {
					normal: {
						lineStyle: {
							color: "#fad567"
						}
					}
				},
				data: [50, 32, 21, 14, 38]
			}
		]
	};
	myChart.setOption(option);
	*/
	
	
	// 安全分析echarts实例
	/*
	var myChart = echarts.init(document.getElementById('second-echart'));
	option = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		color: ['#6a8ee3', '#ffc400', '#b39ddb'],
		legend: {
			x: 'center',
			y: 'bottom',
			data: ['情况一', '情况二', '情况三']
		},
		series: [{
			name: '访问来源',
			type: 'pie',
			radius: ['50%', '70%'],
			avoidLabelOverlap: false,
			label: {
				normal: {
					show: false,
					position: 'center'
				},
				emphasis: {
					show: true,
					textStyle: {
						fontSize: '20',
						fontWeight: 'bold'
					}
				}
			},
			labelLine: {
				normal: {
					show: false
				}
			},
			data: [{
					value: 65,
					name: '情况一'
				},
				{
					value: 350,
					name: '情况二'
				},
				{
					value: 134,
					name: '情况三'
				}
			]
		}]
	};
    
	myChart.setOption(option);
	*/
	// 毕业生职业分析echarts实例
	
	
	/*
	var myChart = echarts.init(document.getElementById('third-echart'));
	var plantCap = [{
		name: '电子网络',
		value: '120',

	}, {
		name: '媒体艺术',
		value: '115',

	}, {
		name: '物流采购',
		value: '113',

	}, {
		name: '金融保险',
		value: '95',

	}, {
		name: '教育科研',
		value: '92',

	}, {
		name: '官员翻译',
		value: '87',

	}, {
		name: '行政高管',
		value: '87',

	}]

	var datalist = [{
		offset: [7, 40],
		symbolSize: 160,
		opacity: 1,
		fontSize: '14',
		itemStyle: {
			normal: {
				color: '#ddd'
			}
		}
	}, {
		offset: [32, 100],
		symbolSize: 75,
		opacity: 1,
		fontSize: '14',
		itemStyle: {
			normal: {
				color: '#c00'
			}
		}
	}, {
		offset: [53, 93],
		symbolSize: 75,
		opacity: 1,
		fontSize: '14',
		itemStyle: {
			normal: {
				color: '#c00'
			}
		}
	}, {
		offset: [79, 92],
		symbolSize: 120,
		opacity: 1,
		fontSize: '14',
		itemStyle: {
			normal: {
				color: '#c00'
			}
		}
	}, {
		offset: [44, 0],
		symbolSize: 90,
		opacity: 1,
		fontSize: '14',
		itemStyle: {
			normal: {
				color: '#c00'
			}
		}
	}, {
		offset: [65, 0],
		symbolSize: 60,
		opacity: 1,
		fontSize: '12',
		itemStyle: {
			normal: {
				color: '#c00'
			}
		}
	}, {
		offset: [98, 0],
		symbolSize: 110,
		opacity: 1,
		fontSize: '14',
		itemStyle: {
			normal: {
				color: '#c00'
			}
		}
	}]
	var datas = [];
	for(var i = 0; i < plantCap.length; i++) {
		var item = plantCap[i];
		var itemToStyle = datalist[i];
		datas.push({
			name: item.name + '\n' + item.value + '%',
			value: itemToStyle.offset,
			symbolSize: itemToStyle.symbolSize,
			label: {
				normal: {
					textStyle: {
						fontSize: itemToStyle.fontSize
					}
				},

			},
			itemStyle: {
				normal: {
					opacity: itemToStyle.opacity
				}
			},
		})
	}
	option = {
		tooltip: {
			trigger: 'item',
			backgroundColor: '#fff',
			textStyle: {
				color: '#999'
			},
			formatter: (item) => {
				if(item.data[2]) {
					return `${item.data[2]}<br/>  坐标: x ${item.data[0]}  y ${item.data[1]}`;
				}
			}
		},
		xAxis: [{
			gridIndex: 0,
			type: 'value',
			show: false,
			min: 0,
			max: 100,
			nameLocation: 'middle',
			nameGap: 30

		}],
		yAxis: [{
			gridIndex: 0,
			min: 0,
			show: false,
			max: 100,
			nameLocation: 'middle',
			nameGap: 30,
		}],
		series: [{
				type: 'scatter',
				symbol: 'circle',
				symbolSize: 120,
				label: {
					normal: {
						show: true,
						formatter: '{b}',
						color: '#fff',
						textStyle: {
							fontSize: '20'
						}
					},

				},

				itemStyle: {
					normal: {
						color: function(params) {
							var colorList = [
								'#50cbc4', '#de6060', '#4168a2', '#b39ddb', '#ffc400',
								'#6a8ee3', '#8166e4'
							];
							return colorList[params.dataIndex]
						},
					}
				},
				data: datas
			}

		]
	};

	// 为echarts对象加载数据 
	myChart.setOption(option);
	*/
	initAqyj();
	
	initReportList();
	
	initWanguiList();
	
	initWarningIncreaseRate();
});


//获取安全预警统计数据
function initAqyj(){
	$.get(_path+"/warning/getIndexQst", {} ,function(result,status){
		if(!!result){
			renderAqyjCharts(result);//渲染安全预警echarts
		} 
	},"json");
}


//渲染安全预警echarts
function renderAqyjCharts(resultData){
	var myChart = echarts.init(document.getElementById('first-echart'));
	
	option = {
		    title: {
		        text: ''
		    },
		    tooltip: {
		        trigger: 'axis',
		        //在这里设置
		        formatter: function (params, ticket, callback) {
		        	var str = "";
		        	params.forEach(function(item,index){
		        		str += "<span style='color:"+item.color+";'>●</span>&nbsp;"+item.seriesName+":"+parseFloat(item.value)*100+"%<br/>"; 
		        	});
		            return str;
		        }
		    },
		    legend: {
		        data:['消费预警','晚归预警','成绩预警','借阅预警']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: resultData.dateStrList
		    },
		    yAxis: {
		    	name:'日增长率',
		        type: 'value',
		        axisLabel: {
		        	formatter: function (value) {
			            return value*100+"%";
			        }
	            }
		    },
		    series: [
		        {
		            name:'消费预警',
		            type:'line',
		            data:resultData.xfyjZzlList
		        },
		        {
		            name:'晚归预警',
		            type:'line',
		            data:resultData.wgyjZzlList
		        },
		        {
		            name:'成绩预警',
		            type:'line',
		            data:resultData.cjyjZzlList
		        },
		        {
		            name:'借阅预警',
		            type:'line',
		            data:resultData.jyyjZzlList
		        }
		    ]
		};
	myChart.setOption(option);
}


//初始化分析报告和舆情监控列表
function initReportList(){
	$.get(_path+"/report/getList", {} ,function(result,status){
		if(!!result){
			var fxbgList = result.fxbg;
			var yqjkList = result.yqjk;
			
			var fxbgHtml = "";
			fxbgList.forEach(function(item,index){
				fxbgHtml += "<li><a href='report/getDetail?id="+item.id+"' target='_blank'>"+item.title+"</a><span class='time'>"+item.time+"</span></li>";
			});
			$("#fxbgPanel ul").html(fxbgHtml);
			
			var yqjkHtml = "";
			yqjkList.forEach(function(item,index){
				yqjkHtml += "<li><a href='report/getDetail?id="+item.id+"' target='_blank'>"+item.title+"</a><span class='time'>"+item.time+"</span></li>";
			});
			$("#yqjkPanel ul").html(yqjkHtml);
		}
	},"json");
}

//初始化晚归和危险列表
function initWanguiList(){
	$.get(_path+"/report/getWanguiList", {} ,function(result,status){
		if(!!result){
			var weixin = result.weixin;
			var wangui = result.wangui;
			
			var fxbgHtml = "";
			weixin.forEach(function(item,index){
				fxbgHtml += "<li><span>"+item.username +"&nbsp;&nbsp;" + item.name +"</span></li>";
			});
			$("#weixqymd ul").html(fxbgHtml);
			
			var yqjkHtml = "";
			wangui.forEach(function(item,index){
				yqjkHtml += "<li><span>"+item.username +"&nbsp;&nbsp;" + item.name +"</span></li>";
			});
			$("#wangmd ul").html(yqjkHtml);
		}
	},"json");
}

//初始化增长率
function initWarningIncreaseRate(){
	$.get(_path+"/warning/getIndexPageWarningCount", {} ,function(result,status){
		if(!!result){
			console.log(result);
			$("#totalCountXf").text(result.totalCountXf);
			$("#totalCountWg").text(result.totalCountWg);
			$("#totalCountCj").text(result.totalCountCj);
			$("#totalCountJy").text(result.totalCountJy);
			
			$("#xfDateZzl").text(parseFloat(result.xfDateZzl.toFixed(2))*100+"%");
			$("#xfMonthZzl").text(parseFloat(result.xfMonthZzl.toFixed(2))*100+"%");
			$("#xfYearZzl").text(parseFloat(result.xfYearZzl.toFixed(2))*100+"%");
			
			$("#wgDateZzl").text(parseFloat(result.wgDateZzl.toFixed(2))*100+"%");
			$("#wgMonthZzl").text(parseFloat(result.wgMonthZzl.toFixed(2))*100+"%");
			$("#wgYearZzl").text(parseFloat(result.wgYearZzl.toFixed(2))*100+"%");
			
			$("#cjDateZzl").text(parseFloat(result.cjDateZzl.toFixed(2))*100+"%");
			$("#cjMonthZzl").text(parseFloat(result.cjMonthZzl.toFixed(2))*100+"%");
			$("#cjYearZzl").text(parseFloat(result.cjYearZzl.toFixed(2))*100+"%");
			
			$("#jyDateZzl").text(parseFloat(result.jyDateZzl.toFixed(2))*100+"%");
			$("#jyMonthZzl").text(parseFloat(result.jyMonthZzl.toFixed(2))*100+"%");
			$("#jyYearZzl").text(parseFloat(result.jyYearZzl.toFixed(2))*100+"%");
			
			$(".percentage").each(function(index,element){
				 var textVal = parseFloat($(element).text().substring(0,$(element).text().length-1))
				 //alert(textVal);
				 if(textVal < 0){
					$(element).removeClass('red-bg');
				 	$(element).addClass('green-bg');
				 	
				 	$(element).prev().removeClass('fa-long-arrow-up');
				 	$(element).prev().addClass('fa-long-arrow-down');
				 }
			});
		}
	},"json");
}