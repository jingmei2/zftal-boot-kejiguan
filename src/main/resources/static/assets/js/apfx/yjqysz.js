$(function(){
	getTableData();
	
	
	$("#yjqyTable").on('click','input',function(){  
		   var v=this.value;  
		   $("input[type='checkbox']").each(function(){   
		   if($(this).val()!=v){  
		      $(this).removeAttr("checked");//取消选中  
		   }  
	   });  
	});  
	
});


//加载表格数据
function getTableData(){
	var title = $("#title").val();
	$.post(_path+"/mDevice/getPolygonList", {"title":title} ,function(result,status){
		   //console.log(result);
		  if(!!result.data){
		    var polygonList = result.data;
		    var trStr = "";
		    polygonList.forEach(function(item,index){
		    	var polygonId = item.polygonId;
				var title = item.title;
				var warnLevel = item.warnLevel;
				var color = item.color;
				var description = item.description;
				trStr += "<tr><td><input name='polygonId' type='checkbox' value='"+polygonId+"'/></td><td>"+title+"</td><td>"+warnLevel+"</td><td><span style='background-color:"+color+";'>&nbsp;&nbsp;&nbsp;&nbsp;</span></td><td>"+description+"</td></tr>";
		    });
		    $('#yjqyTable tr:gt(0)').remove();
		    $('#yjqyTable').append(trStr);
		  }
	  },"json");
}

//修改区域
function updatePolygonModal(){
	var polygonIdChecked = $("input[name=polygonId]:checked").val();
	if(!!polygonIdChecked==false){
		alert("请先选择一条记录!");
		return;
	}
	$('#editPolygonModal').modal('show');
	$.get(_path+"/mDevice/getPolygonById",{"polygonId":polygonIdChecked},function(result,status){
		if(!!result.data){
			var model = result.data;
			$("#polygonId").val(model.polygonId);
			$("#titleForUpdate").val(model.title);
			$("#description").val(model.description);
			$("#warnLevel").val(model.warnLevel);
			$("#startTime").val(model.startTime);
			$("#endTime").val(model.endTime);
			$("#color").val(model.color);
		}
	},"json");
}

function updatePolygon(){
	  var polygonId = $("#polygonId").val();
	
	  var title = $("#titleForUpdate").val();
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
	  $.post(_path+"/mDevice/updatePolygon", {
		      "polygonId":polygonId,
		      "title":title,
		      "description":description,
		      "warnLevel":warnLevel,
		      "startTime":startTime,
		      "endTime":endTime,
		      "color":color,
		      } ,function(result,status){
		  //console.log(result);
		  if(!!result){
			$('#editPolygonModal').modal('hide');
			window.location.reload();
		  }
	  },"json");
}

//删除区域
function deletePolygon(){
	var polygonIdChecked = $("input[name=polygonId]:checked").val();
	if(!!polygonIdChecked==false){
		alert("请先选择一条记录!");
		return;
	}
	
	//alert(polygonIdChecked);
	if(confirm("确认删除吗?")){
		$.post(_path+"/mDevice/deletePolygon", {"polygonId":polygonIdChecked} ,function(result,status){
			//console.log(result);
			if(!!result.code&&result.code==1){
				getTableData();
			}
		},"json");
	}
}