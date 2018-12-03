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
    
    getMonthData(10101,1);//获取月份数据
    
    //切换到新增用户图表
    $("#newUserMonth").click(function(){
    	getMonthData(10101,0);
    });
    
    //切换到活跃用户图表
    $("#activeUserMonth").click(function(){
    	getMonthData(10102,0);
    });
	
	//切换到启动次数图表
    $("#sessionCountMonth").click(function(){
    	getMonthData(10103,0);
	});
    
    //切换到累计次数图表
    $("#totalUserMonth").click(function(){
    	getMonthData(10104,0);
	});
    
    //切换到活跃账号图表
    $("#qvMonth").click(function(){
    	getMonthData(10105,0);
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

//获取关键指标数据
function getGjzbData(){
	var crtDate = getNowFormatDate();
	$.get(_path+"/mta/yyjbzbSssj?idx=10101,10102,10103,10104,10105", {} ,function(result,status){
  		if(!!result&&!!result.ret_code&&result.ret_code==60000){
  			if(!!result.ret_data){
  				$('#newUser').html($('#newUser').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+result.ret_data.NewUser+"<span>");
  				$('#activeUser').html($('#activeUser').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+result.ret_data.ActiveUser+"<span>");
  				$('#QQUser').html($('#QQUser').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+result.ret_data.QQUser+"<span>");
  				$('#sessionCount').html($('#sessionCount').text()+"<br/><span style='font-weight:bold;font-size:20px;'>"+result.ret_data.SessionCount+"<span>");
  			}
  		}
  	},"json");
}

var monthData = {};
//获取月份数据,初始化图表，初始化表格
function getMonthData(type,first){
	var crtDate = getNowFormatDate();
	$.get(_path+"/mta/yyjbzbLxsjLastThirtyDays?idx=10101,10102,10103,10104,10105", {} ,function(result,status){
  		if(!!result){
  			if(!!result){
  				monthData = result;
  				if(type==10101){
  					initChart(monthData.keyList,monthData.newUserList);
  				}else if(type==10102){
  					initChart(monthData.keyList,monthData.activeUserList);
  				}else if(type==10103){
  					initChart(monthData.keyList,monthData.sessionCountList);
  				}else if(type==10104){
  					initChart(monthData.keyList,monthData.totalUserList);
  				}else if(type==10105){
  					initChart(monthData.keyList,monthData.qvList);
  				}
  				
  				if(first==1){
  				    //初始化月数据下方表格
  	  				initMonthDataTable(monthData);
  				}
  			}
  		}
  	},"json");
}

//初始化月数据表格
function initMonthDataTable(monthData){
	$("#monthDataTable").html("<tr><th>时间</th><th>新增用户数</th><th>活跃用户数</th><th>启动次数</th><th>累计次数</th><th>活跃账号数</th></tr>");
	var addHtml = "";
	if(!!monthData.keyList){
		var keyListHere = monthData.keyList;
		
		var newUserListHere = monthData.newUserList;
		var activeUserListHere = monthData.activeUserList;
		var qvListHere = monthData.qvList;
		var sessionCountListHere = monthData.sessionCountList;
		var totalUserListHere = monthData.totalUserList;
		
		keyListHere.forEach(function(item,index){
			addHtml += "<tr><td>"+item+"</td><td>"+newUserListHere[index]+"</td><td>"+activeUserListHere[index]+"</td><td>"+sessionCountListHere[index]+"</td><td>"+totalUserListHere[index]+"</td><td>"+qvListHere[index]+"</td></tr>";
		});
		
		$("#monthDataTable").append(addHtml);
	}
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