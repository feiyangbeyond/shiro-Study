//角色表格
$(function() {
	$("#jqGrid").jqGrid({
		url : 'sys/role/list',
		datatype : "json",
		colModel : [ 
			{label : '角色ID',	name : 'roleId',width : 35,hidden : true,key : true}, 
			{label : '角色名称',name : 'roleName',align : "center",width : 75}, 
			{label : '备注',name : 'roleRemark',align : "center",width : 100}, 
			{label : '创建人',	name : 'createUser',align : "center",width : 80,sortable: false},
			{label : '创建时间',name : 'createTime',align : "center",index : "createTime",width : 80},
			{label : '修改人',	name : 'updateUser',align : "center",width : 80,sortable: false},
			{label : '修改时间',name : 'updateTime',align : "center",index : "updateTime",width : 80,sortable: false} 
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

	// 查询角色按钮
	$("#getRole").click(function() {
		reloadList();
	});

	// 添加角色按钮
	$("#saveRole").click(function() {
		clearPanel();
		getMenuTree();
		showList(false, "添加角色");
	});
	// 修改角色
	$("#updateRole").click(function() {
		var roleId = getSelectedRow();
		if (roleId == null) {
			return;
		}
		clearPanel();
		getMenuTree();
		if($('#menuTree:has(li)').length == 0){
			setTimeout(function(){
			},1000);
		}
		getRoleInfo(roleId);
		showList(false, "修改角色");
	});

	// 删除角色
	$("#deleteRole").click(function() {
		deleteUser();
	});
	
	//刷新页面
	$("#refreshRole").click(function(){
		window.location.reload();
	})

	// 保存或修改确认
	$("#saveOrUpdate").click(function() {
		if (validator()) {
			return;
		}
		saveOrUpdate();
	});

	// 返回列表
	$("#returnList").click(function() {
		reloadList();
	});

});

// ----------------
var zTree;
var setting = {
	check : {
		enable : true,
	},
	view : {
		showLine : false,// 是否显示节点之间的连线
	},
	data : {
		key : {
			url : "xUrl",
			name : "menuName"
		},
		simpleData : {
			enable : true,
			idKey : "menuId",// 节点id
			pIdKey : "parentId",// 父节点id
			rootPId : null
		}
	}
};

// -----------------

// 判断是否有角色名（插入还是更新）
var mRoleId = null;

// 请求菜单树
function getMenuTree() {
	$.ajax({ 
       type: "get", 
       url: "sys/menu/list", 
       cache:false, 
       async:false, //同步加载，防止进入页面回显时，树还未初始化完成
       success: function(res){
    	   var parsed = jQuery.parseJSON(res);
   		   zTree = $.fn.zTree.init($("#menuTree"), setting, parsed);
//   	   zTree.expandAll(true);//展开全部
       } 
	});
}

// 获取指定角色ID信息
function getRoleInfo(roleId) {
	$.get("sys/role/info/" + roleId, function(res) {
		var parsed = jQuery.parseJSON(res);
		var role = parsed.role;
		$("#roleName").val(role.roleName);
		$("#roleRemark").val(role.roleRemark);
		try{
			(role.permissionsIdList).forEach(function(value, index, array) {//这里的permissionsIdList相当于menuIdList
//				var node = zTree.getNodeByParam("menuId", value);
				var node = zTree.getNodeByParam("permissionsId", value);//根据权限ID去回显
				zTree.checkNode(node, true, false, false);
			});
		}catch(err){
			window.location.reload();
		}
		mRoleId = role.roleId;
	});
}

// 新增或者修改角色
function saveOrUpdate() {
	var url = mRoleId == null ? "sys/role/save" : "sys/role/update";
	var roleInfo = getPanelInfo();
	roleInfo.roleId = mRoleId;
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(roleInfo),
		success : function(res) {
			if (res.code === 0) {
				alert("操作成功");
				reloadList();
			} else {
				alert(res.msg);
			}
		}
	});
}

// 删除角色
function deleteUser() {
	var roleIds = getSelectedRows();
	if (roleIds == null) {
		return;
	}
	var ret = confirm("确定删除 " + roleIds.length + "条角色信息?", function() {
		$.ajax({
			type : "POST",
			url : "sys/role/delete",
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(roleIds),
			success : function(res) {

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

// -----------------
// 获取编辑界面角色的信息
function getPanelInfo() {
	var nodes = zTree.getCheckedNodes(true);
	var permissionsIds = new Array();
	for (var i = 0; i < nodes.length; i++) {
		permissionsIds.push(nodes[i].permissionsId);
	}
	var roleInfo = {
		permissionsIdList : []
	};
	roleInfo.roleName = $("#roleName").val();
	roleInfo.roleRemark = $("#roleRemark").val();
	roleInfo.permissionsIdList = permissionsIds;
	return roleInfo;
}

// 清空编辑界信息
function clearPanel() {
	$("#roleName").val("");
	$("#roleRemark").val("");
	if (zTree) {
		zTree.cancelSelectedNode();
	}
	mRoleId = null;
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

// 显示列表或者编辑界面
function showList(show, title) {
	if (show) {
		$("#role-list").show();
		$("#role-information").hide();
	} else {
		$("#role-list").hide();
		$("#role-information").show();
		$("#role-title").text(title);
	}
}

// 验证(demo)
function validator() {
	if (isBlank($("#roleName").val())) {
		alert("角色名不能为空");
		return true;
	}
}
