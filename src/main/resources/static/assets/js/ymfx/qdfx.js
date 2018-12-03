jQuery(function($) {
	//初始化日历
	laydate.render({ 
		elem: '#kssj', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		type: 'datetime',
		format: 'yyyy年MM月dd日 HH:mm:ss',
		value: new Date()
	});
	
	//初始化日历
	laydate.render({ 
		elem: '#jssj', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		type: 'datetime',
		format: 'yyyy年MM月dd日 HH:mm:ss',
		value: new Date(),
		done: function(value, date, endDate){
			//getChartData();
		}
	});
	
	
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
	
	
	//getChartData();
	
	//加载学院信息
	getXyData();
});

//获取月份数据,初始化图表，初始化表格
function getChartData(){
	var kssj = $("#kssj").val();
	var jssj = $("#jssj").val();
	var instituteId = $("#instituteId").val();
	var classId = $("#classId").val();
	var address = $("#address").val();
	var yhm = $("#yhm").val();
	$.get(_path+"/mDevice/selectSignList?kssj="+kssj+"&jssj="+jssj+"&instituteId="+instituteId+"&classId="+classId+"&address="+address+"&yhm="+yhm, {} ,function(result,status){
		if(!!result&&!!result.data){
			initDataTable(result.data);//初始化表格
		}
  	},"json");
}

//初始化表格数据
function initDataTable(tableData){
	var appendTrs = "<tr><th>账号</th><th>姓名</th><th>签到时间</th><th>签到地点</th><th>所在班级</th><th>所在学院</th></tr>";
	tableData.forEach(function(item,index){
		appendTrs += "<tr><td>"+item.yhm+"</td><td>"+item.xm+"</td><td>"+item.signData+"</td><td>"+item.address+"</td><td>"+item.className+"</td><td>"+item.instituteName+"</td></tr>";
	});
	$("#dataTable").html(appendTrs);
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