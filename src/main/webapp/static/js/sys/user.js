$(function () {
    $(".form_datetime").datetimepicker({
    	format: 'yyyy-mm-dd 00:00:00',
//		 format: 'yyyy-mm-dd hh:ii:ss',
		 minView: "month", //不显示时分秒
		 todayHighlight : true, //当天高亮显示
		 todayBtn : "true", //显示今天按钮
		 autoclose: true, //当选择一个日期之后是否立即关闭此日期时间选择器。
		 language: 'zh-CN'

//      format: 'yyyy-mm-dd hh:ii:ss',
//      autoclose: true,
//      language: 'zh-CN'
    });  
//	$(".selectTimeTwo").datetimepicker({
//		 format: 'yyyy-mm-dd hh:ii:ss',
//		 minView: "month", //不显示时分秒
//		 todayHighlight : true, //当天高亮显示
//		 todayBtn : "true", //显示今天按钮
//		 autoclose: true, //当选择一个日期之后是否立即关闭此日期时间选择器。
//		 language: 'zh-CN'
//	});
	
    $("#jqGrid").jqGrid({
        url: 'sys/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'cUsrId', width: 35,hidden:true, key: true },
			{ label: '用户名', name: 'cUsrName',align:"center", width: 75,sortable: false},
			{ label: '真实姓名', name: 'cUsrRealName',align:"center", width: 75,sortable: false },
			{ label: '邮箱', name: 'cUsrEmail',align:"center", width: 90,sortable: false},
			{ label: '手机号', name: 'cUsrPhone',align:"center", width: 90,sortable: false},
			{ label: '状态', name: 'cUsrStatus', width: 40, align:"center",formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger ">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},			
			{ label: '最近登录时间', name: 'lastLoginTime',align:"center", width: 80},
			{ label: '账号有效期', name: 'expirationTime',align:"center", width: 80},
			{ label: '创建人', name: 'createUser',align:"center", width: 80,sortable: false},
			{ label: '创建时间', name: 'createTime',align:"center", index: "createTime", width: 80},
			{ label: '修改人', name: 'updateUser',align:"center", width: 80,sortable: false},
			{ label: '修改时间', name: 'updateTime',width: 80,sortable: false}
        ],
		viewrecords: true,
        height: 'auto',
        rowNum: 10,
		rowList : [10,30,50,100],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
		multiselectWidth: 28,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },        
        loadComplete: function(xhr){
        	//数据请求完成
        	//alert(JSON.stringify(xhr));
        },
        gridComplete:function(){
        	//加载完毕调用
        }
    });
    
    //查询用户按钮
    $("#getUser").click(function(){
    	reloadList();
    });
    
    //添加用户按钮
    $("#saveUser").click(function(){
    	clearPanel();
    	getRoleList();
    	showList(false,"添加用户");
    });
    
    //修改用户
    $("#updateUser").click(function(){
    	var cUsrId = getSelectedRow();
    	if (cUsrId == null) {
    		return;
    	}
    	clearPanel();
    	getRoleList();
    	getUserInfo(cUsrId);
    	showList(false,"修改用户信息");
    });
    
    //删除用户
    $("#deleteUser").click(function(){
    	deleteUser();
    });
    
    //刷新用户列表
    $("#refreshUser").click(function(){
    	window.location.reload();
    });
    
    //保存或修改确认
    $("#saveOrUpdate").click(function(){
	    if(validator()){
	    	return;
	    }
    	saveOrUpdate();
    });
    
    //返回列表
    $("#returnList").click(function(){
    	reloadList();
    });
    
    
});

//判断是否有用户名（插入还是更新）
var mUserId = null;

//-----------------
//获取指定用户ID信息
function getUserInfo(cUsrId){
	$.get("sys/user/info/"+cUsrId,function(res){
		var parsed = jQuery.parseJSON(res);
		var user = parsed.user;
		$("#userName").val(user.cUsrName);
		$("#userrealname").val(user.cUsrRealName);
		$("#email").val(user.cUsrEmail);
		$("#mobile").val(user.cUsrPhone);
		$("#expirationTime").val(user.expirationTime);
		$("input[name=status][value="+user.cUsrStatus+"]").prop("checked",true);
		$.each(user.roleIdList,function(index,roleId){
			$("input[name=roleStatus][value="+roleId+"]").prop("checked",true);
		});
		mUserId = user.cUsrId;
	});
}

