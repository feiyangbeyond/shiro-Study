$(function () {

    $("#loginBtn").click(function(){
	    if(validator()){
	    	return;
	    }
	    if(!checkForm()){
    		return;
    	}
    	login();
    });

});

//回车键登录
$(document).keyup(function(event){
	  if(event.keyCode ==13){
	    $("#loginBtn").trigger("click");
	  }
});

//防止重放攻击
function checkForm() {
	var pwdipt = $("#password");
	var pwdvalue = pwdipt.val().replace(/(^\s*)|(\s*$)/g, "");
	pwdvalue = md5(pwdvalue);
	// 加密后的密码进行第二次加密（可以解密的加密）
	var uuidsalt = $("#uuidsalt").val();
	pwdvalue = encrypt(pwdvalue, uuidsalt, uuidsalt);
	// alert(pwdipt.value + "," + pwdipt.value.length);
	pwdipt.val(pwdvalue);
	// alert(pwdvalue);
	if (pwdipt.val().length == 44) {
		return true;
	} else {
		return false;
	}
}
//JS调用加密工具方法
function encrypt(data, key, iv) { // key，iv：16位的字符串
	var key1 = CryptoJS.enc.Latin1.parse(key);
	var iv1 = CryptoJS.enc.Latin1.parse(iv);
	return CryptoJS.AES.encrypt(data, key1, {
		iv : iv1,
		mode : CryptoJS.mode.CBC,
		padding : CryptoJS.pad.ZeroPadding
	}).toString();
}

//新增或者修改用户
function login(){
	var url = basePath+ "sys/login";
	var userName = $("#userName").val().replace(/(^\s*)|(\s*$)/g, "");
	var password = $("#password").val();
	var rememberMe = $('#rememberMe').prop('checked');
	$.ajax({
		type:"POST",
		url:url,
//		contentType : "application/json",
		dataType: "json",
		data: {
			"userName":userName,
			"password":password,
			"rememberMe":rememberMe
		},
		success: function(res) {
			console.log(res);
			if (res.code === 0) {
				callback();
//				layerAlert(6,"登录成功!");
			} else {
				layerAlert(5,res.msg);
			}
		}
	});
}



//-----------------

function getLoginInfo(){
	
	var loginInfo = {};
	loginInfo.userName = $("#userName").val();
	loginInfo.password = $("#password").val();
	loginInfo.rememberMe = $('#rememberMe').prop('checked'); 
	return loginInfo;
}

//提示信息
function showMsg(tip){
	$("#errMsg").text(tip);
}

//弹窗提示
function layerAlert(icon,msg){
	layer.alert(msg, {
		icon : icon,
		skin : 'layer-ext-moon'
	},function(){
		callback();
	});
}

//回调函数
function callback(){
	location.reload();
}

//判断为空
isBlank = function(obj){
    return(!obj || $.trim(obj) === "");
}

//验证(demo)
function validator(){
	if (isBlank($("#userName").val())){
		showMsg("用户名不能为空");
		return true;
	}
	
	if (isBlank($("#password").val())) {
		showMsg("密码不能为空");
		return true;
	}
	
}


