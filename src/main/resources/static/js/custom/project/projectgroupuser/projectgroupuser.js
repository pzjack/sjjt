/**
 * 列表画面初始化
 */
function initList() {
	projectComboGrid("projectgroupName", false, 500);
	employeeComboGrid("name", false, 500);
	// 初始化角色下拉列表
	comboBoxInit("role", "/role/findAllRole", "id", "name");
	

	// 列表初始化
	dataGridInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", queryFormReset);
	// 添加信息
	$("#btnAdd").bind("click", openAddWindow);
	// 批量删除信息
	$("#btnDelete").bind("click", deleteDatas);
	// 默认加载数据
	findGridData();
}

/**
 * 新增、编辑画面初始化
 */
function initForm() {
	projectComboGrid("projectgroupName", false, 500);
	employeeComboGrid("name", false, 500);
	// 初始化角色下拉列表
	comboBoxInit("role", "/role/findAllRole", "id", "name");
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
		url : '/projects/prjgrpuser/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize
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
		url : '/projects/prjgrpuser/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize
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
	$("#userName").textbox('setValue', '');
	$("#employeeNo").textbox('setValue', '');
	$("#userPhone").textbox('setValue', '');
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
 * 添加画面初始化
 */
function openAddWindow() {
	openWindow("新增项目组用户信息", '/projects/prjgrpuser/formInit', 470, 285);
}

/**
 * 打开编辑画面
 * 
 * @param supplierId
 */
function openEditWindow(id) {
	var href = '/projects/prjgrpuser/findOne?id=' + id;
	openWindow("编辑项目组用户信息", href, 470, 285);
}

/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
}

/**
 * 批量删除信息
 */
function deleteDatas() {
	var id = "";
	$($('#dataList').datagrid('getSelections')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	if (id.length == 0) {
		$.messager.alert('提示信息', message.DELETE_INFORMATION_CONFIRM, "info");
		return;
	}
	$.messager.confirm("提示信息", message.DELETE_MESSAGE_CONFIRM, function(r) {
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
		url : '/projects/prjgrpuser/delete?_csrf=' + csrf,
		data : param,
		success : afterDelete,
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除信息
 * 
 * @param id
 */
function deleteOneData(id) {
	var msg = message.DELETE_MESSAGE_CONFIRM;
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
 * 保存信息
 */
function saveForm() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : '/projects/prjgrpuser/save?_csrf=' + csrf,
		data : $("#saveForm").serialize(),
		success : onSaveSuccess,
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}