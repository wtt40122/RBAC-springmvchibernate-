$.extend({
	getUrlVars : function() {
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href
				.indexOf('?')
				+ 1).split('&');
		for (var i = 0; i < hashes.length; i++) {
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	},
	getUrlVar : function(name) {
		var value =$.getUrlVars()[name];
		if( typeof(value) != "undefined" ){
			return value;
		}else {
			return "";
		}
	},
	showdialog : function(options) {
		var defaults = {
			id : 'dialog',
			title : 'dialog',
			width : '600',
			height : '400',
			url : '',
			icon : 'icon-save',
			hide : '300',
			scrolling : "auto"
		};
		var opts = $.extend(defaults, options);
		var _id = opts.id;
		var _top = ($(window).height() - opts.height) / 2;
		if(_top<0){
			_top=0;
		}
		var _left = ($(window).width() - opts.width) / 2;
		if(_left<0){
			_left=0;
		}
		var _panelW = opts.width;
		var _bodyW = opts.width-2;
		var _scrolling = opts.scrolling;
		var _url = opts.url;
		
		var _iframe = $("<iframe scrolling='" + _scrolling + "' id='ifarme_" + _id
				+ "' class=\"panel_need_destroy_iframe\" frameborder='0'  src='" + _url
				+ "' " + "style='width:100%;height:100%;'></iframe>");
		
		if ($("#div_" + _id)[0]) {
			$("#div_" + _id).find(".panel-body").append(_iframe);
			$("#div_" + _id).show(opts.hide);
			return;
		}
		var _panel = $("<div id='div_"
				+ _id
				+ "' "
				+ " class='panel window' style='display: none; z-index: 9001;'>"
				+ "</div>").appendTo($("body")).css('left', _left).css('top',
				_top).css('width', _panelW);
		var _header = $("<div class='panel-header panel-header-noborder window-header "
				+ "style='width:" + _panelW + "px;height:40px;'></div>")
				.appendTo(_panel);
		var _title = $("<div class ='panel-title panel-with-icon'>"
				+ opts.title + "</div>").appendTo(_header);
		var _icon = $("<div class='panel-icon " + opts.icon + "'></div>")
				.appendTo(_header);
		var _tool = $("<div class='panel-tool'></div>").appendTo(_header);
		var _a = $("<a class='panel-tool-close' href='javascript:void(0)'></a>")
				.appendTo(_tool).bind("click", function() {
							$("#div_" + _id).hide(opts.hide, function() {
										$(this).remove();
									});
						});
		var _body = $("<div class='panel-body panel-body-noborder window-body' "
				+ "style='overflow: hidden;'></div>").appendTo(_panel).css(
				'width', _bodyW).css('height', opts.height);
		
		_body.append(_iframe);
		
		$("#div_" + _id).show(opts.hide);


	},
	showDialogNew:function(options){	
		var defaults = {
				id 			: 'dialog_show___',
				title 		: 'dialog',
				width 		: 600,
				height 		: 400,
				icon 		: 'icon-save',
				modal		:	true,
				zIndex 		: 	1500,
				iframeUrl	:	''
		};
		var opts = $.extend(defaults, options);
		var _iframe = "<iframe scrolling='auto' class=\"panel_need_destroy_iframe\" id='dialog_iframe_" + opts.id + "' name='dialog_iframe' frameborder='0'  src='" 
		+ opts.iframeUrl + "' " + "style='width:100%;height:100%;'></iframe>";
		if($("#"+ opts.id).length>0){
			$("#" + opts.id).html(_iframe);
			$("#" + opts.id).dialog(opts);
		} else {
			$("body").append("<div id=\""+ opts.id +"\" style=\"padding:2px;\" class=\"no-scrolling\">" + _iframe + "</div>");
			$("#" + opts.id).dialog(opts);
		}
	},
	myAjax:function(options){
		var defaults = {
			beforeSend : loadTipFun
		};
		var opts = $.extend(defaults, options);
		
		var temp_suc = opts.success;
		var temp_err = opts.error;
		opts.success = function(data){
			closeTipFun();
			temp_suc(data);
		}
		opts.error = function(a,b,c){
			closeTipFun();
			temp_err(a,b,c);
		}
		$.ajax(opts);
	},
	reloadEasyUiDatagrid:function(selector){
		$(selector).datagrid("reload");
	},
	easyUiTreegrid:function(selector,operator){
		$(selector).treegrid(operator);
	},
	closeEasyUiWin:function(selector){
		$(selector).window("close");
	},
	/************
	 * 刷新treegrid
	 * @param selector  选择器
	 * @param parentNode  父节点id
	 * @param state 状态 open/closed
	 */
	reloadEasyUiTreegrid:function(selector,parentNode,state){
		if(parentNode=="0" || parentNode==""){
			$(selector).treegrid("reload");
		}else {
			if(state=="open"){
				var row = $(selector).treegrid("getParent",parentNode);
				if(row){
					alert(row.id);
					$(selector).treegrid("reload",row.id);
				}else{
					$(selector).treegrid("reload");
				}
			}else{
				$(selector).treegrid("reload",parentNode);
			}
		}		
	},

	reloadEasyUiTreegrid_01:function(selector,parentNode,state){
		if(parentNode=="00" || parentNode==""){
			$(selector).treegrid("reload");
		}else {
			if(state=="open"){
				var row = $(selector).treegrid("getParent",parentNode);
				if(row){
					$(selector).treegrid("reload",row.fdid);
				}else{
					$(selector).treegrid("reload");
				}
			}else{
				$(selector).treegrid("reload",parentNode);
			}
		}		
	},
	getSelectedTabs:function(selector){
		return $(selector).tabs("getSelected");
	},
	optTabs:function(selector,opt,params){
		return $(selector).tabs(opt,params);
	}
	
});

