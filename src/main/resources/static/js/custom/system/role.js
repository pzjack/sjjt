var c = ""
function roleListInit() {
	$("#csrf").val(csrf);
	// 初始化树
	treeInit();
	// 打开新增画面
	$("#btnAdd").bind("click", openAddWindow);
	// 打开编辑画面
	$("#btnEdit").bind("click", openEditWindow);
	// 删除数据
	$("#btnDelete").bind("click", deleteRole);
}

function initForm() {
	// 查询
	$("#btnClose").bind("click", closePopupWindow);
	// 清空表单数据
	$("#btnSave").bind("click", saveForm);
}

function roleAuthSaveInit() {
	treeGridInit();
	// 保存角色权限
	$("#btnSave").bind("click", saveRoleAuth);
}
/**
 * 菜单树初始化
 */
function treeInit() {
	$('#treeUl').tree({
		checkbox : false,
		url : '/role/findAll',
		method : 'get',
		onLoadSuccess : function() {
			// 设置第一个节点高亮
			$("#treeUl li:eq(0)").find("div").addClass("tree-node-selected");
			var node = $("#treeUl").tree("getSelected");
			if (node != null) {
				$("#treeUl").tree("select", node.target);
				var src = '/role/menuList?roleId=' + node.id;
				$("#menuFrame").attr("src", src);
			}
		},
		onClick : function(node) {
			var src = '/role/menuList?roleId=' + node.id;
			$("#menuFrame").attr("src", src);
		}
	});
}

function treeGridInit() {
	var roleId = $("#roleId").val();
	$('#treeMenu').treegrid({
		checkbox : true,
		url : '/role/findMenuAll?roleId=' + roleId,
		method : 'get',
		checkbox : true,
		rownumbers : false,
		idField : 'id',
		treeField : 'text',
		onClick : function(node) {
			var src = '/role/findMenuAll?roleId=' + node.id;
			$("#menuFrame").attr("src", src);
		}
	});
}

function saveRoleAuth() {
	var _csrf = $(window.parent.document).find("input[id='csrf']").val();
	var roleId = $("#roleId").val();
	if (roleId.length == 0) {
		$.messager.alert('提示信息', message.PLS_SELECT_ROLE, "info");
		return;
	}
	var id = "";
	$($('#treeMenu').treegrid('getCheckedNodes')).each(function() {
		id += "" + $(this).attr("id") + ",";
	});
	var param = {
		idArray : [ id.substring(0, id.length - 1) ]
	};
	$.ajax({
		type : "POST",
		url : '/role/saveRoleAuth?roleId=' + roleId + '&_csrf=' + _csrf,
		data : param,
		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
			}
		},
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}

/**
 * 角色信息添加画面初始化
 */
function openAddWindow() {
	openWindow("新增角色信息", '/role/formInit', 420, 135);
}

/**
 * 打开角色信息编辑画面
 * 
 * @param supplierId
 */
function openEditWindow(id) {
	var type = $("#treeUl").tree("getSelected");
	if (type.id != "") {
		var href = '/role/findOne?id=' + type.id;
		openWindow("编辑角色信息", href, 420, 135);
	}else{
		$.messager.alert("提示信息", message.PLS_SELECT_EDIT_DATA, "info");
	}
}
/**
 * 关闭弹出窗口
 */
function closePopupWindow() {
	closeWindow();
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
		url : '/role/save?_csrf=' + csrf,
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

function deleteRole() {
	var type = $("#treeUl").tree("getSelected");
	if (type.id == "") {
		$.messager.alert("提示信息", message.PLS_SELECT_DELETE_DATA, "info");
		return;
	}
	var msg = message.DELETE_ROLE_CONFIRM;
	$.messager.confirm("提示信息", msg, function(r) {
		if (r) {
			$.ajax({
				type : "GET",
				url : '/role/delete?id=' + type.id + '&_csrf=' + csrf,
				success : function(data) {
					if (data != null) {
						$.messager.alert("提示信息", message.byId(data), "info");
						if (data == "DELETE_SUCCESS") {
							treeInit();
						}
					}
				},
				error : onError,
				beforeSend : onBeforeSend,
				complete : onComplete
			});
		}
	});
}