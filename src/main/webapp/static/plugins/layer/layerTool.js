var colseTime = 1500;// 2s
/**
 * layer 是提示消息封装工具类
 */
var Layer = {
	/**
	 * 警告框 closeBtn:提供了两种风格的关闭按钮，可通过配置1和2来展示，如果不显示，则closeBtn: 0
	 * 
	 * @param {}
	 *            content 警示的内容
	 * @param {}
	 *            title 警示的标题，如果为空默认为提示信息
	 */
	showAlert : function(content, title) {
		var options = {
			icon : 0,
			time : colseTime,
			closeBtn : 0,
			btn : 0,
			title : "提示信息",
			skin : ''
		};
		if (title == null) {
			layer.alert(content, options);
		} else {
			$.extend(options.title, title);
			layer.alert(content, options);
		}

	},
	/**
	 * 操作成功提示框
	 * 
	 * @param {}
	 *            content 提示内容 默认：操作成功
	 * @param {}
	 *            callback 回调方法
	 */
	showSucAlert : function(content, callback) {
		var length = arguments.length; // 实际传入参数的长度
		var options = {
			icon : 1,
			time : colseTime,
			btn : 0,
			closeBtn : 0,
			skin : ''
		};
		if (length == 0) { // 没有传入任何参数
			layer.alert("操作成功", options);
		} else if (length == 1) { // 传入了提示内容
			layer.alert(content, options);
		} else if (length == 2) { // 有回调函数的,将不自动关闭
			layer.alert(content, {
				icon : 1
			}, callback);
		}
	},
	/**
	 * 加载消息提示框 2s后自动关闭
	 * 
	 * @param {}
	 *            content 提示内容
	 * @param {}
	 *            callback 回调函数
	 */
	showLoad : function() {
		layer.load(0, {
			shade : false,
			time : colseTime
		}); // 0代表加载的风格，支持0-2
	},

	/**
	 * 操作失败提示框
	 * 
	 * @param {}
	 *            content 提示内容 默认：操作失败
	 * @param {}
	 *            time 关闭时间(单位毫秒) 默认：1S,0:表示不自动关闭
	 */
	showFailAlert : function(content, time) {
		var length = arguments.length; // 实际传入参数的长度
		var options = {
			icon : 2,
			time : colseTime
		};
		if (length == 0) { // 没有传入任何参数
			layer.alert("操作失败", options);
		} else if (length == 1) { // 传入了提示内容
			layer.alert(content, options);
		} else if (length == 2) { // 传入了关闭时间
			options.time = time;
			layer.alert(content, options);
		}
	},
	
	/**
	 * 打开一个对话框(没有回调函数)
	 * 
	 * @param {}
	 *            title 对话框标题(必须)
	 * @param {}
	 *            url 对话框URL(必须)
	 * @param {}
	 *            width 对话框宽度 默认：800px
	 * @param {}
	 *            height 对话框高低 默认：600px
	 */
	openDialogNoCallBack : function(title, url, width, height) {
		layer.open({
			type : 2,
			title : title,
			content : url,
			maxmin : true,
			area : [ width, height ]
		});
	},
	/**
	 * 获取当前的窗口对象
	 * 
	 * @return {}
	 */
	getCurrentWin : function() {
		return this.topWin.frames['ifr_center'];
	},

	/**
	 * 打开一个对话框(带回调函数)
	 * 
	 * @param {}
	 *            title 对话框标题(必须)
	 * @param {}
	 *            url 对话框URL(必须)
	 * @param {}
	 *            width 对话框宽度 默认：800px
	 * @param {}
	 *            height 对话框高低 默认：600px
	 */
	openDialogWithCallBack : function(title, url, width, height, callback) {
		layer.open({
			type : 2,
			title : title,
			content : url,
			area : [ width, height ],
			maxmin : true,
			end : callback
		});
	},
	/**
	 * 打开一个对话框(没有回调函数)
	 * 
	 * @param {}
	 *            title 对话框标题(必须)
	 * @param {}
	 *            url 对话框URL(必须)
	 * @param {}
	 *            width 对话框宽度 默认：800px
	 * @param {}
	 *            height 对话框高低 默认：600px
	 * @param {}
	 *            callback 窗口销毁时的回调方法
	 */
	openDialog : function(title, url, width, height, callback) {
		var length = arguments.length; // 实际传入参数的长度
		if (length == 2) { // 默认宽高
			this.openDialogNoCallBack(title, url, this.width, this.height)
		} else if (length == 3) { // 只传入宽度参数
			width += this.px;
			this.openDialogNoCallBack(title, url, width, this.height)
		} else if (length == 4) { // 传入宽度和高度
			width += this.px;
			height += this.px;
			this.openDialogNoCallBack(title, url, width, height)
		} else if (length == 5) { // 带回调函数
			width += this.px;
			height += this.px;
			this.openDialogWithCallBack(title, url, width, height, callback);
		}
	},

	/**
	 * 关闭弹出层
	 * 
	 * @param {}
	 *            index
	 */
	closeLayer : function(index) {
		layer.close(index);
	},
	/**
	 * 关闭所有的Dialog
	 */
	closeDialog : function() {
		layer.closeAll('iframe');
	},
	/**
	 * 关闭Dialog带有操作成功的提示
	 * 
	 * @param {}
	 *            content
	 */
	closeDialogWithMsg : function(content) {
		layer.closeAll('iframe');
		if (!content)
			content = "操作成功";
		this.showSucMsg(content);
	},
	/**
	 * 显示提示框
	 * 
	 * @param {}
	 *            content
	 */
	showMsg : function(content, callback) {
		layer.msg(content, {
			time : colseTime
		}, callback);
	},
	/**
	 * 显示操作成功的提示框
	 * 
	 * @param {}
	 *            content
	 */
	showSucMsg : function(content, callback) {
		if (!content)
			content = "操作成功";
		layer.msg(content, {
			icon : 1,
			time : colseTime
		}, callback);
	},
	/**
	 * 显示验证框
	 * 
	 * @param {}
	 *            content 提示内容
	 * @param {}
	 *            title 提示标题
	 * @param {}
	 *            yesFunction 确定以后的回调函数
	 */
	showConfirm : function(content, title, yesFunction) {
		var options = {
			btn : [ '确定', '取消' ],
			icon : 3,
			title : title,
			skin : 'layui-layer-molv'
		};
		layer.confirm(content, options, yesFunction);
	},

	/**
	 * Tips
	 * 
	 * @param {}
	 *            content 提示内容
	 * @param {}
	 *            select 选择器
	 */
	showTips : function(content, select, time) {
		var length = arguments.length; // 实际传入参数的长度
		var options = {
			tips : [ 1, '#000' ],
			time : colseTime
		};
		if(length == 2) {
			layer.tips(content, select, options);
		} else if(length == 3){
			options.time = time;
			layer.tips(content, select, options);
		}
	},
};