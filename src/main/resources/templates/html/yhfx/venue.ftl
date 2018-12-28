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
	
	
	<!--<link href="${request.contextPath}/assets/css/datetimepicker.css"/>-->
	<!-- 该页面单独样式 -->

</head>
<body style="background: #fff;">
<div class="main-content">
	<div class="sssj-xssj">
		<div class="sssj-xssj-data">
			<span class="glyphicon glyphicon-option-vertical" aria-hidden="true" style="font-size: 16px;margin: 15px 0 0 15px;">详细数据</span>
			<div class="cz" style="padding:15px;">
				<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">新增场馆</button>
				<button class="xiugai" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">修改场馆</button>
				<button class="shanchu" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">删除场馆</button>
				<div class="form-group" style="position:relative;width:240px;display:inline-block;">
					<input name="title" type="text" id="search-input" class="form-control" placeholder="请输入关键词" style="border-radius: 4px;background: #fff;line-height:40px;height:40px;">
					<i class="fa fa-search icon-right search-icon" aria-hidden="true"></i>
				</div>
				<div class="mask" style="display:none;background: #000;width: 100%;height: 100%;position: fixed;z-index: 100;opacity: 0.68;top: 0;left: 0;"></div>
				<div class="add" style="display:none;width: 400px;position: fixed;top: 5%;left: 50%;box-shadow: 1px 0px 1px 1px #ccc;background: #fff;transform: translateX(-80%);z-index: 100;border-radius: 6px;padding: 20px;">
				    <form id="form" enctype="multipart/form-data">
					    <label class="formTitle" style="font-size: 20px;font-weight: bold;color: #0269f5;display: block;border-bottom: 1px solid #f5f5f5;line-height: 45px;"></label>
					    <div style="margin: 20px 0;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">场馆标题</label>
				            <input name="titles" type="text" placeholder="请输入活动标题" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">
				        </div>
						
						<div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;vertical-align: bottom;">场馆详情</label>
				            <textarea style="width: 245px;" name="desc" placeholder="请输入详情介绍"></textarea>
				            <!--<input name="desc" type="text" placeholder="请输入活动描述" style="border: 1px solid #cccccc;border-radius: 4px;line-height: 40px;width: 245px;box-shadow: inset 0 1px 1px rgba(0,0,0,.075);padding-left: 15px;">-->
				        </div>
				        
				        <div style="margin-bottom: 20px;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">上传图片</label>
				            <img id="showImg" src="" style="display:none;width: 160px;height: 120px;background: #000;vertical-align: bottom;">
				            <div style="width: 92px;height: 45px;position: relative;display: inline-block;">
				            	<!--<input type="hidden" id="img" name="picPath"/>-->
					        	<input name="faceLegalFile" class="submit" style="color:#fff;border-radius:6px;font-size:16px;border: 0;display: inline-block;height: 45px;position: relative;z-index: 100;width: 92px;opacity: 0;" type="file" id="file" onchange="changepic(this)" accept="image/jpg,image/jpeg,image/png,image/PNG">
					        	<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding: 0 15px;border-radius:6px;font-size: 18px;margin-right:10px;position: absolute;left: 0;line-height: 45px;width: 90px;top:0;">上传</button>
					        </div>
				        </div>
				        <div style="margin: 20px 0;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">上传音频</label>
				            <audio controls src="" id="showAudio" style="width: 150px;vertical-align: bottom;margin-right: 10px;">您的浏览器不支持 audio 标签。</audio>
				            <div style="width: 92px;height: 50px;position: relative;display: inline-block;">
					        	<!--<input type="hidden" id="img" name="voicePath"/>-->
					        	<input name="voice" class="submit" style="color:#fff;border-radius:6px;font-size:16px;border: 0;display: inline-block;height: 50px;position: relative;z-index: 100;width: 92px;opacity: 0;" type="file" id="file1" onchange="changeAudio(this)" accept="audio/*">
					        	<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding: 0 15px;border-radius:6px;font-size: 18px;margin-right:10px;position: absolute;left: 0;line-height: 45px;width: 90px;top:0;">上传</button>
					        </div>
				        </div>
				         <div style="margin: 20px 0;">
				            <label style="width: 100px;font-size: 16px;color: #000;display: inline-block;line-height: 40px;">上传视频</label>
				            <video src="" id="showVideo" width="150px" height="150px" style="vertical-align: bottom;margin-right: 10px;" controls="controls"></video>
				            <div style="width: 92px;height: 50px;position: relative;display: inline-block;">
					        	<input type="hidden" id="img" name="videoPath"/>
					        	<input name="video" class="submit" style="color:#fff;border-radius:6px;font-size:16px;border: 0;display: inline-block;height: 50px;position: relative;z-index: 100;width: 92px;opacity: 0;" type="file" id="file2" onchange="changeVideo(this)" accept="video/*">
					        	<button class="xinzeng" type="button" style="border:0;background:#0269f5;color:#fff;padding: 0 15px;border-radius:6px;font-size: 18px;margin-right:10px;position: absolute;left: 0;line-height: 45px;width: 90px;top:0;">上传</button>
					        </div>
				        </div>
					    </form>
					    <button class="addAct" type="button" style="border:0;background:#0269f5;color:#fff;padding:10px 15px;border-radius:6px;font-size:16px;margin-right:10px;">新增场馆</button>
					    <button class="back" type="button" style="border:1px solid #ccc;background:#fff;color:#000;padding:10px 30px;border-radius:6px;font-size:16px;margin-right:10px;">取消</button>
					
				</div>
			</div>
			<div class="sssj-xssj-xxsj">
				<table id="monthDataTable" class="table table-bordered table-striped">
					<tr class="actName">
					    <th>
							<input class="allCheck" type="checkbox">
						</th>
						<th>场馆介绍</th>
						<th>预定时间</th>
						<th>音频</th>
						<th>视频</th>
						<th>场馆详情</th>
						<th>场馆图片</th>
					</tr>
				</table>
			</div>
		</div>

	</div>
