jQuery(function($) {
	//初始化日历
	laydate.render({ 
		elem: '#searchDate', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		value: new Date(),
		done: function(value, date, endDate){
		    //console.log(value);
			$(".sssj-header-day").removeClass('sssj-header-day-selected');
			getChartData(value);
		}
	});
	
	//选择图表数据类型时触发更改css样式
	$(".sssj-header-day").removeClass('sssj-header-day-selected');
    //初始化时选中第一个
    $('.sssj-header-day:first').addClass('sssj-header-day-selected');
	
	$("#todaySp").click(function(){
		$(".sssj-header-day").removeClass('sssj-header-day-selected');
	    $(this).addClass('sssj-header-day-selected');
		getChartData(0);
	});
	$("#sevenDaySp").click(function(){
		$(".sssj-header-day").removeClass('sssj-header-day-selected');
	    $(this).addClass('sssj-header-day-selected');
		getChartData(7);
	});
	$("#seventeenSp").click(function(){
		$(".sssj-header-day").removeClass('sssj-header-day-selected');
	    $(this).addClass('sssj-header-day-selected');
		getChartData(14);
	});
    $("#thirtySp").click(function(){
    	$(".sssj-header-day").removeClass('sssj-header-day-selected');
        $(this).addClass('sssj-header-day-selected');
    	getChartData(30);
	});
    $("#totalSp").click(function(){
    	$(".sssj-header-day").removeClass('sssj-header-day-selected');
        $(this).addClass('sssj-header-day-selected');
    	getChartData('total');
	});
	getChartData(0);
});

//初始化柱状图表
function initBarChart(chartData){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('qstBarChart'));
    
    var carrierNameArr = [];
    var amountArr = [];

    chartData.forEach(function(item,index){
    	carrierNameArr.push(item.carrierName);
    	amountArr.push(item.amount);
    });
    
    option = {
    		title: {
    	        text: '柱状图',
    	        left:'center'
    	    },
    	    tooltip : {
    	        trigger: 'axis',
    	        axisPointer : {      // 坐标轴指示器，坐标轴触发有效
    	            type : 'shadow'  // 默认为直线，可选为：'line' | 'shadow'
    	        }
    	    },
    	    xAxis: {
    	        type: 'category',
    	        data: carrierNameArr
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    color:['#54A2D5'],
    	    series: [{
    	    	barWidth: '50%',
    	        data: amountArr,
    	        type: 'bar'
    	    }]
    	};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//初始化饼图图表
function initPieChart(chartData){
	// 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('qstPieChart'));

    var carrierNameArr = [];
    var seriesArr = [];

    chartData.forEach(function(item,index){
    	carrierNameArr.push(item.carrierName);
    	seriesArr.push({name:item.carrierName,value:item.amount,itemStyle:{normal:{
    	}
    	}});
    });
    
    // 指定图表的配置项和数据
    option2 = {
	    title : {
	        text: '饼状图',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: carrierNameArr
	    },
	    color:['#FA8349', '#65C882','#EF6B6B','#649AE5'],  
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:seriesArr,
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);
}

var chartData = {};
//获取月份数据,初始化图表，初始化表格
function getChartData(lastDates){
	$.get(_path+"/mDevice/getCarrierCountList?lastDates="+lastDates, {} ,function(result,status){
		if(!!result&&!!result.data){
			initBarChart(result.data);//初始化柱状图
			initPieChart(result.data);//初始化饼图
			initDataTable(result.data);//初始化表格
		}
  	},"json");
}

//初始化表格数据
function initDataTable(tableData){
	var appendTrs = "<tr><th>运营商</th><th>用户数量</th></tr>";
	tableData.forEach(function(item,index){
		appendTrs += "<tr><td>"+item.carrierName+"</td><td>"+item.amount+"</td></tr>";
	});
	$("#dataTable").html(appendTrs);
}

function getCurrentDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" +strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}