String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
				replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};
Array.prototype.S = String.fromCharCode(2);
Array.prototype.in_array = function(e) {
	var r = new RegExp(this.S + e + this.S);
	return (r.test(this.S + this.join(this.S) + this.S));
};

function loadTipFun(){
	parent.$.jBox.tip("正在处理…","loading");
}
function closeTipFun(){
	parent.$.jBox.closeTip();
}

function easyuiRefresh(options){
	var defaults = {
			id			:	'showList',
			type		:	'datagrid',
			pattern	:	'all'
		};
	var opts = $.extend(defaults, options);
	if(opts.type=='treegrid'){
		if(opts.pattern=='parent'){
			var row = $("#" + opts.id).treegrid("getSelected");
			if(row ){
				var level = $("#" + opts.id).treegrid("getLevel",row.id);
				if(level>0){
					var p_row = $("#" + opts.id).treegrid("getParent",row.id);
					if(p_row){
						$("#" + opts.id).treegrid("reload",p_row.id);
						$("#" + opts.id).treegrid("expand",p_row.id);
					}else {
						$("#" + opts.id).treegrid("reload");
					}
				}else{
					$("#" + opts.id).treegrid("reload");
				}
			}else {
				$("#" + opts.id).treegrid("reload");
			}
		}else if(opts.pattern=='myself'){
			var row = $("#" + opts.id).treegrid("getSelected");
			if(row && row.state=='closed'){
				$("#" + opts.id).treegrid("reload",row.id);
				$("#" + opts.id).treegrid("expand",row.id);
			}else{
				var level = $("#" + opts.id).treegrid("getLevel",row.id);
				if(level>0){
					var p_row = $("#" + opts.id).treegrid("getParent",row.id);
					if(p_row){
						$("#" + opts.id).treegrid("reload",p_row.id);
						$("#" + opts.id).treegrid("expand",p_row.id);
					}else {
						$("#" + opts.id).treegrid("reload");
					}
				}else{
					$("#" + opts.id).treegrid("reload");
				}
			}
		}else {
			$("#" + opts.id).treegrid("reload");
		}
	}
	$("#" + opts.id).treegrid("unselectAll");
}


function refreshEasyUi(selector,component,para){
	
	if(method=="datagrid"){
		$(selector).datagrid(para);
	}
	
}



