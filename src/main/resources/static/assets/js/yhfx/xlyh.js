jQuery(function($) {
	
    //初始化时选中第一个
    $('.sssj-xssj-type:first').addClass('selected-background');
    //选择图表数据类型时触发更改css样式
    $('.sssj-xssj-type').click(function(){
    	 $(".sssj-xssj-type").removeClass('selected-background');
    	 $(this).addClass('selected-background');
    });
    
    //alert(getNowFormatDate());
    
    
    getMonthData(10401,1);//获取月份数据
    
    $("#onlineTimePerUv").click(function(){
    	getMonthData(10401,0);
    });
    
    $("#onlineTimePerSession").click(function(){
    	getMonthData(10402,0);
    });
	
    $("#pageCountPerUv").click(function(){
    	getMonthData(10403,0);
	});
    
    $("#pageCountPerSession").click(function(){
    	getMonthData(10404,0);
	});
});

//初始化图表
function initChart(sData,yData){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('qstChart'));

    // 指定图表的配置项和数据
    option = {
    		title: {
    	        text: '最近30天数据折线图'
    	    },
    	    tooltip: {
    	        trigger: 'axis'
    	    },
    	    xAxis: {
    	        type: 'category',
    	        data: sData
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    series: [{
    	        data: yData,
    	        type: 'line'
    	    }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

var monthData = {};
//获取月份数据,初始化图表，初始化表格
function getMonthData(type,first){
	var crtDate = getLastDayFormatDate();
	$.get(_path+"/mta/yhxwsjUsageLastThirtyDays?idx=10401,10402,10403,10404", {} ,function(result,status){
		if(!!result){
			monthData = result;
			if(type==10401){
				monthData.onlineTimePerUvList.forEach(function(item,index){
				});
				initChart(monthData.keyList,monthData.onlineTimePerUvList);
			}else if(type==10402){
                monthData.onlineTimePerSessionList.forEach(function(item,index){
                	
				});
				initChart(monthData.keyList,monthData.onlineTimePerSessionList);
			}else if(type==10403){
				initChart(monthData.keyList,monthData.pageCountPerUvList);
			}else if(type==10404){
				initChart(monthData.keyList,monthData.pageCountPerSessionList);
			}
		}
  	},"json");
}

function getLastDayFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + (strDate-1);
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}