</div>
<script src="${request.contextPath}/assets/js/jquery.form.js"></script>
<script type="text/javascript" src="${request.contextPath}/assets/js/yhfx/ssyhgj.js?ver=${versionUtil()}"></script>
<script src="${request.contextPath}/assets/js/jquery.min.js"></script>
<!--<script type="text/javascript" src="${request.contextPath}/assets/js/datetimepicker.js"></script>-->
<script>
	var tip;
	actList();
	//新增
	$('.xinzeng').click(function(){
		if($('body').find('.actId').attr('class')=='actId'){
			$('body').find('.actId').remove();
		}
		if($('.add').is(':hidden')){
			tip=1;
			$('.mask').show();
			$('.add').show().find('input').val('');
			$('.add').find('textarea').val('');
			$('.formTitle').text('新增场馆');
			$('.addAct').text('新增场馆');
			$('#showImg').attr('src','').hide();
			$('#showAudio').attr('src','').hide();
			$('#showVideo').attr('src','').hide();
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
			$('.formTitle').text('修改场馆');
			$('.addAct').text('修改场馆');
			if($('body').find('.actId').attr('name')!='id'){
				$('.add form').append('<input class="actId" type="hidden" id="activityId" name="id"/>');
			}
			$.get("http://10.71.19.166:9097/kejiguan/selectVenueById",{
				id:$("input[type=checkbox]:checked").val()
			},function(res){
	           if(res.code==1){
	           	 tip=2;
	           	 $('.add').show();
	           	 $('.mask').show();
	           	 $('input[name="titles"]').val(res.data.titles);
	           	 $('textarea[name="desc"]').val(res.data.desc);
	           	 $('input[name="picPath"]').val(res.data.picPath);
	           	 $('input[name="id"]').val(res.data.id);
	           	 $('#showImg').attr('src',res.data.picPath).show();
	           	 $('#showAudio').attr('src',res.data.voicePath).show();
	           	 $('#showVideo').attr('src',res.data.videoPath).show();
	           }
			});
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
//			url: 'http://portal.zfsoft.com:9098/kejiguan/saveorupdatePicForVenue',
//          type: "Post",
//          timeout:60000,  
//          success:function(res){
//          	alert(res.status);
//          	$('.add').hide();
//          	$('.mask').hide();
//          	$('body').find('#monthDataTable tr').not('.actName').remove();
//          	actList();
//          },
//          fail:function(err){
//          	console.log(err)
//          }
//		});
		if(!window.FormData) {　
	        alert('your brower is too old');
	        return false;
	    }
		var formData=new FormData(document.getElementById('form'));
		console.log(formData.get('video'))
		console.log(formData.get('audio'))
		console.log(formData.get('faceLegalFile'))
		$.ajax({
			url:"http://10.71.19.166:9097/kejiguan/saveorupdatePicForVenue",
			type:"POST",
			data:formData,
			dataType: "JSON",
		    cache: false,
			contentType:false,
			processData:false,
			success:function(res){
				alert(res.status);
            	$('.add').hide();
            	$('.mask').hide();
            	$('body').find('#monthDataTable tr').not('.actName').remove();
            	actList();
			},
			fail:function(data){
				alert(json.stringify(data))
			}
		})
		
	})	

	//删除
	$('.shanchu').click(function(){
		var len=$("input[type=checkbox]:checked").length;
		if(len>1){
			alert('只能勾选一个活动！');
		}else if(len==0){
			alert('请勾选活动！');
		}else{
			$.post("http://10.71.19.166:9097/kejiguan/deleteVenueById",{
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
	
	function changeAudio(){
		var file = document.getElementById('file1').files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
             $('#showAudio').attr('src',reader.result).show();
        };

	}
	
	function changeVideo(){
		var file = document.getElementById('file2').files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
             $('#showVideo').attr('src',reader.result).show();
        };
	}

    //请求数据
	function actList(){
		$.post("http://10.71.19.166:9097/kejiguan/selectVenueListById",
			'',
			function(data,status){
				//alert("Data: " + data + "\nStatus: " + status);
				var htm = "";
				var list = data.data;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					obj.titles=obj.titles==null?'':obj.titles;
					obj.desc=obj.desc==null?'':obj.desc;
					htm += "<tr><td><input type='checkbox' name='id' value="+obj.id+"></td>";
					htm += "<td>"+ obj.titles +"</td>";
					htm += "<td>"+ obj.createTime +"</td>";
					htm += "<td><audio style='width:240px' controls src="+ obj.voicePath +" >您的浏览器不支持 audio 标签。</audio></td>";
					htm += "<td><video controls src="+ obj.videoPath +" ></video></td>";
					htm += "<td>"+ obj.desc +"</td>";
					htm += "<td><img width='100px' src="+ obj.picPath +" ></td></tr>";
				}
				$("#monthDataTable").append(htm);

		});
	}

</script>
</body>
</html>
