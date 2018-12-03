jQuery(function($) {
	
	getChartData();
});


//初始化饼图图表
function initPieChart(){
	// 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('qstPieChart'));

    // 指定图表的配置项和数据
    option2 = {
	    title : {
	        text: '上课时间学生所在区域统计',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:['宿舍','操场','其他','教室']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'访问来源',
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
	                {value:10, name:'宿舍'},
	                {value:5, name:'操场'},
	                {value:5, name:'其他'},
	                {value:80, name:'教室'}
	            ]
	        }
	    ]
	};
    	                    
    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);
}

var chartData = {};
//获取月份数据,初始化图表，初始化表格
function getChartData(){
	/*
	$.get(_path+"/mDevice/getCarrierCountList?lastDates="+lastDates, {} ,function(result,status){
		if(!!result&&!!result.data){
			initPieChart(result.data);//初始化饼图
		}
  	},"json");
	*/
	initPieChart();//初始化饼图
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