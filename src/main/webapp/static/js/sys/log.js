$(function () {
	
    $("#jqGrid").jqGrid({
        url: 'sys/log/list',
        datatype: "json",
        colModel: [			
			{ label: 'logId', name: 'logId',width: 35,hidden:true, key: true },
			{ label: '用户名', name: 'cUsrName',align : "center", width: 35,sortable: false },
			{ label: '用户操作', name: 'cUsrOperation',align : "center", width: 70,sortable: false },
			{ label: '请求方法', name: 'methodName',align : "center", width: 70 ,sortable: false},
			{ label: '请求参数', name: 'theParams',align : "center", width: 70,sortable: false },
			{ label: '耗时(单位:毫秒)', name: 'theTimes',align : "center", width: 70},
			{ label: 'IP地址', name: 'theIp',align : "center", width: 70,sortable: false},
			{ label: '创建时间', name: 'createTime',align : "center", width: 80}
        ],
		viewrecords: true,
        height: 'auto',
        rowNum: 20,
		rowList : [20,50,100],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
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
    
    //查询
    $("#getLog").click(function(){
    	reloadList();
    });
    
    $("#exportFile").click(function(){
    	$('#jqGrid').tableExport( {type:'excel', excelstyles:['border-bottom', 'border-top', 'border-left', 'border-right']});
    });

    
});



//重新加载表格
function reloadList() {
	var page = $("#jqGrid").jqGrid('getGridParam','page');
	$("#jqGrid").jqGrid('setGridParam',{
		postData:{'key': $("#searchName").val()},
		page:page
	}).trigger("reloadGrid");
	
	$("#jqGrid").setGridWidth($(window).width()-42);
}

