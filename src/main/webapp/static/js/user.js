$(function () {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,
        language: 'zh-CN'
    });
    
    $("#addUserBtn").click(function(){
    	//添加用户按钮绑定事件
    	//验证数据有效性
    	$("#addUserFrom").submit();
    });
    
    //模态框显示完毕事件
    $("#updateUserModal").on("shown.bs.modal",function(){
    	//初始化插件
    	$("#updateUserModal .selectpicker").selectpicker();
    });
    
    //模态框隐藏事件
    $("#updateUserModal").on("hidden.bs.modal",function(){
    	$(this).removeData("bs.modal");//清空编辑模态框中的数据
    });
    
    //删除按钮点击事件
    $("#batchDelUsersBtn").on("click",function(){
    	//1.获取到勾选了的复选框dom对象，集合变量里
    	var checkboxs = $(".chkone:checked");
    	if(checkboxs.length == 0){
    		alert("未选择任何用户!");
    		return;
    	}else{
    		//2.获取到对选了的复选框的value(user的id)
    		var userIds = new Array();
    		checkboxs.each(function(){
    			userIds.push($(this).val());
    		});
    		var datas = JSON.stringify(userIds);
    		var flag = delSure();
    		//3.通过ajax，向服务端提交批量删除用户的请求，userIds传入后台
    		if(flag){
    			$.ajax({
    				url: '/qihangkt/admin/batchDelUsers.html',
    				type: 'POST',
    				data: {
    					uid:datas
    				},
    				success: function(rs){
    					if(rs == "success"){
    						$(location).attr("href","/qihangkt/admin/userManager.html");
    					}
    				}
    			});
    		}
    		
    	}
    });
    
    //查询按钮点击事件
    $("#searchBtn").on('click',function(){
    	$("#search_form").submit();
    });
});

//复选框全选
function chkall() {
    $(".chkone").prop("checked", $(".chkall").prop("checked"));//将所有chkone的状态更改为chkall的状态
}

//复选框选择
function chkone() {
    var flag = true;//标记为true
    $(".chkone").each(function (index, el) {
        var chk = $(el);
        if ($(el).prop("checked") == false) {
            flag = false;
        }
    });
    if (flag) {
        $(".chkall").prop("checked", true);
    } else {
        $(".chkall").prop("checked", false);
    }
}

//编辑提交事件
function updateUserFormSubmint(){
	$("#updateUserForm").submit();
}

//单个删除
function delSure(){
	if(confirm("确定要删除这个用户吗？")){
		return true;
	}else{
		return false;
	}
}

//批量删除
