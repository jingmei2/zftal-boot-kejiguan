//初始化开始

    var map = new AMap.Map('container', {
		resizeEnable: true,
		zoom:15,
		center: [119.636423,29.062591]
        //center: [120.31433,30.261329]
	});
	
	//在地图中添加MouseTool插件
    var mouseTool = new AMap.MouseTool(map);
    AMap.event.addDomListener(document.getElementById('polygon'), 'click', function() {
        mouseTool.polygon();
    }, false);
    
    //提交多边形区域经纬度数据
    AMap.event.addDomListener(document.getElementById('savePolygon'), 'click', function() {
    	openPolygonEditModal();
    	//postPolygonlnglat(lnglatArrayStr);
    }, false);
    
    //获取多边形区域列表
   /* AMap.event.addDomListener(document.getElementById('getPolygonListForTable'), 'click', function() {
    	getPolygonListForTable();
    }, false);*/
    
    
    //帮助提示信息
    AMap.event.addDomListener(document.getElementById('helpInfo'), 'click', function() {
    	openHelpInfo();
    }, false);
    
    //切换到水博园中心点switchCenterPointBtn
    /*
    AMap.event.addDomListener(document.getElementById('switchCenterPointBtn'), 'click', function() {
    	 //alert(123);
    	 var marker = new AMap.Marker({
    			icon: _path+"/assets/images/mapjzy.png",
    			title:'水博园',
    		    position: [120.31433,30.261329]
    	 });
    	 marker.setMap(map);
    	 var sbyCenterArr = [120.31433,30.261329];
    	 map.setCenter(sbyCenterArr);
    }, false);
    */
    
    //重新定位中心点
    AMap.event.addDomListener(document.getElementById('searchBtn'), 'click', function() {
        var userName = document.getElementById('userName').value;
        //alert(userName);
        //根据账号获取定位，重新设置中心点
  	    $.get(_path+"/mDevice/getDeviceInfo?userName="+userName,function(result,status){
		  if(!!result.data){
			 console.log(result.data);
			 var deviceInfo = result.data;
			 if(!!deviceInfo&&!!deviceInfo.locInfo){
				 var localInfoArr = deviceInfo.locInfo.split(",");
				 map.setCenter(localInfoArr);
			 }
		  }
	    },"json");
  	  
    }, false);
    
    var lnglatArrayStr = '';//多边形区域经纬度串
    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = map.on('click', function(e) {
        //document.getElementById("lnglat").value = ;
        //console.log(e.lnglat.getLng() + ',' + e.lnglat.getLat());
        lnglatArrayStr += (e.lnglat.getLng() + ',' + e.lnglat.getLat()+';');
        //console.log(lnglatArrayStr);
    });
    
    
    
	 //水博园maker
     /*
	 var marker = new AMap.Marker({
		icon: _path+"/assets/images/mapjzy.png",
		title:'水博园',
	    position: [120.31433,30.261329]
	 });
     */
	
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
    
    //获取经纬度数据绘制多边形区域
    getPolygonList(map);
    
    //初始化地图点数据
    initDevicesLocInfo(map);
  
    //定时任务，刷新地图上的点数据
    window.setInterval(function(){
    	//先清除所有的标注，再重新标注
    	map.remove(allMarkers);
    	allMarkers = [];
    	//console.log(allMarkers);
		var deviceArray = [];
		$.get(_path+"/mDevice/getList", {status : 1} ,function(result,status){
	  		if(!!result&&!!result.data){
			  deviceArray = result.data;
			  
			  if(!!deviceArray){
					deviceArray.forEach(function(item,index){
						if(!!item.locInfo){
							var jingweidu = item.locInfo.split(",");
							
							marker = new AMap.Marker({
								icon: _path+"/assets/images/blueMaker.png",
								title: "账号:"+item.userName+',姓名:'+item.xm,
								position: jingweidu
							});
							//检查点是否在多边形区域内
							if(polygonListForCheckMarkers.length>0){
								polygonListForCheckMarkers.forEach(function(itm,idx){
									if(itm.contains(jingweidu)){
										marker = new AMap.Marker({
											icon: _path+"/assets/images/redMaker.png",
											title: "账号:"+item.userName+',姓名:'+item.xm,
											position: jingweidu
										});
										
										//提交预警数据
										/*
										var title = polygonList[idx].title;
										var username = item.userName;
										var polygonId = polygonList[idx].polygonId;
										var warnLevel = polygonList[idx].warnLevel;
										$.post(_path+"/warning/insert",{
											"place":title,
											"username":username,
											"polygonId":polygonId,
											"warnLevel":warnLevel
										},function(result,status){
										    
										});
										*/
									}
								});
							}
							allMarkers.push(marker);
							marker.setMap(map);
						}
					});
				}
	  		}
	  	},"json");
	},30000);
  //初始化结束
    
    //初始化楼栋信息
    initlddm();
    
    //初始化日历
    jQuery(function($) {
	laydate.render({ 
		elem: '#searchDate', //或 elem: document.getElementById('test')、elem: lay('#test') 等
		value: new Date(),
		done: function(value, date, endDate){
		    //console.log(value);
			$(".sssj-header-day").each(function(index,element){
				 $(element).removeClass('sssj-header-day-selected');
			});
			var yjTypeSelected = $(".selected-background").attr("id");
			
			//getChartData(value,yjTypeSelected);
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

    
    });
    
  //初始化地图点数据
  var allMarkers = [];  
  function initDevicesLocInfo(map){
	if(!!allMarkers){
		//先清除所有的标注，再重新标注
		map.remove(allMarkers);
		allMarkers = [];
	}
	  
  	var deviceArray = [];
  	$.get(_path+"/mDevice/getList", {status : 1} ,function(result,status){
  		if(!!result&&!!result.data){
		  deviceArray = result.data;
		  
		  if(!!deviceArray){
				deviceArray.forEach(function(item,index){
					if(!!item.locInfo){
						var jingweidu = item.locInfo.split(",");
						
						marker = new AMap.Marker({
							icon: _path+"/assets/images/blueMaker.png",
							title: "账号:"+item.userName+',姓名:'+item.xm,
							position: jingweidu
						});
						//检查点是否在多边形区域内
						if(polygonListForCheckMarkers.length>0){
							polygonListForCheckMarkers.forEach(function(itm,idx){
								if(itm.contains(jingweidu)){
									marker = new AMap.Marker({
										icon: _path+"/assets/images/redMaker.png",
										title: "账号:"+item.userName+',姓名:'+item.xm,
										position: jingweidu
									});
									
									//提交预警数据
									/*
									var title = polygonList[idx].title;
									var username = item.userName;
									var polygonId = polygonList[idx].polygonId;
									var warnLevel = polygonList[idx].warnLevel;
									$.post(_path+"/warning/insert",{
										"place":title,
										"username":username,
										"polygonId":polygonId,
										"warnLevel":warnLevel
									},function(result,status){
									    
									});
									*/
								}
							});
						}	
						allMarkers.push(marker);
						marker.setMap(map);
					}
				});
			}
  		}
  	},"json");
  }
  
  //打开多边形区域编辑模态框
  function openPolygonEditModal(){
	  $('#editPolygonModal').modal('show');
  }
  
  function savePolygon(){
	  if(!!lnglatArrayStr==false){
		  alert("请先绘制好区域再进行提交！");
		  return false;
	  }
	  var title = $("#title").val();
	  if(!!title==false){
		  alert("标题不能为空！");
		  return false;
	  }
	  var description = $("#description").val();
	  if(!!description==false){
		  alert("备注不能为空！");
		  return false;
	  }
	  var warnLevel = $("#warnLevel").val();
	  if(!!warnLevel==false){
		  alert("等级不能为空！");
		  return false;
	  }
	  var startTime = $("#startTime").val();
	  if(!!startTime==false){
		  alert("开始时间不能为空！");
		  return false;
	  }
	  var endTime = $("#endTime").val();
	  if(!!endTime==false){
		  alert("结束时间不能为空！");
		  return false;
	  }
	  var color = $("#color").val();
	  if(!!color==false){
		  alert("颜色不能为空！");
		  return false;
	  }
	  var iscon = $("#iscon").val();
	  if(!!iscon==false){
		  alert("类型不能为空！");
		  return false;
	  }
	  if(iscon == 1){
		  var lddm = $("#lddm").val();
		  if(!!lddm==false){
			  alert("楼栋不能为空！");
			  return false;
		  }
	  }
	  $.post(_path+"/mDevice/submitPolygonLnglat", {
		      "lnglatStr":lnglatArrayStr.substring(0,lnglatArrayStr.length-1),
		      "title":title,
		      "description":description,
		      "warnLevel":warnLevel,
		      "startTime":startTime,
		      "endTime":endTime,
		      "color":color,
		      "iscon":iscon,
		      "lddm":$("#lddm").val(),
		      } ,function(result,status){
		  //console.log(result);
		  if(!!result){
			$('#editPolygonModal').modal('hide');
			window.location.reload();
		  }
	  },"json");
  }
  
  //提交多边形经纬度串
  function postPolygonlnglat(lnglatStr){
	  if(!!lnglatArrayStr==false){
		  alert("请先绘制好区域再进行提交！");
		  return false;
	  }
	  var proInfor = prompt("请填写信息","");
	  if(proInfor!=null&&proInfor!=""){
		  $.post(_path+"/mDevice/submitPolygonLnglat", {"lnglatStr":lnglatStr.substring(0,lnglatStr.length-1),"description":proInfor} ,function(result,status){
			  //console.log(result);
			  if(!!result){
				window.location.reload();
			  }
		  },"json");
	  }
  }
  
  //获取多边形数据
  var polygonListForCheckMarkers = [];
  var polygonList = [];
  function getPolygonList(map){
	  $.post(_path+"/mDevice/getPolygonList", {} ,function(result,status){
		   //console.log(result);
		  if(!!result.data){
			polygonList = result.data;
			polygonList.forEach(function(item,index){
				var lnglatStr = item.lnglatStr;
				var lnglatArray = lnglatStr.split(';');
				
				//绘制多边形到地图
				var polygonArr = new Array();//多边形覆盖物节点坐标数组
				lnglatArray.forEach(function(item,index){
					var lnglat = item.split(',');
					polygonArr.push(lnglat);
				});
				
				var  polygon = new AMap.Polygon({
					path: polygonArr,//设置多边形边界路径
					strokeColor: "#FF0000", //线颜色
					strokeOpacity: 0.2, //线透明度
					strokeWeight: 3,    //线宽
					fillColor: item.color, //填充色
					fillOpacity: 0.35//填充透明度
				});
				polygon.setMap(map);
				
				polygonListForCheckMarkers.push(polygon);
			});
			
		  }
	  },"json");
  }
  
  //查看地图区域列表
  function getPolygonListForTable(){
	  $('#myModal').modal('show');
	  $.post(_path+"/mDevice/getPolygonList", {} ,function(result,status){
		   //console.log(result);
		  if(!!result.data){
		    var polygonList = result.data;
		    var trStr = "<tr><th>标题</th><th>等级</th><th>颜色</th><th>备注</th></tr>";
		    polygonList.forEach(function(item,index){
		    	var polygonId = item.polygonId;
				var title = item.title;
				var warnLevel = item.warnLevel;
				var color = item.color;
				var description = item.description;
				trStr += "<tr><td>"+title+"</td><td>"+warnLevel+"</td><td><span style='background-color:"+color+";'>&nbsp;&nbsp;&nbsp;&nbsp;</span></td><td>"+description+"</td></tr>";
		    });
		    $('#polygonArrTable').html(trStr);
		  }
	  },"json");
  }
  
  //操作提示
  function openHelpInfo() {
      //构建信息窗体中显示的内容
      var info = [];
      info.push("<div style='padding:0px 0px 0px 4px;'><div style='font-size:18px;color:#4a89dc;'>预警区域设置说明</div><br/>");
      info.push("<div><strong>绘制区域</strong></div>");
      info.push("<div>点击鼠标左键，绘制预警区域，绘制完成区域后点击鼠标右键结束绘制。</div><br/>");
      info.push("<div><strong>保存区域</strong></div>");
      info.push("<div>完成区域绘制后，点击保存区域，完成区域设置。</div>");
      infoWindow = new AMap.InfoWindow({
          content: info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
      });
      infoWindow.open(map, map.getCenter());
  }

  //切换中心点
  function switchCenterPoint(map){
	  
  }
  
  function initlddm () {
		var html = "<option value=''>--选择楼栋--</option>";
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
			$("#kkk").hide();
		}else{
			$("#lddmtr").show();
			$("#kkk").show();
		}
	}
	
	
	var path = [
	    /*new AMap.LngLat("11.368904","39.913423"),
	    new AMap.LngLat("11.382122","39.901176"),
	    new AMap.LngLat("116.387271","39.912501"),
	    new AMap.LngLat("116.398258","39.904600")*/
	];
	// 创建折线实例
	var polyline;
function drawPoly(){
	path = [];
	 var userName = $("#userName").val();
	 console.log("userName = "+userName)
	if(!!userName == false){
		alert("请输入学号");
		return false;
	}
	var daySelectedHere = $(".sssj-header-day-selected").attr("id");
	$.ajax({
		type:"post",
		url:"/mDevice/locations",
		data:{
			daySelected:daySelectedHere,
			username:userName,
		},
		success:function(data){
			if(data.code == 1){
				var x = data.data.x;
				var y = data.data.y;
				for(var i = 0;i < x.length;i++){
					path.push( new AMap.LngLat(x[i],y[i]));
				}
				console.log(path);
	
				polyline = new AMap.Polyline({
				    path: path,  
				    borderWeight: 2, // 线条宽度，默认为 1
				    strokeColor: 'red', // 线条颜色
				    lineJoin: 'round' // 折线拐点连接处样式
				});
				
				map.add(polyline);
			}else{
				alert("信息有误");
			}
		}
	});
	
	// 将折线添加至地图实例
	/*path.push( new AMap.LngLat("11.368904","39.913423"));
	path.push( new AMap.LngLat("11.382122","39.901176"));
	path.push( new AMap.LngLat("116.387271","39.912501"));
	path.push( new AMap.LngLat("116.398258","39.904600"));*/
	
}

function delPoly(){
	map.remove(polyline);	
}
