/**
 * 部门信息列表画面初始化
 */
function departmentListInit() {
	// 列表初始化
	dataGridInit();
	// 查询
	$("#btnSearch").bind("click", findGridData);
	// 清空表单数据
	$("#btnClear").bind("click", queryFormReset);
	// 添加部门信息
	$("#btnAdd").bind("click", openAddWindow);
	// 批量删除部门信息
	$("#btnDelete").bind("click", deletedepartments);
	// 默认加载数据
	findGridData();
}

/**
 * 部门信息保存画面初始化
 */
function departmentSaveInit() {
	// 添加部门信息
	$("#btnSave").bind("click", savedepartment);
	// 关闭窗体
	$("#btnClose").bind("click", closePopupWindow);
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
		url : '/department/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize,
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
		url : '/department/list?pageIndex=' + pageIndex + '&pageSize=' + pageSize,
		data : $("#queryForm").serialize(),
		success : onSuccess,
		error : onError
	});
}

/**
 * 清空查询区域
 */
function queryFormReset() {
	$("#name").textbox('setValue', '');
	$("#principal").textbox('setValue', '');
	$("#contact").textbox('setValue', '');
	$("#contactPhone").textbox('setValue', '');
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
	result += "<a href='javascript:void(0);' onclick='deletedepartment(\"" + row.id
			+ "\")'>删除</a>&nbsp;&nbsp;";
	if (result == "") {
		$("#dataList").datagrid('hideColumn', 'operate');
	}

	return result;
}

/**
 * 部门信息添加画面初始化
 */
function openAddWindow() {
	openWindow("新增部门信息", '/department/formInit', 340, 190);
}

/**
 * 打开部门信息编辑画面
 * 
 * @param supplierId
 */
function openEditWindow(id) {
	var href = basePath + '/supplier/getdepartmentById.do?id=' + id;
	openWindow("编辑部门信息", href, 620, 190);
}

/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	alert(1)
	closeWindow();
}

/**
 * 批量删除部门信息
 */
function deletedepartments() {
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
		url : basePath + '/supplier/deletedepartmentsById.do',
		data : param,
		success : afterDelete,
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 删除部门信息
 * 
 * @param id
 */
function deletedepartment(id) {
	var msg = message.DELETE_SUPPLIER_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : basePath + '/supplier/deletedepartmentById.do',
				data : {
					'id' : id
				},
				success : afterDelete,
				error : doError,
				beforeSend : onBeforeSend,
				complete : onComplete
			});
		}
	});
}

/**
 * 保存部门信息
 */
function savedepartment() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : basePath + '/supplier/savedepartment.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
				if (data == "SAVE_SUCCESS") {
					closePopupWindow();
					reload();
				}
			}
		},
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}
/**
 * 重置表单数据
 */
function resetSaveForm() {
	$("#popName").val('');
	$("#popPrincipal").val('');
	$("#popContact").val('');
	$("#popContactPhone").val('');
	$("#popdepartmentDesc").val('');
}
