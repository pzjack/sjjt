/**
 * 供应商信息列表画面初始化
 */
function supplierListInit() {
	// 列表初始化
	dataGridInit();
	// 供应商类型Combobox初始化
	comboInit("supplierType", 1);
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", queryFormReset);
	// 添加供应商信息
	$("#btnAdd").bind("click", openAddWindow);
	// 批量删除供应商信息
	$("#btnDelete").bind("click", deleteDatas);
	// 默认加载数据
	findGridData();
}
/**
 * 供应商新增、编辑画面初始化
 */
function initForm() {
	// 供应商类型Combobox初始化
	comboInit("popSupplierType", 1);
	// 查询
	$("#btnClose").bind("click", closePopupWindow);
	// 清空表单数据
	$("#btnSave").bind("click", saveForm);
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
		url : '/supplier/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize
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
		url : '/supplier/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize
				+ '&_csrf=' + csrf,
		data : $("#queryForm").serialize(),
		success : onSuccess,
		error : onError
	});
}

/**
 * 清空查询区域
 */
function queryFormReset() {
	$("#supplierName").textbox('setValue', '');
	$("#supplierType").combobox('setValue', '');
}

/**
 * 格式化操作列
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function operateFormatter(value, row, index) {
	var result = "";
	result += "<a href='javascript:void(0);' onclick='openEditWindow(\""
			+ row.id + "\")'>编辑</a>&nbsp;&nbsp;";
	result += "<a href='javascript:void(0);' onclick='deleteOneData(\""
			+ row.id + "\")'>删除</a>&nbsp;&nbsp;";
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}

	return result;
}

/**
 * 供应商信息添加画面初始化
 */
function openAddWindow() {
	openWindow("新增供应商信息", '/supplier/formInit', 470, 280);
}

/**
 * 打开供应商信息编辑画面
 * 
 * @param supplierId
 */
function openEditWindow(id) {
	var href = '/supplier/findOne?id=' + id;
	openWindow("编辑供应商信息", href, 470, 280);
}

/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除供应商信息
 */
function deleteDatas() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	$.messager.confirm("提示信息", message.DELETE_SUPPLIER_CONFIRM, function(r) {
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
		url : '/supplier/delete?_csrf=' + csrf,
		data : param,
		success : afterDelete,
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除供应商信息
 * 
 * @param id
 */
function deleteOneData(id) {
	var msg = message.DELETE_SUPPLIER_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			var param = {
				idArray : [ id ]
			};
			doDelete(param);
		}
	});
}

/**
 * 保存供应商信息
 */
function saveForm() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : '/supplier/save?_csrf=' + csrf,
		data : $("#saveForm").serialize(),
		success : onSaveSuccess,
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}