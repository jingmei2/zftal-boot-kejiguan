jQuery(function($) {
    //初始化时选中第一个
    $('.sssj-xssj-type:first').addClass('selected-background');
    //选择图表数据类型时触发更改css样式
    $('.sssj-xssj-type').click(function(){
    	 $(".sssj-xssj-type").removeClass('selected-background');
    	 $(this).addClass('selected-background');
    });
    
    //alert(getNowFormatDate());
    
    getGjzbData();//获取关键指标数据
    
    getMonthData(10201,1);//获取月份数据
    
    $("#dauMonth").click(function(){
    	getMonthData(10201,0);
    });
    
    $("#wauMonth").click(function(){
    	getMonthData(10202,0);
    });
	
    $("#dauwauMonth").click(function(){
    	getMonthData(10204,0);
	});
    
    $("#mauMonth").click(function(){
    	getMonthData(10203,0);
	});
    
    $("#daumauMonth").click(function(){
    	getMonthData(10205,0);
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

//获取昨日关键指标数据
function getGjzbData(){
	var lastDateStr = getLastDayFormatDate();
	$.get(_path+"/mta/yhxwsjActive?idx=10201,10202,10203&start_date="+lastDateStr+"&end_date="+lastDateStr, {} ,function(result,status){
  		if(!!result&&!!result.ret_code&&result.ret_code==60000){
  			if(!!result.ret_data){
  				var retDataObj = {};
  				for(var fieldName in result.ret_data){
  					retDataObj = result.ret_data[fieldName];
  				}
  				var dayUv = (!!retDataObj.DayUv)? retDataObj.DayUv:0;
  				var weekUv = (!!retDataObj.WeekUv)? retDataObj.WeekUv:0;
  				var monthUv = (!!retDataObj.MonthUv)? retDataObj.MonthUv:0;
  				var dmUv = 0;
  				if(!!dayUv&&!!monthUv){
  					dmUv = (retDataObj.DayUv/retDataObj.MonthUv).toFixed(1)
  				}
  				$('#lastDateDAUCount').html($('#lastDateDAUCount').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+dayUv+"<span>");
  				$('#lastDateWAUCount').html($('#lastDateWAUCount').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+weekUv+"<span>");
  				$('#lastDateMAUCount').html($('#lastDateMAUCount').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+monthUv+"<span>");
  				$('#DAUMAUPercent').html($('#DAUMAUPercent').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+dmUv+"<span>");
  			}
  		}
  	},"json");
}

var monthData = {};
//获取月份数据,初始化图表，初始化表格
function getMonthData(type,first){
	var crtDate = getLastDayFormatDate();
	$.get(_path+"/mta/yhxwsjActiveLastThirtyDays?idx=10201,10202,10203", {} ,function(result,status){
		if(!!result){
			monthData = result;
			if(type==10201){
				initChart(monthData.keyList,monthData.dayUvList);
			}else if(type==10202){
				initChart(monthData.keyList,monthData.weekUvList);
			}else if(type==10203){
				initChart(monthData.keyList,monthData.monthUvList);
			}else if(type==10204){
				monthData.duvwuvList.forEach(function(item,index){
					if(!!item.toString().split(".")[1]&&item.toString().split(".")[1].length>2){
						monthData.duvwuvList[index]=item.toFixed(2);
					}
				});
				initChart(monthData.keyList,monthData.duvwuvList);
			}else if(type==10205){
				monthData.duvmuvList.forEach(function(item,index){
					if(!!item.toString().split(".")[1]&&item.toString().split(".")[1].length>2){
						monthData.duvmuvList[index]=item.toFixed(2);
					}
				});
				initChart(monthData.keyList,monthData.duvmuvList);
			}
			
			if(first==1){
			    //初始化月数据下方表格
  				initMonthDataTable(monthData);
			}
		}
  	},"json");
}

//初始化月数据表格
function initMonthDataTable(monthData){
	$("#monthDataTable").html("<tr><th>时间</th><th>DAU</th><th>WAU</th><th>DAU/WAU</th><th>MAU</th><th>DAU/MAU</th></tr>");
	var addHtml = "";
	if(!!monthData.keyList){
		var keyListHere = monthData.keyList;
		var dayUvListHere = monthData.dayUvList;
		var weekUvListHere = monthData.weekUvList;
		var monthUvListHere = monthData.monthUvList;
		var duvwuvListHere = monthData.duvwuvList;
		var duvmuvListHere = monthData.duvmuvList;
		
		monthData.duvwuvList.forEach(function(item,index){
			if(!!item.toString().split(".")[1]&&item.toString().split(".")[1].length>2){
				monthData.duvwuvList[index]=item.toFixed(2);
			}
		});
		monthData.duvmuvList.forEach(function(item,index){
			if(!!item.toString().split(".")[1]&&item.toString().split(".")[1].length>2){
				monthData.duvmuvList[index]=item.toFixed(2);
			}
		});
		
		keyListHere.forEach(function(item,index){
			addHtml += "<tr><td>"+item+"</td><td>"+dayUvListHere[index]+"</td><td>"+weekUvListHere[index]+"</td><td>"+duvwuvListHere[index]+"</td><td>"+monthUvListHere[index]+"</td><td>"+duvmuvListHere[index]+"</td></tr>";
		});
		$("#monthDataTable").append(addHtml);
	}
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