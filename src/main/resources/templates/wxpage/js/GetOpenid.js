var code = GetQueryString('code');
var openid = getCookie('openid');
var ip_addr = "127.0.0.1";
var ipad = encodeURIComponent(document.location.href);
console.log(ipad)
if(!openid) {
	console.log("判断是否已有openid");
	if(code == null || code == 'undefined') {
		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8b0c08eb508e8da2&redirect_uri=" + ipad + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	} else {
		GetUserOpenID();
	}

	function GetUserOpenID() {
		console.log(code);
		$.ajax({
			dataType: "json",
            type: 'post',
            url:'/wx/getOpenId',
			data:{
				code:code
			},
			success: function(data) {
				if(data.code == 1) {
					var openid = data.data;
					setCookie("openid", openid);
				}
			}
		});
	}
	} else {
	// getUserIsFocus(openid);
}

//获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");

	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}

//写cookies 

function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    //exp.setTime(exp.getTime() + 30 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//读取cookies 
function getCookie(name) {

    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

    if (arr = document.cookie.match(reg))

    return unescape(arr[2]);
    else
        return null;
}

//删除cookies 
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();


}
