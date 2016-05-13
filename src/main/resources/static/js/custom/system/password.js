/**
 * 页面初始化
 */
function passwordFormInit() {
	// 保存密码
	$("#btnSave").bind("click", saveForm);
}
/**
 * 保存密码
 */
function saveForm() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	$.ajax({
		type : "POST",
		url : '/account/updatePwd?_csrf=' + csrf,
		data : $("#saveForm").serialize(),
		success : function (data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
			}
		},
		error : onError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}