//请求角色列表
function getRoleList(){
	$.ajax({ 
		type: "get", 
	    url: "sys/role/select", 
	    cache:false, 
	    async:false, //同步加载，防止进入页面回显时，角色还未初始化完成
	    success: function(res){
	    	var parsedJson = jQuery.parseJSON(res);
	   		var html="";
	   		$.each(parsedJson.list,function(index,role){
	   			html+="<label class='checkbox-inline'><input type='checkbox' name='roleStatus' value="+ role.roleId +">"+role.roleName+"</label>";
	   		});
	   		$("#roleList").append(html);
	    } 
	});
}

//新增或者修改用户
function saveOrUpdate(){
	var url = mUserId == null ? "sys/user/save" : "sys/user/update";
	var userInfo = getPanelInfo();
	userInfo.cUsrId = mUserId;
	if (isBlank($("#expirationTime").val())) {
		var ret = confirm("账号有效期为空，该用户将永久有效，仅受状态限制，是否继续?", function() {
			ajaxSaveOrUpdate(url,userInfo);
		},function(){
			return;
		});
	}else{
		ajaxSaveOrUpdate(url,userInfo);
	}
}

//新增或修改请求
function ajaxSaveOrUpdate(url,userInfo){
	$.ajax({
		type:"POST",
		url:url,
		contentType : "application/json",
		dataType: "json",
		async:false,
		data: JSON.stringify(userInfo),
		success: function(res) {
			if (res.code === 0) {
				alert("操作成功");
				reloadList();
			} else {
				alert(res.msg);
			}
		}
	});
}

//删除用户
function deleteUser(){
	var userIds = getSelectedRows();
	if (userIds == null) {
		return;
	}
	var ret = confirm("确定删除 "+userIds.length+ "条用户信息?",function(){
		$.ajax({
			type:"POST",
			url:"sys/user/delete",
			contentType : "application/json",
			dataType: "json",
			data: JSON.stringify(userIds),
			success:function(res){
    			if (res.code === 0) {
    				alert("删除成功");
    				reloadList();
    			} else {
    				alert(res.msg);
    			}
			}
		});
	});
}

//-----------------
//获取编辑界面用户的信息
function getPanelInfo(){
	var userInfo = {roleIdList:[]};
	userInfo.cUsrName = $("#userName").val();
	userInfo.cUsrPassword = $("#password").val();
	userInfo.cUsrRealName = $("#userrealname").val();
	userInfo.cUsrEmail = $("#email").val();
	userInfo.cUsrPhone = $("#mobile").val();
	userInfo.expirationTime = $("#expirationTime").val();
	userInfo.cUsrStatus = Number($("input[name='status']:checked").val());
	$('input[name=roleStatus]:checked').each(function(){ 
		userInfo.roleIdList.push($(this).val());
	}); 
	return userInfo;
}

//清空编辑界信息
function clearPanel(){
	$("#userName").val("");
	$("#password").val("");
	$("#userrealname").val("");
	$("#email").val("");
	$("#mobile").val("");
	$("#expirationTime").val("");
	$("#roleList").empty();
	mUserId = null;
	$("input[name=status][value=1]").prop("checked",true); 
}

//重新加载表格
function reloadList() {
	showList(true);
	var page = $("#jqGrid").jqGrid('getGridParam','page');
	$("#jqGrid").jqGrid('setGridParam',{
		postData:{'searchName': $("#searchName").val()},
		page:page
	}).trigger("reloadGrid");
	
	$("#jqGrid").setGridWidth($(window).width()-42);
}

//显示列表或者编辑界面
function showList(show,title) {
	$("#user-title").text(title);
	if (show) {
		$("#user-list").show();
		$("#user-information").hide();
	} else {
		$("#userName").attr("disabled","disabled")
		$("#user-list").hide();
		$("#user-information").show();
	}
}

//验证(demo)
function validator(){
	if (isBlank($("#userName").val())){
		Layer.showTips('用户名不能为空','#userName');
		return true;
	}
	if (mUserId == null && isBlank($("#password").val())) {
		Layer.showTips('密码不能为空','#password');
		return true;
	}else{
		if(!checkForm()){
			Layer.showTips('页面异常错误，请刷新页面后重试','#password');
			return true;
		}
	}
	if (isBlank($("#email").val())) {
		Layer.showTips('邮箱不能为空','#email');
		return true;
	}
}

//将密码加密后发送给后台
function checkForm() {
	var pwdipt = $("#password");
	var pwdvalue = pwdipt.val().replace(/(^\s*)|(\s*$)/g, "");
	if(pwdvalue.length > 0){
		pwdvalue = md5(pwdvalue);
		pwdipt.val(pwdvalue);
		if (pwdipt.val().length == 32) {
			return true;
		}else{
			return false;
		}
	}
	return true;
}


