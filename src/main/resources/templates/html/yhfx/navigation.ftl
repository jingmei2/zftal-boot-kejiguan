<!DOCTYPE html>
<html lang="zh_CN">
<head>
	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-echarts3.ftl" /]
	[#include "/head/zftal-ui-required.ftl" /]
	<!--首页专用的额外引用-->
	<script type="text/javascript">
		//全局变量
		var _path = "${request.contextPath}";
		var _systemPath = "${request.contextPath}";
	</script>

	[#include "/head/zftal-ui-meta.ftl" /]
	[#include "/head/zftal-ui-required.ftl" /]

	<!-- 应用系统自定义样式 -->

	<link href="${request.contextPath}/assets/css/zftal-ui-app.css?ver=${versionUtil()}" rel="stylesheet" type="text/css" />

	<link href="http://dcloud.io/hellomui/css/mui.min.css" />
	<!--<link href="${request.contextPath}/assets/css/datetimepicker.css"/>-->
	<!-- 该页面单独样式 -->

</head>
<body style="background: #fff;">
<div class="main-content">
	<div class="sssj-xssj">
		<div class="sssj-xssj-data">

			<div class="cz" style="padding:15px;position: fixed;width: 100%;height: 130px;background: #fff;z-index: 10000;">
				<span class="glyphicon glyphicon-option-vertical" aria-hidden="true" style="display:block;font-size: 16px;display: block;line-height: 50px;">详细数据</span>
				<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">新增图片</button>
				<button class="xiugai" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">修改图片</button>
				<button class="shanchu" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">删除图片</button>
				<div class="mask" style="display:none;background: #000;width: 100%;height: 100%;position: fixed;z-index: 100;opacity: 0.68;top: 0;left: 0;"></div>
				<div class="add" style="display:none;width: 400px;position: fixed;top: 10%;left: 50%;box-shadow: 1px 0px 1px 1px #ccc;background: #fff;transform: translateX(-80%);z-index: 100;border-radius: 6px;padding: 20px;">
				    <form id="form" enctype="multipart/form-data">
					    <label class="formTitle" style="font-size: 20px;font-weight: bold;color: #0269f5;display: block;border-bottom: 1px solid #f5f5f5;line-height: 45px;"></label>
				        <div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;">上传图片</label>
				            <input type="hidden" id="state" name="state" value=""/>
				            <img id="showImg" src="" style="display:none;width: 160px;height: 120px;background: #000;vertical-align: bottom;">
				            <div style="width: 92px;height: 45px;position: relative;display: inline-block;">
				            	<input type="hidden" id="img" name="picPath"/>
					        	<input name="faceLegalFile" class="submit" style="color:#fff;border-radius:6px;font-size:16px;border: 0;display: inline-block;height: 45px;position: relative;z-index: 100;width: 92px;opacity: 0;" type="file" id="file" onchange="changepic(this)" accept="image/jpg,image/jpeg,image/png,image/PNG">
					        	<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding: 0 15px;border-radius:6px;font-size: 18px;margin-right:10px;position: absolute;left: 0;line-height: 45px;width: 90px;top:0;">上传</button>
					        </div>
				        </div>
				        <div style="margin: 20px 0;">
				            <label style="width: 100px;font-size: 16px;color: #000;">图片描述</label>
				            <input name="desc" type="text" placeholder="请输入图片描述" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>
				        <div style="margin: 20px 0;">
				            <label style="width: 100px;font-size: 16px;color: #000;">图片链接</label>
				            <input name="url" type="text" placeholder="请输入图片链接" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>
					    <button class="addAct" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;"></button>
					    <button class="back" type="button" style="border:1px solid #ccc;background:#fff;color:#000;padding:10px 30px;border-radius:6px;font-size:16px;margin-right:10px;">取消</button>
					</form>
				</div>
			</div>
			<div class="sssj-xssj-xxsj" style="padding-top: 130px;">
				<table id="monthDataTable" class="table table-bordered table-striped">
					<tr class="actName">
					    <th>
							<input class="allCheck" type="checkbox">
						</th>
						<th>创建时间</th>
						<th>描述</th>
						<th>图片链接</th>
						<th>背景图片</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
		</div>

	</div>
</div>
<script src="https://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/ssyhgj.js?ver=${versionUtil()}"></script>
<script src="http://dcloud.io/hellomui/js/mui.min.js"></script>

<!--<script type="text/javascript" src="${request.contextPath}/assets/js/datetimepicker.js"></script>-->
<script>
	actList();

	//新增
	$('.xinzeng').click(function(){
		if($('body').find('.actId').attr('name')=='id'){
			$('body').find('.actId').remove();
		}
		if($('.add').is(':hidden')){
			$('.add').show().find('input').val('');
			//默认开启
			$('.mask').show();
			$('input[name="state"]').val('1');
			$('.formTitle').text('新增图片');
			$('.addAct').text('新增图片');
			$('#showImg').attr('src','').hide();
		}
	})

	//修改
	$('.xiugai').click(function(){
		var len=$("input[type=checkbox]:checked").length;
		if(len>1){
			alert('只能勾选一个活动！');
		}else if(len==0){
			alert('请勾选活动！');
		}else{
			//根据id找活动赋值
			$('.formTitle').text('修改图片');
			$('.addAct').text('修改图片');
            getData($("input[type=checkbox]:checked").val(),function(res){
            	 console.log(res)
            	 $('.add').show();
            	 $('.mask').show();
	           	 $('#showImg').attr('src',res.data.picPath).show();
            })
		}
	})

	//取消
	$('.add .back').click(function(){
		$('.add').hide();
		$('.mask').hide();
	})

    //添加/修改
	$('.addAct').click(function(){
//		$('#form').ajaxSubmit({
//			url: 'http://10.71.21.98:9097/kejiguan/saveorupdatePicForNavigation',
//          type: "Post",
//          success:function(res){
//          	alert(res.status);
//          	$('.add').hide();
//          	$('body').find('#monthDataTable tr').not('.actName').remove();
//          	actList();
//          }
//		});
		submit(function(){
			$('.add').hide();
			$('.mask').hide();
		})
	})

	function getData(id,func){

		if($('body').find('.actId').attr('name')!='id'){
			$('.add form').append('<input class="actId" type="hidden" id="activityId" name="id"/>');
		}
    	$.get("${request.contextPath}/kejiguan/selectNavigationById",{id:id},function(res){
           if(res.code==1){
           	 $('input[name="createTime"]').val(res.data.createTime);
           	 $('input[name="picPath"]').val(res.data.picPath);
           	 $('input[name="url"]').val(res.data.url);
           	 $('input[name="state"]').val(res.data.state);
           	 $('input[name="id"]').val(res.data.id);
           	 func(res);
           }
		});
    }

	function submit(func){
		$('#form').ajaxSubmit({
			url: 'http://121.43.179.186:9097/kejiguan/saveorupdatePicForNavigation',
            type: "Post",
            success:function(res){
            	console.log(res)
            	alert(res.status);
            	func();
            	if($('body').find('.actId').attr('class')=='actId'){
					$('body').find('.actId').remove();
				}
            	$('body').find('#monthDataTable tr').not('.actName').remove();
        		actList();
            }
		});
	}

	//删除
	$('.shanchu').click(function(){
		var len=$("input[type=checkbox]:checked").length;
		if(len>1){
			alert('只能勾选一个活动！');
		}else if(len==0){
			alert('请勾选活动！');
		}else{
			$.post("${request.contextPath}/kejiguan/deleteNavigationById",{
				id:$("input[type=checkbox]:checked").val()
			},function(res){
	           if(res.code==1){
	           	  window.location.reload();
	           }
			});
		}
	})

	//全选
//	$('.allCheck').click(function(){
//		if(this.checked){
//			$('#monthDataTable').find("input[type='checkbox']").attr('checked',true);
//		}else{
//			$('#monthDataTable').find("input[type='checkbox']").attr('checked',false);
//		}
//	})

	//显示图片
	function changepic(){
	   var image = '';
	   var base64;
	   var reader = new FileReader();//读取文件
	   //将文件已Data URL的形式读入页面
	   reader.readAsDataURL(document.getElementById('file').files[0]);
	   reader.onload = function(event){//文件读取完成的回调函数
		  image = document.getElementById('showImg');
		  image.style.display='inline-block';
		  image.src = event.target.result;//读入文件的base64数据(可直接作为src属性来显示图片)
	   }
	}

    //请求数据
	function actList(){
		$.post("${request.contextPath}/kejiguan/selectNavigationListById",'',
			function(data,status){
				//alert("Data: " + data + "\nStatus: " + status);
				var htm = "";
				var list = data.data;
				var check,
				    checkstatus;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					console.log(typeof(obj.state))
					if(obj.state==1){
						check='关闭';
					}else if(obj.state==0||obj.state==null || obj.state==''||obj.state==undefined || typeof(obj.state)!='string'){
						check='开启';
						obj.state=0;
					}
					obj.desc=obj.desc==null?'':obj.desc;
					obj.url=obj.url==null?'':obj.url;
					htm += "<tr><td><input type='checkbox' name='id' value="+obj.id+"></td>";
					htm += "<td>"+ obj.createTime +"</td>";
					htm += "<td>"+ obj.desc +"</td>";
					htm += "<td>"+ obj.url +"</td>";
					htm += "<td><img width='100px' src="+ obj.picPath +" ></td>";
					htm += "<td><button class='changeStatus' type='button' value="+obj.state+">"+check+"</button></td></tr>";
				}
				$("#monthDataTable").append(htm);

		});
	}

    $('body').on('click','.changeStatus',function(){
    	$('.add').find('input').val('');
    	var val=$(this).parent().parent().find('input[name="id"]').val();
     	if($('body').find('.actId').attr('name')!='id'){
			$('.add form').append('<input class="actId" type="hidden" id="activityId" name="id" value="'+val+'"/>');
		}else{
			$('body').find('.actId').val(val);
		}
     	if($(this).attr('value')=='1'){
     		$('input[name="state"]').val('0');
     	}else{
     		$('input[name="state"]').val('1');
     	}
     	submit(function(){});
    })



</script>
</body>
</html>
