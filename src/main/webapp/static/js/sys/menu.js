$(function() {
	
	reloadMenuList();
	getTreeGrid();

	// 查询菜单按钮
	$("#searchMenu").click(function() {
		//reloadMenuList();
	});

	// 添加菜单按钮
	$("#saveMenu").click(function() {
		initPanel();
		showList(false, "新增菜单");
	});
	// 修改菜单
	$("#updateMenu").click(function() {
		var rowId = getOneRow();
		var permissionsId = getRowData(rowId).permissionsId;
		if (permissionsId == null) {
			return;
		}
		initPanel();
		getMenuInfo(permissionsId);
		showList(false, "修改菜单");
	});

	// 删除菜单
	$("#deleteMenu").click(function() {

		deleteMenu();
	});
	
	// 刷新按钮
	$("#refreshMenu").click(function() {
		window.location.reload();
	});

	// 保存或修改确认
	$("#saveOrUpdate").click(function() {

		saveOrUpdate();
	});

	// 返回列表
	$("#returnList").click(function() {
		reloadMenuList();
	});
	
	//选择类型
	$("input[name=menuType]").click(function(){
		var type = $(this).val()
		if(type == 0){
			//alert("点击了目录");
			
			$("#div-menu-url").hide();
			$("#div-menu-perms").hide();
			$("#div-menu-order").show();
			$("#div-menu-icon").show();
			
		} else if(type == 1) {
			//alert("点击了菜单");
			$("#div-menu-url").show();
			$("#div-menu-perms").show();
			$("#div-menu-order").show();
			$("#div-menu-icon").show();
			
		} else if(type == 2) {
			//alert("点击了按钮");
			$("#div-menu-perms").show();
			$("#div-menu-url").hide();
			$("#div-menu-order").hide();
			$("#div-menu-icon").hide();
		}
	});
	
	//弹出菜单选择
	$("#parentName").click(function(){
		getMenuTree();
	});
	//选择的父菜单
	$("#selectMenu").click(function(){
		var node = zTree.getSelectedNodes();
		mMenu.parentId = node[0].menuId;
		mMenu.parentName = node[0].menuName;
		$('#showMenuList').modal('hide');
		$('#parentName').val(mMenu.parentName);
	});

});


// 判断是否有菜单ID（插入还是更新）
var mMenu = {
	menuName:null,
	menuId:null,
	parentId:0,
	parentName:null,
	menuUrl:null,
	menuType:1,
	menuPerms:null,
	orderNum:0,
	menuIcon:null
};
var treeData = [];
//菜单树
var zTree;
var setting = {
	data: {
		key:{
			url: "xUrl",
			name : "menuName"
		},
		simpleData: {
			enable: true,
			idKey:"menuId",
			pIdKey:"parentId",
			rootPId:-1
		}
	}
};

//请求菜单树
function getMenuTree() {
	$.get("sys/menu/select",function(res){
		var parsed = jQuery.parseJSON(res);
		zTree = $.fn.zTree.init($("#menuTree"), setting, parsed.menuList);
//		zTree.expandAll(true);//展开全部
		var node = zTree.getNodeByParam("menuId",1);
		zTree.selectNode(node);
		$('#showMenuList').modal('show');
	});
}



//加载菜单列表并按照规则整理数据
function reloadMenuList() {
	
	showList(true);
	
	treeData=[];
	
	$("#jqGrid").jqGrid('clearGridData'); //清空表格
	
	$.ajax({
		url : "sys/menu/list",
		dataType : "JSON",
		success : function(res) {
			getLevelData(res, 0, 0);

			$("#jqGrid").jqGrid('setGridParam',{ // 重新加载数据
			 	datastr: treeData,
			 	treedatatype : "jsonstring"
			}).trigger("reloadGrid");
		}
	});
}

//加载获取后的数据到jqgrid
function getTreeGrid() {
	$("#jqGrid").jqGrid({
		//url: "sys/menu/list",
		datatype : "jsonstring",
		datastr : treeData,
		colModel : [
				{ label : "权限ID", name : 'permissionsId',width : 50,hidden:true}, 
				{ label : "菜单ID", name : 'menuId', index : 'menuId', width : 50,sortable: false,key : true}, 
				{ label : "菜单名称", name : 'menuName', width : 120,sortable: false},
				{ label : "父菜单", name : 'parentName', width : 60, align : "center",sortable: false},
				{ label : "图标", name : 'menuIcon', width : 40, align : 'center',sortable: false, formatter : function(value, options, row){
					return value == null ? '' : '<i class = "' + value +'">';
				}},
				{ label : "类型", name : 'menuType', width : 60, align : "center" ,sortable: false,formatter:function(value, options, row){
					
					if (value === 0) {
						return '<sapn class="label label-primary">目录</span>';
					}  
					if (value === 1) {
						return '<sapn class="label label-success">菜单</span>';
					}
					if (value === 2) {
						return '<sapn class="label label-warning">按钮</span>';
					}
				}}, 
				{ label : "序号", name : 'orderNum', width : 50, align : "center",sortable: false }, 
				{ label : "URL", name : 'menuUrl', width : 120,sortable: false }, 
				{ label : "授权", name : 'menuPerms', width : 180,sortable: false}
		],
		hoverrows : true,
		viewrecords : false,
		gridview : true,
		height : "auto",
        autowidth:true,
		sortname : "menuId",
		treeGrid : true,
		ExpandColumn : "menuName",
		treedatatype : "local",
		// treeGridModel: "nested", 
		treeGridModel : "adjacency",
		loadonce : true,
		treeReader : {
			parent_id_field : "parentId",
			level_field : "level",
			leaf_field : "isLeaf",
			expanded_field : "expanded",
			loaded : "loaded",
			icon_field:"xicon"
		},
        loadComplete: function(xhr){
        	//数据请求完成
        },
        gridComplete:function(){
        	//加载完毕调用				
        },
		treeIcons : {
//			plus: "fa fa-caret-right",
//			minus: "fa fa-caret-down",
			leaf : ""
		}

	});
	
}


