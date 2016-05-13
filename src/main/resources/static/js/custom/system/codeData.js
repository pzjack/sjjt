/**
 * 
 */
function codeDataListInit() {
	// 初始化树
	treeInit();
	// 列表初始化
	dataGridInit();
	// 新增分类代码
	$("#btnAddType").bind("click", openCodeTypeAddWindow);
	// 编辑分类代码
	$("#btnEditType").bind("click", openCodeTypeEditWindow);
	// 新增代码数据
	$("#btnAdd").bind("click", openCodeDataAddWindow); 
	// 批量删除代码数据
	$("#btnDeletes").bind("click", deleteCodeDatas);
	// 默认加载数据
	findGridData();

}
/**
 * 菜单树初始化
 */
function treeInit() {
	$('#treeUl').tree({
		checkbox : false,
		url : '/codeType/findAll',
		method : 'get',
		onLoadSuccess : function() {
		},
		onClick : function(node) {
			// 判断是否是叶子节点
			if ($('#treeUl').tree('isLeaf', node.target)) {
				$("#codeType").val(node.id);
				findGridData();
			} else {
				$("#codeType").val("");
				findGridData();
			}
		}
	});
}
/**
 * 加载数据
 * 
 * @param pageIndex
 * @param pageSize
 */
function loadData(pageIndex, pageSize) {
	$.ajax({
		type : "POST",
		url : '/codeData/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize
				+ '&_csrf=' + csrf,
		data : $("#queryForm").serialize(),
		beforeSend : onBeforeSend,
		success : onSuccess,
		error : onError,
		complete : onComplete
	});
}
/**
 * 重新加载数据，不带进度条
 * 
 * @param pageIndex
 * @param pageSize
 */
function reloadData(pageIndex, pageSize) {
	$.ajax({
		type : "POST",
		url : '/codeData/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize
				+ '&_csrf=' + csrf,
		data : $("#queryForm").serialize(),
		success : onSuccess,
		error : onError
	});
}

function initTypeForm() {
	// 查询
	$("#btnClose").bind("click", closePopupWindow);
	// 保存代码数据
	$("#btnSave").bind("click", saveTypeForm);
}
/**
 * 代码分类新增画面初始化
 */
function openCodeTypeAddWindow() {
	var href = '/codeType/formInit';
	openWindow("新增代码分类", href, 380, 170, "myWindow", initTypeForm);
}

/**
 * 代码分类编辑画面初始化
 */
function openCodeTypeEditWindow() {
	var type = $("#treeUl").tree("getSelected");
	if (type.id != "") {
		var href = '/codeType/findOne?id=' + type.id;
		openWindow("编辑代码分类", href, 380, 170, "myWindow", initTypeForm);
	}
}
/**
 * 保存代码分类信息
 */
function saveTypeForm() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : '/codeType/save?_csrf=' + csrf,
		data : $("#saveForm").serialize(),
		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
				if (data == "SAVE_SUCCESS") {
					// 编辑完成，直接关闭
					closeWindow();
					treeInit();
				}
			}
		},
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}
/**
 * 代码信息新增画面初始化
 */
function openCodeDataAddWindow() {
	var type = $("#treeUl").tree("getSelected");
	if (type==null || type.id == "") {
		$.messager.alert('提示信息', message.PLS_SELECT_CODETYPE,"info");
		return
	}
	var href = '/codeData/formInit?typeId='+type.id;
	openWindow("新增代码信息", href, 380, 205);
}
/**
 * 代码信息编辑画面初始化
 */
function openCodeDataEditWindow(id) {
	var href = '/codeData/findOne?id='+id;
	openWindow("编辑代码信息", href, 380, 205);
}
/**
 * 格式化操作
 */
function operateFormatter(value, row, index) {
	var edit = "<a href='javascript:void(0);' onclick='openCodeDataEditWindow(\"" + row.id + "\")'>修改</a>&nbsp;&nbsp;";
	var del = "<a href='javascript:void(0);' onclick='deleteCodeData(\"" + row.id + "\")'>删除</a>&nbsp;&nbsp;";
	return edit + del;
}
/**
 * 代码信息新增画面保存和关闭
 */
function initForm() {
	// 保存添加的类型
	$("#btnSave").bind("click", saveCodeData);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
}
 
/**
 * 保存代码信息
 */
function saveCodeData() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : '/codeData/save?_csrf=' + csrf,
		data : $("#saveForm").serialize(),
		success : onSaveSuccess,
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}
 
/**
 * 批量删除代码信息
 */
function deleteCodeDatas() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";

	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA,"info");
		return;
	}
	$.messager.confirm("提示信息", message.DELETE_CODEDATA_CONFIRM, function(r) {
		if (r) {
			var param = {
					idArray : [ id.substring(0, id.length - 1) ]
			};
			doDelete(param);
		}
	});
}
/**
 * 执行删除操作
 */
function doDelete(param) {
	$.ajax({
		type : "POST",
		url : '/codeData/delete?_csrf=' + csrf,
		data : param,
		success : afterDelete,
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除代码信息
 * 
 * @param type
 */
function deleteCodeData(id) {
	var msg = message.DELETE_CODEDATA_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			var param = {
				idArray : [ id ]
			};
			doDelete(param);
		}
	});
}
