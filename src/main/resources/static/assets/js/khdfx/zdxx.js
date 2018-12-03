jQuery(function($) {
	//初始化日历
	laydate.render({ 
		elem: '#searchDate', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		value: new Date(),
		done: function(value, date, endDate){
		    //console.log(value);
			getChartData(1);
			
		    //选择图表数据类型时触发更改css样式
	    	 $(".sssj-xssj-type").removeClass('selected-background');
		    //初始化时选中第一个
		    $('.sssj-xssj-type:first').addClass('selected-background');
		}
	});
	
    //初始化时选中第一个
    $('.sssj-xssj-type:first').addClass('selected-background');
    //选择图表数据类型时触发更改css样式
    $('.sssj-xssj-type').click(function(){
    	 $(".sssj-xssj-type").removeClass('selected-background');
    	 $(this).addClass('selected-background');
    });
    
    //二级初始化时选中第一个
    $('.sssj-xssj-type2:first').addClass('selected-background2');
    //选择图表数据类型时触发更改css样式
    $('.sssj-xssj-type2').click(function(){
    	 $(".sssj-xssj-type2").removeClass('selected-background2');
    	 $(this).addClass('selected-background2');
    });
    
    //alert(getNowFormatDate());
    
    getChartData(1);//获取月份数据
    
    //切换到操作系统版本图表
    $("#czxtbb").click(function(){
    	getChartData(1);
    });
    
    //切换到分辨率图表
    $("#fbl").click(function(){
    	getChartData(2);
    });
	
	//切换到网络环境图表
    $("#wlhj").click(function(){
    	getChartData(3);
	});
    
    //切换到运营商图表
    $("#yys").click(function(){
    	getChartData(4);
	});
    
    //切换到设备型号图表
    $("#sbxh").click(function(){
    	getChartData(5);
    });
    
    //选择日期触发更新图表
    $("#searchDate").change(function(){
    	
    });
});

