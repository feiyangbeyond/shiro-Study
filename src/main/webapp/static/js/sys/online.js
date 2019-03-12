//角色表格
$(function() {
	$("#jqGrid").jqGrid({
		url : 'sys/online/list',
		datatype : "json",
		colModel : [ 
			{label : 'sessionID',	name : 'sessionId',width : 35,hidden : true,key : true}, 
			{label : '用户名',name : 'user.cUsrName',align : "center",width : 75}, 
			{label : '真实姓名',name : 'user.cUsrRealName',align : "center",width : 100}, 
			{label : '手机号',	name : 'user.cUsrPhone',align : "center",width : 80,sortable: false},
			{label : '最近登陆时间',name : 'user.lastLoginTime',align : "center",index : "createTime",width : 80},
			{label : '账号过期时间',	name : 'user.expirationTime',align : "center",width : 80,sortable: false},
		],
		viewrecords : true,
		height : 'auto',
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		multiselectWidth : 28,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 加载完毕调用
		}
	});

	// 踢出用户
	$("#offOnline").click(function() {
		offOnlineUser();
	});
	
	//刷新页面
	$("#refreshOnline").click(function(){
		window.location.reload();
	})

});


// 删除角色
function offOnlineUser() {
	var sessionIds = getSelectedRows();
	if (sessionIds == null) {
		return;
	}
	var ret = confirm("确定踢出 " + sessionIds.length + "个用户?", function() {
		$.ajax({
			type : "POST",
			url : "sys/online/offline",
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(sessionIds),
			success : function(res) {
				if (res.code === 0) {
					alert("踢出成功");
				} else {
					alert(res.msg);
				}
			}
		});
	});
}

// 重新加载表格
function reloadList() {
	showList(true);
	var page = $("#jqGrid").jqGrid('getGridParam', 'page');
	$("#jqGrid").jqGrid('setGridParam', {
		postData : {
			'searchName' : $("#searchName").val()
		},
		page : page
	}).trigger("reloadGrid");
	$("#jqGrid").setGridWidth($(window).width() - 42);
}
