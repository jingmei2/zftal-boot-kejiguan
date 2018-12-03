/*
 * 业务框架全局Ajax设置：详情参见：jquery.zftal.settings-1.0.0.js
 */
;(function(b){var a={};b.ajaxSetup({abortOnRetry:true,cache:false,contentType:"application/x-www-form-urlencoded;charset=utf-8",statusCode:{400:function(){a={};if(b("#statusModal").size()>0){return}},401:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u8bbf\u95ee\u88ab\u62d2\u7edd\uff0c\u8bd5\u56fe\u672a\u7ecf\u6388\u6743\u8bbf\u95ee\u53d7\u5bc6\u7801\u4fdd\u62a4\u7684\u9875\u9762!",function(){window.close()},{modalName:"statusModal"})},404:function(){a={};if(console&&console.error){console.error("\u8bf7\u6c42\u4e0d\u5b58\u5728\u7684\u8d44\u6e90\u6216\u7f51\u9875!")}},408:function(){if(b("#statusModal").size()>0){return}b.alert("\u670d\u52a1\u5668\u7b49\u5019\u8bf7\u6c42\u65f6\u53d1\u751f\u8d85\u65f6,\u8bf7\u68c0\u67e5\u7f51\u7edc\u8fde\u63a5\u662f\u5426\u6b63\u786e!",function(){},{modalName:"statusModal"})},500:function(){if(b("#statusModal").size()>0){return}b.alert("\u670d\u52a1\u5668\u53d1\u751f\u9519\u8bef\uff0c\u65e0\u6cd5\u5b8c\u6210\u8bf7\u6c42!",function(){},{modalName:"statusModal"})},502:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u670d\u52a1\u5668\u4f5c\u4e3a\u7f51\u5173\u6216\u4ee3\u7406\uff0c\u4ece\u4e0a\u6e38\u670d\u52a1\u5668\u6536\u5230\u65e0\u6548\u54cd\u5e94!",function(){},{modalName:"statusModal"})},503:function(){a={};if(b("#statusModal").size()>0){return}b.alert(" \u670d\u52a1\u5668\u76ee\u524d\u65e0\u6cd5\u4f7f\u7528\uff08\u7531\u4e8e\u8d85\u8f7d\u6216\u505c\u673a\u7ef4\u62a4\uff09\u3002\u8bf7\u670d\u52a1\u542f\u52a8\u540e\u518d\u6b21\u5c1d\u8bd5!",function(){},{modalName:"statusModal"})},504:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u670d\u52a1\u5668\u4f5c\u4e3a\u7f51\u5173\u6216\u4ee3\u7406\uff0c\u8bf7\u6c42\u4e0a\u6e38\u670d\u52a1\u5668\u8d85\u65f6!",function(){},{modalName:"statusModal"})},901:function(){if(b("#statusModal").size()>0){return}b.alert("\u5f53\u524d\u767b\u5f55\u7528\u6237\u5df2\u9000\u51fa\u767b\u5f55\u6216\u8005\u4f1a\u8bdd\u5df2\u8fc7\u671f,\u8bf7\u91cd\u65b0\u767b\u5f55!",function(){top.location.href=_systemPath+"/logout"},{modalName:"statusModal"})},902:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u5f53\u524d\u7528\u6237\u767b\u5f55\u7684\u89d2\u8272\u65e0\u6b64\u529f\u80fd\u6743\u9650!",function(){window.close()},{modalName:"statusModal"})},903:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u540c\u4e00\u6d4f\u89c8\u5668\u4e2d\u53ea\u5141\u8bb8\u4e00\u4e2a\u7528\u6237\u767b\u5f55!",function(){window.close()},{modalName:"statusModal"})},904:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u8bf7\u52ff\u9891\u7e41\u5237\u65b0\u6216\u8005\u70b9\u51fb\u83dc\u5355!~",function(){},{modalName:"statusModal"})},905:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u5f53\u524d\u8bf7\u6c42\u8def\u5f84\u542f\u7528\u53c2\u6570\u503c\u4e00\u81f4\u6027\u6821\u9a8c\u8fc7\u6ee4\uff0c\u60a8\u65e0\u6743\u8bbf\u95ee\u975e\u81ea\u5df1\u6743\u9650\u5185\u7684\u6570\u636e!",function(){},{modalName:"statusModal"})},906:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u5f53\u524d\u8bf7\u6c42\u8def\u5f84\u542f\u7528IP\u5730\u5740\u767d\u540d\u5355\u8fc7\u6ee4\uff0c\u53d1\u73b0\u975e\u6cd5IP\u5ba2\u6237\u7aef\u8bbf\u95ee!",function(){},{modalName:"statusModal"})},907:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u5f53\u524d\u8bf7\u6c42\u8def\u5f84\u542f\u7528Mac\u5730\u5740\u767d\u540d\u5355\u8fc7\u6ee4\uff0c\u53d1\u73b0\u975e\u6cd5Mac\u5ba2\u6237\u7aef\u8bbf\u95ee!",function(){},{modalName:"statusModal"})},908:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u5f53\u524d\u8bf7\u6c42\u8def\u5f84\u542f\u7528\u5371\u9669\u8bbf\u95ee\u6765\u6e90\u8fc7\u6ee4\uff0c\u53d1\u73b0\u975e\u6cd5\u8bf7\u6c42\u6765\u6e90!",function(){},{modalName:"statusModal"})},909:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u5904\u7406http\u8bf7\u6c42\u7684Action\u5bf9\u8c61\u672a\u521d\u59cb\u5316!",function(){},{modalName:"statusModal"})},910:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u8bf7\u6c42\u7684\u65b9\u6cd5\u5728Action\u5bf9\u8c61\u4e2d\u672a\u5b9a\u4e49!",function(){},{modalName:"statusModal"})},911:function(){a={};if(b("#statusModal").size()>0){return}b.alert("\u5e94\u7528\u7a0b\u5e8f\u8fd0\u884c\u671f\u95f4\u53d1\u751f\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\u67e5\u770b\u5f02\u5e38\u65e5\u5fd7!",function(){},{modalName:"statusModal"})}}});b.ajaxPrefilter(function(g,l,i){if(g.abortOnRetry){var k=b.param(l.data||{});var h=b.founded(k)?((g.url||"")+((g.url||"").indexOf("?")>-1?"&":"?")+k):(g.url||"");var f=(typeof b.md5!="undefined")?b.md5(h):h;var d=a[f];if(b.defined(d)){try{if(d.readyState!=4){i.abort();if(console&&console.error){console.error("\u5df2\u5b58\u5728\u4e0e\u5f53\u524d\u8bf7\u6c42\u8def\u5f84\u548c\u53c2\u6570\u76f8\u540c\u7684\u8bf7\u6c42\u4e14\u672a\u5b8c\u6210!")}}else{a[f]=i}}catch(j){delete a[f];console.error(j)}}else{a[f]=i}}});function c(e){if(e.abortOnRetry){var g=b.isPlainObject(e.data)?b.param(e.data||{}):"";var f=b.founded(g)?((e.url||"")+((e.url||"").indexOf("?")>-1?"&":"?")+g):(e.url||"");var d=(typeof b.md5!="undefined")?b.md5(f):f;a[d]=null;delete a[d]}}b(document).ajaxSend(function(e,f,d){if(b.matchURL(d.url)){}else{if(jQuery("#gnmkdmKey").size()==1&&jQuery("#gnmkdmKey").founded()){if(b.defined(d.data)){if(b.isPlainObject(d.data)){d.data.gnmkdmKey=jQuery("#gnmkdmKey").val()}else{if(b.defined(d.url)){if(d.url.indexOf("?")>-1){d.url=d.url+"&gnmkdmKey="+jQuery("#gnmkdmKey").val()}else{d.url=d.url+"?gnmkdmKey="+jQuery("#gnmkdmKey").val()}}}}else{if(b.defined(d.url)){if(d.url.indexOf("?")>-1){d.url=d.url+"&gnmkdmKey="+jQuery("#gnmkdmKey").val()}else{d.url=d.url+"?gnmkdmKey="+jQuery("#gnmkdmKey").val()}}}}if(jQuery("#sessionUserKey").size()==1&&jQuery("#sessionUserKey").founded()){if(b.defined(d.data)){if(b.isPlainObject(d.data)){d.data.sessionUserKey=jQuery("#sessionUserKey").val()}else{if(b.defined(d.url)){if(d.url.indexOf("?")>-1){d.url=d.url+"&sessionUserKey="+jQuery("#sessionUserKey").val()}else{d.url=d.url+"?sessionUserKey="+jQuery("#sessionUserKey").val()}}}}else{if(b.defined(d.url)){if(d.url.indexOf("?")>-1){d.url=d.url+"&sessionUserKey="+jQuery("#sessionUserKey").val()}else{d.url=d.url+"?sessionUserKey="+jQuery("#sessionUserKey").val()}}}}}}).ajaxError(function(g,i,e){c(e);if(i.status==904&&b("#loading_status").size()>0){var f="\u8bf7\u52ff\u9891\u7e41\u5237\u65b0\u6216\u8005\u70b9\u51fb\u83dc\u5355,\u8bf7<label id='times' data-time='"+_refreshInterval+"'>"+_refreshInterval+"</label>\u79d2\u540e\u518d\u64cd\u4f5c!~";b("#loading_status").replaceWith('<div class="red " style="font-size: 20px;margin-top: 150px;text-align: center;" role="alert"  id="messageTip">'+f+"</div>");var h=parseInt(b("#times").data("time"));var d=window.setInterval(function(){h-=1;if(h<=0){window.clearInterval(d);b("#messageTip").html("\u8bf7\u91cd\u65b0\u5237\u65b0\u9875\u9762\u6216\u8005\u70b9\u51fb\u6309\u94ae!").removeClass("red2").addClass("green")}b("#times").text(h)},1000)}}).ajaxComplete(function(e,f,d){c(d)})})(jQuery);
/*
 * 业务框架独有全局方法：详情参见：jquery.zftal-1.0.0.js
 */
;(function(a){a.getURL=function(b,c){c=c||{};if(jQuery("#gnmkdmKey").size()==1&&jQuery("#gnmkdmKey").founded()){if(!a.defined(c.gnmkdm)&&a.defined(b)&&b.indexOf("gnmkdm")==-1){if(b.indexOf("?")>-1){b=b+"&gnmkdm="+jQuery("#gnmkdmKey").val()}else{b=b+"?gnmkdm="+jQuery("#gnmkdmKey").val()}}}if(jQuery("#sessionUserKey").size()==1&&jQuery("#sessionUserKey").founded()){if(!a.defined(c.su)&&a.defined(b)&&b.indexOf("su")==-1){if(b.indexOf("?")>-1){b=b+"&su="+jQuery("#sessionUserKey").val()}else{b=b+"?su="+jQuery("#sessionUserKey").val()}}}return b};a.openWin=function(b){top.window.open(a.getURL(b))};a.showWin=function(d,b,e,c,g){var f="";if(c==null){f="Status:YES;dialogWidth:"+b+"px;dialogHeight:"+e+"px;help:no;scroll:no"}else{f="Status:YES;dialogWidth:"+b+"px;dialogHeight:"+e+"px;help:no;scroll:yes"}if(g){window.showModalDialog(a.getURL(requestURL),window,f)}else{window.open(a.getURL(d),"","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=yes,width="+b+",height="+e+",left=100,top=100,screenX=0,screenY=0")}};a.clearIframe=function(f){var c=document.getElementById(f),b=c.contentWindow;if(c){try{c.src="about:blank";try{b.document.write("");b.document.clear()}catch(d){}document.body.removeChild(c)}catch(d){}}};a.fn.resetIndex=function(b){return a(this).each(function(){a(this).find("tr.jqgrow").each(function(c,d){a(this).find(":text,select,:hidden,textarea").each(function(){var e=a(this).attr("name");if(a.founded(e)){a(this).attr("name",e.replace(/\[\d+\]/,"["+c+"]"))}});if(a.isFunction(b)){b.call($this,c,d)}})})};a.fn.resetOrdinal=function(b){return a(this).each(function(){var c=this;a(c).find("tbody tr.jqgrow").each(function(d,e){a(this).find("td.detail-rownum").text(d+1);if(a.isFunction(b)){b.call(c,d,e)}})})};a.fn.clearTitle=function(b){return a(this).each(function(){var d=this;var c=jQuery("#"+d.id).jqGrid("getGridParam","colModel");a.each(c||[],function(e,f){if(a.isFunction(f.formatter)){a(d).find("tr.jqgrow").each(function(g,h){a(this).find("td[aria-describedby$='_"+f.name+"']").attr("title","");if(a.isFunction(b)){b.call(d,g,h)}})}})})};a.fn.newTab=function(options){options = options||{};top.$("#tabs").tabs("builTab",{id:a(this).data("addtab"),title:a(this).attr("title")?a(this).attr("title"):a(this).html(),content:options.content?options.content:a(this).attr("content"),url:a(this).data("src"),ajax:a(this).attr("ajax")?true:false,tablayout:a(this).data("tab-layout"),funclayout:a(this).data("blank-layout"),data:a(this).data("request")||{}})};a.closeTab=function(b){top.$("#tabs").tabs("close",b)};a.openReport=function(c){if(a("#statusModal").size()>0){return}c=c||{};var b=c.reportID;if(!a.founded(b)){throw new Error("reportID \u4e0d\u80fd\u4e3a\u7a7a !")}delete c.reportID;var d={};a.each(c||{},function(e,f){d["mapRow.row."+e]=encodeURIComponent(f||"")});a.buildForm("reportViewForm",_path+"/design/viewReport_cxFineReportViewIndex.html?reportID="+b+"&_t"+new Date().getTime(),d).submit()}})(jQuery);
