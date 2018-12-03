var school = 0;
var classid = 0;

jQuery(function($) {
	//初始化日历
	laydate.render({ 
		elem: '#searchDate', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		value: new Date(),
		done: function(value, date, endDate){
		    //console.log(value);
			$(".sssj-header-day").each(function(index,element){
				 $(element).removeClass('sssj-header-day-selected');
			});
			var yjTypeSelected = $(".selected-background").attr("id");
			
			getChartData(value,yjTypeSelected);
		}
	});
	
    //初始化时选中第一个
    $('.sssj-header-day:first').addClass('sssj-header-day-selected');
    //选择图表数据类型时触发更改css样式
    $(".sssj-header-day").click(function(index,element){
    	$(".sssj-header-day").each(function(index,element){
        	$(element).removeClass('sssj-header-day-selected');
        });
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
		var yjTypeSelected = $(".selected-background").attr("id");
		//alert(daySelected+"-----"+yjTypeSelected);
		getChartData(daySelected,yjTypeSelected);
	});
	
	//点击类型查询数据
	$(".sssj-xssj-type").click(function(){
		var daySelected = $(".sssj-header-day-selected").attr("id");
		var yjTypeSelected = $(this).attr("id");
		//alert(daySelected+"-----"+yjTypeSelected);
		
		getChartData(daySelected,yjTypeSelected);
	});
	
	//选择学院查询学院ap数据
	$("#instituteId").change(function(){
		var daySelected = $(".sssj-header-day-selected").attr("id");
		var yjTypeSelected = $(".sssj-xssj-type").attr("id");
		getChartData(daySelected,yjTypeSelected);
	});
		//选择学院查询学院ap数据
	$("#classId").change(function(){
		var daySelected = $(".sssj-header-day-selected").attr("id");
		var yjTypeSelected = $(".sssj-xssj-type").attr("id");
		getChartData(daySelected,yjTypeSelected);
	});
	
		//查询个人ap数据
	$("#searchBtn").click(function(){
		var daySelected = $(".sssj-header-day-selected").attr("id");
		var yjTypeSelected = $(".sssj-xssj-type").attr("id");
		getChartData(daySelected,yjTypeSelected);
	});
	
	//初始化
	var daySelectedHere = $(".sssj-header-day-selected").attr("id");
	var yjTypeSelectedHere = $(".selected-background").attr("id");
	getChartData(daySelectedHere,yjTypeSelectedHere);
	
	getXyData();
});

//初始化柱状图表
function initBarChart(chartData){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('qstBarChart'));
    
    var dateStrArr = [];
    var amountArr = [];

    /*
    chartData.forEach(function(item,index){
    	dateStrArr.push(item.time);
    	amountArr.push(item.personsAmt);
    });
    */
    //！！！循环遍历出所有日期，然后根据日期统计数量
    chartData.forEach(function(item,index){
    	if(dateStrArr.indexOf(item.time)>=0){
    		return false;
    	}
    	dateStrArr.push(item.time);
    });
    
    //按时间序排序
    dateStrArr.sort();
    
    //计算每日预警数量
    dateStrArr.forEach(function(item,index){
    	 var count = 0;
    	 chartData.forEach(function(it,idx){
    		 if(it.time==item){
    			 count++;
    		 }
    	 });
    	 amountArr.push(count);
    });
    //console.log(dateStrArr);	
    //console.log(amountArr);	 
    
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
    	        data: dateStrArr
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


var chartData = {};
//获取月份数据,初始化图表，初始化表格
function getChartData(lastDates,yjType){
	
	var instituteid = $("#instituteId").val();
	var classid = $("#classId").val();
	var username = $("#yhm").val();
	console.log('instituteid='+instituteid +'classId= '+classId+'username='+username)
	$.get(_path+"/ymfx/getData?daySelected="+lastDates+"&yjTypeSelected="+yjType+"&instituteid="+instituteid+"&classid="+classid+"&username="+username,function(result,status){
		if(!!result&&!!result.warningModelList){
			initBarChart(result.warningModelList);//初始化柱状图
			initDataTable(result.warningModelList);//初始化表格
		}
  	},"json");
}

//初始化表格数据
function initDataTable(tableData){
	var appendTrs = "<tr><th>日期</th><th>人员</th><th>人员姓名</th><th>是否请假</th></tr>";
	var str = '未请假';
	tableData.forEach(function(item,index){
		if(item.leaveStatus == '1'){
			str = '已请假';
		}else{
			str = '未请假';
		}
		appendTrs += "<tr><td>"+item.time+"</td><td>"+item.username+"</td><td>"+item.name+"</td><td>"+str+"</td></tr>";
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

//点击出现下拉框
function showSelect(){
	console.log('school='+school+'classid='+classid);
	if(school == 0){
		$("#schooldiv").attr('style','display:inline-block');
		school = 1;
		return;
	}else if(classid == 0){
		$("#classdiv").attr('style','display:inline-block');
		classid = 1;
	}else{
		$("#schooldiv").hide();
		$("#classdiv").hide();
		$("#stuInfo").attr('style','display:inline-block');
	}
}


//加载学院信息
function getXyData(){
	var institutesHtml = "";
	$.get(_path+"/mAp/getInstituteList",function(result,status){
		if(!!result){
			result.forEach(function(item,index){
				institutesHtml += "<option value='"+item.instituteId+"'>"+item.instituteName+"</option>";
			});
			$("#instituteId").append(institutesHtml);
		}
  	},"json");
}


//选择学院联动班级下拉列表
$("#instituteId").change(function(){
	$("#classId").html("<option value=''>--选择班级--</option>");
	var xyId = $("#instituteId").val();
	
	var classesHtml = "";
	$.get(_path+"/mAp/getClassList?xyId="+xyId,function(result,status){
		if(!!result){
			result.forEach(function(item,index){
				classesHtml += "<option value='"+item.classId+"'>"+item.className+"</option>";
			});
			$("#classId").append(classesHtml);
		}
  	},"json");
});
