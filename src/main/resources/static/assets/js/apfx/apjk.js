$(function(){
	var map = new AMap.Map('container', {
		resizeEnable: true,
		zoom:16,
		center: [119.636423,29.062591]
	});
	
    // 在新中心点添加 marker 
    var marker = new AMap.Marker({
    	icon: _path+"/assets/images/mapjzy.png",
    	title:'金华职业技术学院',
        position: [119.636423,29.062591]
    });
    //marker.setTitle("姓名:"+item.userName+',手机号:'+item.phoneNumber);
    marker.setMap(map);
    // 设置点标记的动画效果，此处为弹跳效果
    //marker.setAnimation('AMAP_ANIMATION_BOUNCE');
    
    //绘制区域
    var polygonArr = new Array();//多边形覆盖物节点坐标数组
    polygonArr.push([119.635093,29.064354]);
    polygonArr.push([119.635693,29.065817]);
    polygonArr.push([119.634449,29.065854]);
    polygonArr.push([119.634148,29.065404]);
    var  polygon = new AMap.Polygon({
        path: polygonArr,//设置多边形边界路径
        strokeColor: "#FF33FF", //线颜色
        strokeOpacity: 0.2, //线透明度
        strokeWeight: 3,    //线宽
        fillColor: "#1791fc", //填充色
        fillOpacity: 0.35//填充透明度
    });
    polygon.setMap(map);
    
    //初始化地图点数据
    initDevicesLocInfo(map);
    
    //定时任务，刷新地图上的点数据
    window.setInterval(function(){
		var deviceArray = [];
		$.get(_path+"/mDevice/getList", {} ,function(result,status){
			if(!!result&&!!result.data){
				deviceArray = result.data;
				
				if(!!deviceArray){
					deviceArray.forEach(function(item,index){
						if(!!item.locInfo){
							var jingweidu = item.locInfo.split(",");
							marker = new AMap.Marker({
								icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
								title: "账号:"+item.userName+',手机号:'+item.phoneNumber,
								position: jingweidu
							});
							marker.setMap(map);
						}
					});
				}
			}
		},"json");
		
	},60000);
    
    //初始化楼栋信息
    initlddm();
    
});

//初始化地图点数据
function initDevicesLocInfo(map){
	var deviceArray = [];
	$.get(_path+"/mDevice/getList", {} ,function(result,status){
		if(!!result&&!!result.data){
			deviceArray = result.data;
			
			if(!!deviceArray){
				deviceArray.forEach(function(item,index){
					if(!!item.locInfo){
						var jingweidu = item.locInfo.split(",");
						marker = new AMap.Marker({
							icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
							title: "账号:"+item.userName+',手机号:'+item.phoneNumber,
							position: jingweidu
						});
						marker.setMap(map);
					}
				});
			}
		}
	},"json");
}

//加载数据
function getData(){
	var deviceArray = [];
	$.get(_path+"/mDevice/getList", {} ,function(result,status){
		if(!!result&&!!result.data){
			deviceArray = result.data;
		}
	},"json");
	return deviceArray;
}

function initlddm () {
	var html = '<option value=''>--选择楼栋--</option>';
	$.get(_path+"/mAp/getLddmList",function(result,status){
		if(!!result){
			result.forEach(function(item,index){
				html += "<option value='"+item.lddm+"'>"+item.ldmc+"</option>";
			});
			$("#lddm").html(html);
		}
  	},"json");
}

function changelddm(){
	var a = $("#iscon").val();
	console.log(a);
	if(a == 0){
		$("#lddmtr").hide();
	}else{
		$("#lddmtr").show();
	}
}