//初始化图表
function initChart(resultDataArr){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('qstChart'));

    var newUserCount = 0;
    var activeUserCount = 0;
    var sessionCountCount = 0;
    
    var terminalArr = [];
	var newUserArr = [];
	var activeUserArr = [];
	var sessionCountArr = [];
	
	resultDataArr.sort(function(a,b){
		return a.Terminal-b.Terminal;
	});
	
	resultDataArr.forEach(function(item,index){
		//遍历出终端类型
		if(item.Terminal!=undefined){
			terminalArr.push(item.Terminal);
		}
		//遍历出新增用户
		if(item.NewUser!=undefined){
			newUserArr.push(item.NewUser);
			newUserCount += parseInt(item.NewUser);
		}
		//遍历出活跃用户
		if(item.ActiveUser!=undefined){
			activeUserArr.push(item.ActiveUser);
			activeUserCount += parseInt(item.ActiveUser);
		}
		//遍历出启动次数
		if(item.SessionCount!=undefined){
			sessionCountArr.push(item.SessionCount);
			sessionCountCount += parseInt(item.SessionCount);
		}
	});
    
    // 指定图表的配置项和数据
    option = {
	    title: {
	        text: '设备分析数据统计',
	        subtext: ''
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	        data: ['新增用户','活跃用户','启动次数']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    yAxis: {
	        type: 'category',
	        data: terminalArr
	    },
	    series: [
	        {
	            name: '新增用户',
	            type: 'bar',
	            label:{
	                normal:{
	                    show:true,
	                    position:"right",
	                    formatter:function(params){
	                    	if(!!newUserCount){
	                    		return (params.value/newUserCount*100).toFixed(2)+"%";
	                    	}else{
	                    		return "0%";
	                    	}
	                    }
	                },
	                emphasis:{
	                    show:true,
	                    position:"right",
	                    formatter:function(params){
	                    	if(!!newUserCount){
	                    		return (params.value/newUserCount*100).toFixed(2)+"%";
	                    	}else{
	                    		return "0%";
	                    	}
	                    }
	                }
	            },
	            data: newUserArr
	        },
	        {
	            name: '活跃用户',
	            type: 'bar',
	            label:{
	                normal:{
	                    show:true,
	                    position:"right",
	                    formatter:function(params){
	                    	if(!!activeUserCount){
	                    		return (params.value/activeUserCount*100).toFixed(2)+"%";
	                    	}else{
	                    		return "0%";
	                    	}
	                    }
	                },
	                emphasis:{
	                    show:true,
	                    position:"right",
	                    formatter:function(params){
	                    	if(!!activeUserCount){
	                    		return (params.value/activeUserCount*100).toFixed(2)+"%";
	                    	}else{
	                    		return "0%";
	                    	}
	                    }
	                }
	            },
	            data: activeUserArr
	        },
	        {
	            name: '启动次数',
	            type: 'bar',
	            label:{
	                normal:{
	                    show:true,
	                    position:"right",
	                    formatter:function(params){
	                    	if(!!sessionCountCount){
	                    		return (params.value/sessionCountCount*100).toFixed(2)+"%";
	                    	}else{
	                    		return "0%";
	                    	}
	                    }
	                },
	                emphasis:{
	                    show:true,
	                    position:"right",
	                    formatter:function(params){
	                    	if(!!sessionCountCount){
	                    		return (params.value/sessionCountCount*100).toFixed(2)+"%";
	                    	}else{
	                    		return "0%";
	                    	}
	                    }
	                }
	            },
	            data: sessionCountArr
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

var chartData = {};
//获取数据,初始化图表，初始化表格
function getChartData(type){
	var crtDate = !!$("#searchDate").val()? $("#searchDate").val():getNowFormatDate();
	//alert(crtDate);
	var idxStr="10301,10302,10303";
	if(!!type&&type==2){
		idxStr="10301,10302";
	}
	
	$.get(_path+"/mta/zdsbsj?idx="+idxStr+"&start_date="+crtDate+"&end_date="+crtDate+"&ty="+type, {} ,function(result,status){
  		if(!!result){
			//console.log(result);
			if(result.ret_code=='60000'){
				if(!!result.ret_data){
					//初始化图表信息
					initChart(result.ret_data);
					
					//初始化表格信息
					initDataTable(result.ret_data)
				}
			}
  		}
  	},"json");
}

//初始化数据表格
function initDataTable(resultDataArr){
	var terminalArr = [];
	var newUserArr = [];
	var activeUserArr = [];
	var sessionCountArr = [];
	
	resultDataArr.sort(function(a,b){
		return a.Terminal-b.Terminal;
	});
	
	resultDataArr.forEach(function(item,index){
		//遍历出终端类型
		if(item.Terminal!=undefined){
			terminalArr.push(item.Terminal);
		}
		//遍历出新增用户
		if(item.NewUser!=undefined){
			newUserArr.push(item.NewUser);
		}
		//遍历出活跃用户
		if(item.ActiveUser!=undefined){
			activeUserArr.push(item.ActiveUser);
		}
		//遍历出启动次数
		if(item.SessionCount!=undefined){
			sessionCountArr.push(item.SessionCount);
		}
	});
	
	//展示表格数据
    $("#dataTable").html("<tr><th>"+$(".selected-background").text().trim()+"</th><th>新增用户数</th><th>活跃用户数</th><th>启动次数</th></tr>");	
    //初始化详细数据表格信息
    var addTr = "";
    terminalArr.forEach(function(item,index){
    	var sessionCountTd = (!!sessionCountArr[index])? sessionCountArr[index]:0;
    	addTr += "<tr><td>"+terminalArr[index]+"</td><td>"+newUserArr[index]+"</td><td>"+activeUserArr[index]+"</td><td>"+sessionCountTd+"</td></tr>"
    });
    $("#dataTable").append(addTr);
}

//初始化日历输入框
function initDateInput(){
	//初始化日历
	laydate.render({ 
		elem: '#searchDate', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		value: new Date()
	});
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}