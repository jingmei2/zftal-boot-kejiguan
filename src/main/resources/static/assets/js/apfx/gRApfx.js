jQuery(function($) {
	//初始化日历
	laydate.render({ 
		elem: '#searchDate', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		value: new Date(),
		done: function(value, date, endDate){
		    //console.log(value);
			$(".sssj-header-day").removeClass('sssj-header-day-selected');
			
			var yhm = $("#yhm").val();
			getChartData(value,yhm);
		}
	});
	
    //初始化时选中第一个
    $('.sssj-header-day:first').addClass('sssj-header-day-selected');
    //选择图表数据类型时触发更改css样式
    $(".sssj-header-day").click(function(index,element){
    	$(".sssj-header-day").removeClass('sssj-header-day-selected');
    	$(this).addClass('sssj-header-day-selected');
    });
    
    
    //初始化时选中第一个
    $('.sssj-xssj-type:first').addClass('selected-background');
    //选择图表数据类型时触发更改css样式
    $('.sssj-xssj-type').click(function(){
    	 $(".sssj-xssj-type").each(function(index,element){
    		 $(element).removeClass('selected-background');
    	 });
    	 $(this).addClass('selected-background');
    });
    
	//点击时间查询数据
	$(".sssj-header-day").click(function(){
		var daySelected = $(this).attr("id");
		//alert(daySelected+"-----"+yjTypeSelected);
		var yhm = $("#yhm").val();
		getChartData(daySelected,yhm);
	});
	
	//点击类型查询数据
	$(".sssj-xssj-type").click(function(){
		var daySelected = $(".sssj-header-day-selected").attr("id");
		//alert(daySelected+"-----"+yjTypeSelected);
		
		var yhm = $("#yhm").val();
		getChartData(daySelected,yhm);
	});
	
	//查询个人ap数据
	$("#searchBtn").click(function(){
		var daySelected = $(".sssj-header-day-selected").attr("id");
		//alert(daySelected+"-----"+yjTypeSelected);
		
		var yhm = $("#yhm").val();
		getChartData(daySelected,yhm);
	});
	
	//初始化
	var daySelectedHere = $(".sssj-header-day-selected").attr("id");
	//alert(daySelected+"-----"+yjTypeSelected);
	
	var yhm = $("#yhm").val();
	getChartData(daySelectedHere,yhm);
	
});

//初始化柱状图表
function initBarChart(chartData){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('qstBarChart'));
    
    var wifiArr = [];
    var useTimesArr = [];

    chartData.forEach(function(item,index){
    	wifiArr.push(item.wifiName);
    	useTimesArr.push(item.useTimes);
    });
    
    option = {
    		title: {
    	        text: '',
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
    	        data: wifiArr
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    color:['#54A2D5'],
    	    series: [{
    	    	barWidth: '50%',
    	        data: useTimesArr,
    	        type: 'bar',
    	        itemStyle : { 
    	        	normal: {
    	        		label : {
    	        			show: true,
    	        			position: 'top',
    	        		}
    	            }
    	        },
    	    }]
    	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


var chartData = {};
//获取月份数据,初始化图表，初始化表格
function getChartData(lastDates,yhm){
	$.get(_path+"/mAp/selectApCountList?daySelected="+lastDates+"&yhm="+yhm,function(result,status){
		if(!!result&&!!result.data){
			initBarChart(result.data);//初始化柱状图
			initDataTable(result.data);//初始化表格
		}
  	},"json");
}

//初始化表格数据
function initDataTable(tableData){
	var appendTrs = "<tr><th>序号</th><th>WiFi名称</th><th>访问量</th></tr>";
	tableData.forEach(function(item,index){
		appendTrs += "<tr><td>"+(index+1)+"</td><td>"+item.wifiName+"</td><td>"+item.useTimes+"</td></tr>";
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