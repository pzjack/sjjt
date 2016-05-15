function Message() {
	this.PLS_SELECT_DELETE_DATA = "请选择要删除的数据！";
	this.DELETE_DEPARTMENT_CONFIRM = "您确定要删除所选的部门信息吗？";
	this.DELETE_SUPPLIER_CONFIRM = "您确定要删除所选的供应商信息吗？";

	this.INSERT = "INSERT";
	this.UPDATE = "UPDATE";

	this.SAVE_SUCCESS = "保存成功！";
	this.SAVE_FAIL = "保存失败！";
	this.DELETE_SUCCESS = "删除成功！";
	this.DELETE_FAIL = "删除失败！";

	this.CODE_TYPE_EXISTS = "分类编码已存在，请重新输入！";
	this.PLS_SELECT_CODETYPE = "请先选择数据分类！";
	this.DELETE_CODEDATA_CONFIRM = "您确定要删除所选的数据信息吗？";
	this.DATA_TYPE_EXISTS = "编码已存在，请重新输入！";
	this.PASSWORD_NOT_EQUALS = "确认密码与密码不一致！";
	this.ACCOUNT_NOT_EXISTS = "当前账户不存在！";
	this.PLS_SELECT_ROLE = "请先选择要授权的角色！";
	this.PLS_SELECT_EDIT_DATA = "请选择要修改的数据！";
	this.ROLE_NAME_EXISTS = "角色名称已存在，请重新输入！";

	this.COMMIT_SUCCESS = "提交成功！";
	this.COMMIT_FAILED = "提交失败！";
	this.OPERATE_SUCCESS = "操作成功！";
	this.OPERATE_FAILED = "操作失败！";
	this.RESET_SUCCESS = "密码重置成功！";
	this.RESET_FAILED = "密码重置失败！";
	this.CODEDATA_EXISTS = "代码类别已存在";

	// 提示信息
	this.UNAUTHORIZED = "当前登录用户还未分配角色！";
	this.FROZEN = "当前登录用户已冻结！";
	this.LOGIN_FAILED = "用户名或密码错误！";
	this.LOGIN_ID_EXISTS = "用户名已存在，请重新输入！";

	this.BATCH_DELETE_CUSTOMER_CONFIRM = "您确定要删除勾选的客户信息吗？";
	this.DELETE_CUSTOMER_CONFIRM = "您确定要删除所选的客户信息吗？";
	this.BATCH_DELETE_USER_CONFIRM = "您确定要删除勾选的用户信息吗？";
	this.DELETE_USER_CONFIRM = "您确定要删除所选的用户信息吗？";
	this.BATCH_DELETE_ROLE_CONFIRM = "您确定要删除勾选的角色信息吗？";
	this.DELETE_ROLE_CONFIRM = "您确定要删除所选的角色信息吗？";
	this.PLS_SELECT_MENU = "请选择菜单！";
	this.BATCH_INSERT_ROLE_MENU = "您确定要为所选角色分配菜单吗？";
	this.BATCH_DELETE_NOTICE_CONFIRM = "您确定要删除所选择的公告信息吗？";
	this.DELETE_REPORT_CONFIRM = "您确定要删除所选的日报信息吗？";
	this.BATCH_DELETE_REPORT_CONFIRM = "您确定要删除勾选的日报信息吗？";
	this.DELETE_INFORMATION_CONFIRM = "您确定要删除所选的信息吗？";
	this.BATCH_DELETE_EMPLOYEE_CONFIRM = "您确定要删除所选的员工信息吗？"

	this.OPERATE_DATA = "请选择要操作的数据！";
	this.OPERATE_PASS_DATA = "您确定要通过所勾选的考勤申请吗？";
	this.OPERATE_FAIL_DATA = "您确定不通过所勾选的考勤申请吗？";
	this.PUBLISH_NOTICE_DATA = "您确定要对该条公告进行发布吗？";
	this.CANCEL_PUBLISH_NOTICE_DATA = "您确定要对其取消发布吗？";
	this.STICK_NOTICE_DATA = "您确定要对该条公告进行置顶吗？";
	this.CANCEL_STICK_NOTICE_DATA = "您确定要对其取消置顶吗？";

	this.PLS_INPUT_LOGINID = "请输入用户名！";
	this.PLS_INPUT_PASSWORD = "请输入密码！";
	this.AUDITING_SUCCESS = "审核成功！";
	this.AUDITING_FAILED = "审核失败！";
	this.NO_DATA = "查无数据！";
	this.STICK_SUCCESS = "置顶成功！";
	// 确认信息
	this.EXIT_CONFIRM = "您确定要退出系统吗？";
	this.BATCH_DELETE_CODE_CONFIRM = "您确定要删除勾选的码表信息吗？";

	this.SUPPLIER_EXISTS = "供应商已存在，请重新输入！";
	this.DELETE_EQUIPTYPE_CONFIRM = "您确定要删除所选的设备物资类别吗？";
	this.EQUIPTYPE_EXISTS = "设备物资类别已存在，请重新输入！";
	this.DELETE_EQUIPMEASURE_CONFIRM = "您确定要删除所选的计量单位吗？";
	this.EQUIPMEASURE_EXISTS = "计量单位已存在，请重新输入！";
	this.DELETE_EQUIPMENT_CONFIRM = "您确定要删除所选的设备信息吗？";
	this.EQUIPMENT_EXISTS = "设备名称已存在，请重新输入！";
	this.NORIGHT_DELETE_ROLE = "不允许删除所选角色！";
	this.DELETE_ROLE_CONFIRM = "您确定要删除所选的角色信息吗？";
	this.PLS_INPUT_DELETE_REASON = "请输入删除原因！";
	this.DELETE_BORROW_CONFIRM = "您确定要删除所选的设备借出信息吗？";
	this.DELETE_SCAP_CONFIRM = "您确定要删除所选的设备报废信息吗？";
	this.DELETE_SPACE_CONFIRM = "您确定要删除所选的网络空间信息吗？";
	this.DELETE_DOC_CONFIRM = "您确定要删除所选的文档信息吗？";
	this.NO_SPACE = "未给您分配网络空间，无法上传！";
	this.SPACE_NOT_ENOUGH = "您的网络空间不足，无法上传！";
	this.COMMIT_DOC_CONFIRM = "您确定要提交所需的文档吗？";
	this.DELETE_ORG_CONFIRM = "您确定要删除所选的部门信息吗？";
	this.NUMBER_NOT_ENOUGH = "设备数量不足，请重新输入！";
	this.IS_SYSTEM_ROLE = "该角色是系统角色，不允许删除";
	this.DELETE_MESSAGE_CONFIRM = "您确定要删除所选的消息吗？";
	this.READ_MESSAGE_CONFIRM = "您确定要处理所选的消息吗？";
	this.BATCH_RESET_USER_CONFIRM = "您确定要重置勾选的用户密码吗？";
	this.SPACE_EXISTS = "该用户的网络空间已分配！";
	// 错误信息

	this.SYSTEM_ERROR = "系统异常！";
}
/**
 * 格式化字符串
 */
function format() {
	if (arguments.length == 0) {
		return null;
	}
	var str = arguments[0];
	for (var i = 1; i < arguments.length; i++) {
		var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
		str = str.replace(re, arguments[i]);
	}
	return str;
};

Message.prototype.byId = function(id, args) {
	if (!this[id]) {
		return '';
	}

	if (!args) {
		return this[id];
	} else {
		return this[id].replace(/\{(\d+)\}/g, function(s, i) {
			return args[i];
		});
	}
};

var message = new Message();

Message.show = function(data) {
	if (data && (data.messageID || data.message)) {
		var type = "info";
		var title = "";
		if (data.flag == -1) {
			type = "error";
			title = "错误";
		} else if (data.flag == 0) {
			type = "info";
			title = "消息";
		}
		var msgId = message.byId(data.messageID, data.data);
		var msg = '';
		if (msgId) {
			msg = data.message ? msgId + "<br/>" + data.message : msgId;
		} else {
			msg = data.message;
		}

		$.messager.alert(title + "提示", msg, type);

		return true;
	}
	return false;
};