//递归数组，排序生成符合treegrid要求的格式（暂时解决，效率，以后修改 思路：生成等级数组or去掉父节点遍历）
function getLevelData(rows,menuId,lv) {
	var level = lv;
	$.each(rows, function(index, item) {
		if(item.parentId==menuId){
			var isLeaf = false;
			$.each(rows, function(i, c) {
				if (item.menuId == c.parentId) {
					isLeaf = false;
					return false;
				}
				isLeaf = true;
			});
			item.level = level;
			item.isLeaf = isLeaf;
			item.expanded = false;
			item.loaded = true;
			treeData.push(item);
			getLevelData(rows,item.menuId,level+1)
		} 
	});
}


// -----------------
// 获取指定菜单ID信息
function getMenuInfo(permissionsId) {
	$.get("sys/menu/info/" + permissionsId, function(res) {
		var parsed = jQuery.parseJSON(res);
		mMenu = parsed.menu;
		setPanelInfo();
	});
}


// 新增或者修改菜单
function saveOrUpdate() {
	if (validator()) {
		return;
	}
	var url = mMenu.menuId == null ? "sys/menu/save" : "sys/menu/update";
	getPanelInfo();
	
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(mMenu),
		success : function(res) {
			if (res.code === 0) {
				alert("操作成功");
				reloadMenuList();
			} else {
				alert(res.msg);
			}
		}
	});
}

// 删除菜单
function deleteMenu() {
	var rowId = getOneRow();
	var permissionsId = getRowData(rowId).permissionsId;
	if (permissionsId == null) {
		return;
	}
	var ret = confirm("确定删除该菜单项?",function(){
		$.ajax({
			type : "POST",
			url : "sys/menu/delete",
			dataType : "json",
			data : { "permissionsId": permissionsId },
			success : function(res) {
				if (res.code === 0) {
					alert("删除成功");
					reloadMenuList();
				} else {
					alert(res.msg);
				}
			}
		});
	});
}

// -----------------
// 获取编辑界面菜单的信息
function getPanelInfo() {
	mMenu.menuType = $("input[name=menuType]:checked").val();
	mMenu.menuName = $("#menuName").val();
	mMenu.parentName = $("#parentName").val();
	mMenu.menuUrl = $("#menuURL").val();
	mMenu.menuPerms = $("#perms").val();
	mMenu.orderNum = $("#orderNum").val();
	mMenu.menuIcon = $("#icon").val();
}

//设置面板信息
function setPanelInfo(){
	$("input[name=menuType][value="+ mMenu.menuType +"]").prop("checked", true).trigger("click");
	$("#menuName").val( mMenu.menuName);
	$("#parentName").val( mMenu.parentName);
	$("#menuURL").val( mMenu.menuUrl);
	$("#perms").val( mMenu.menuPerms);
	$("#orderNum").val( mMenu.orderNum);
	$("#icon").val( mMenu.menuIcon);
}

// 清空编辑界信息
function initPanel() {
	mMenu = {
		menuName:null,
		menuId:null,
		parentId:0,
		parentName:null,
		menuUrl:"",
		menuType:1,
		menuPerms:"",
		orderNum:0,
		menuIcon:""
	};
	$("input[name=menuType][value=1]").prop("checked", true).trigger("click");
	$("#menuName").val("");
	$("#parentName").val("");
	$("#menuURL").val("");
	$("#perms").val("");
	$("#orderNum").val("");
	$("#icon").val("");
}


// 显示列表或者编辑界面
function showList(show, title) {
	$("#menu-title").text(title);
	if (show) {
		$("#menu-list").show();
		$("#menu-information").hide();
	} else {
		$("#menu-list").hide();
		$("#menu-information").show();
	}
}

// 验证(demo)
function validator() {
	if (isBlank($("#menuName").val())) {
		alert("菜单名称不能为空");
		return true;
	}
	if ($("input[name=menuType]:checked").val()==1 && isBlank($("#menuURL").val())) {
		alert("菜单url不能为空");
		return true;
	}
}
