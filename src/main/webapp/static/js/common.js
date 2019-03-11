$.jgrid.defaults.width = 1000;
//$.jgrid.defaults.responsive = true;
$.jgrid.defaults.guiStyle = 'bootstrap';

$(window).resize(function(){
	$("#jqGrid").jqGrid('setGridWidth', $(window).width()-40, true);
});

//重写alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}


//单选
function getOneRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.jqGrid('getGridParam',"selrow");
    if (rowKey)
        return rowKey;
    else
        alert("请选择一条记录");
}

//获取对应id行数据
function getRowData(rowId){
	var grid = $("#jqGrid");
	var rowData = grid.jqGrid('getRowData',rowId);
	if (rowData)
        return rowData;
    else
        alert("获取该行数据失败，请刷新页面后重试");
}

//选择记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.jqGrid('getGridParam',"selrow");

    if (!rowKey){
        alert("请选择一条记录");
        return;
	} 
	var selectedIDs = grid.jqGrid('getGridParam',"selarrrow");
//    var result = "";
//    for (var i = 0; i < selectedIDs.length; i++) {
//        result += selectedIDs[i] + ",";
//    }
//    alert(result);
	if (selectedIDs.length>1) {
		alert("只能选择一条记录");
        return;
	} 
	
	return selectedIDs[0];
}
//多选提示
function getSelectedRows() {
	var grid = $("#jqGrid");
	var rowKey = grid.jqGrid('getGridParam',"selrow");
	if (!rowKey) {
		alert("至少选择一条记录");
		return;
	}
	return grid.jqGrid('getGridParam',"selarrrow");
}

//判断为空
isBlank = function(obj){
    return(!obj || $.trim(obj) === "");
}

//	isBlank(" ") //true
//	isBlank("") //true
//	isBlank("\n") //true
//	isBlank("a") //false
//
//	isBlank(null) //true
//	isBlank(undefined) //true
//	isBlank(false) //true
//	isBlank([]) //true

