/**
 * 列表画面初始化
 */
function initList() {
	projectComboGrid("projectGroupName", false, 500);
	
	projectComboGrid("projectGroupNameAdd", false, 500);
	
	employeeComboGrid("employeeAdd", false, 500);
	
	// 初始化角色下拉列表
	comboBoxInit("employeeRole", "/role/findAllRole", "id", "name");
}