//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height()-100);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();


var pasData = {
	password : "",
	newPassword : "",
	confirmPassword : ""
};


$(function() {
	$("#btnPassword").click(function(){
		setPasData();
		if (validator()) {
			return;
		}
		if(!checkForm()){
			return;
		}
		var password = $("#password").val();
		var newPassword = $("#newPassword").val();
		var confirmPassword = $('#confirmPassword').val();
		$.ajax({
			type:"POST",
			url:"sys/user/password",
			dataType: "json",
			data: {
				"password":password,
				"newPassword":newPassword,
				"confirmPassword":confirmPassword
			},
			success:function(res){
				if (res.code === 0) {
					layerAlert(6,"修改成功");
					$('#modal-password').modal('hide');
					clearPasData();
				} else {
					layerAlert(5,res.msg);
					$('#modal-password').modal('hide');
					clearPasData();
				}
			}
		});
	});

	
});

//将密码加密后发送给后台
function checkForm() {
	var pwdipt = $("#password");
	var pwdvalue = pwdipt.val().replace(/(^\s*)|(\s*$)/g, "");
	pwdvalue = md5(pwdvalue);
	pwdipt.val(pwdvalue);
	var newpwdipt = $("#newPassword");
	var newpwdvalue = newpwdipt.val().replace(/(^\s*)|(\s*$)/g, "");
	newpwdvalue = md5(newpwdvalue);
	newpwdipt.val(newpwdvalue);
	var confirmpwdipt = $("#confirmPassword");
	var confirmpwdvalue = confirmpwdipt.val().replace(/(^\s*)|(\s*$)/g, "");
	confirmpwdvalue = md5(confirmpwdvalue);
	confirmpwdipt.val(confirmpwdvalue);
	if (pwdipt.val().length == 32 && newpwdipt.val().length == 32 && confirmpwdipt.val().length == 32) {
		return true;
	}else{
		return false;
	}
}

//显示点击状态
function loadFrame(obj) {
	var url = obj.contentWindow.location.href;
	//var index = url.lastIndexOf("\/");
	//var str = url.substring(index + 1, url.length);
	var str = url.substr(url.lastIndexOf('/', url.lastIndexOf('/') - 1) + 1);
	//导航菜单展开
	$(".treeview").removeClass("active");
	$(".treeview-menu li").removeClass("active");
    $("a[href$='"+str+"']").parents("li").addClass("active");
    
    var nav = $("a[href$='"+str+"'] span").text();
    $("#navTitle").text(nav.length==0?"Welcome.":nav);
	
}



function setPasData(){
	pasData.password = $("#password").val();
	pasData.newPassword = $("#newPassword").val();
	pasData.confirmPassword = $("#confirmPassword").val();
}

function clearPasData(){
	pasData.password = $("#password").val("");
	pasData.newPassword = $("#newPassword").val("");
	pasData.confirmPassword = $("#confirmPassword").val("");
}

//验证(demo)
function validator(){
	
	if ($("#password").val().length==0){
		layerAlert(0,"旧密码不能为空");
		return true;
	}
	if ($("#newPassword").val().length==0){
		layerAlert(0,"新密码不能为空");
		return true;
	}
	if($("#confirmPassword").val().length==0){
		layerAlert(0,"确认密码不能为空");
		return true;
	}
}

//弹窗提示
function layerAlert(icon,msg){
	layer.alert(msg, {
		icon : icon,
		skin : 'layer-ext-moon'
	});
}
