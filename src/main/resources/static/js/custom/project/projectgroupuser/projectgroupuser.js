/**
 * 列表画面初始化
 */
function initList() {
	projectComboGrid("projectgroupId", false, 500);
	employeeComboGrid("userId", false, 500);
	// 初始化角色下拉列表
	comboBoxInit("role", "/role/findAllRole", "id", "name");
	

	// 列表初始化
	dataGridInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 添加信息
	$("#btnAdd").bind("click", saveForm);
	// 批量删除信息
	$("#btnDelete").bind("click", deleteDatas);
	// 默认加载数据
	findGridData();
}


/**
 * 清空查询区域
 */
function queryFormReset() {
	$("#projectgroupId").textbox('setValue', '');
	$("#userId").textbox('setValue', '');
	$("#role").textbox('setValue', '');
	$("#post").textbox('setValue', '');
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
 * 打开编辑画面
 * 
 * @param supplierId
 */
function openEditWindow(id) {
	var href = '/projects/prjgrpuser/findOne?id=' + id;
	openWindow("编辑项目组用户信息", href, 520, 385);
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
	alert(param)
//	$.ajax({
//		type : "POST",
//		url : '/projects/prjgrpuser/delete?_csrf=' + csrf,
//		data : param,
//		success : afterDelete,
//		error : onError,
//		beforeSend : onBeforeSend,
//		complete : onComplete
//	});
}

/**
 * 删除信息
 * 
 * @param id
 */
function deleteOneData(id) {
	alert(id);
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