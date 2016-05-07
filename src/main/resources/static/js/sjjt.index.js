
$(function() {
	// 关闭tab页签
	tabClose();
	// 初始化左侧菜单
	InitLeftMenu();
});

/**
 * 初始化左侧菜单
 */
function InitLeftMenu() {
	hoverMenuItem();
	$('#wnav li a').bind('click', function() {
		var tabTitle = $(this).children('.nav').text();
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		addTab(tabTitle, url);
		$('#wnav li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});
}

/**
 * 菜单项鼠标Hover
 */
function hoverMenuItem() {
	$(".easyui-accordion").find('a').hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

/**
 * 添加tab页签
 * 
 * @param subtitle
 * @param url
 */
function addTab(subtitle, url) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}
/**
 * 添加iframe
 * 
 * @param url
 * @returns {String}
 */
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}
/**
 * 关闭tab页签
 */
function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
		$('#tabs').tabs('resize');
	});
}
 
/**
 * 退出系统
 */
function logout() {
	$.messager.confirm("提示", "您确定要退出系统？", function(r) {
		if (r) {
			document.location.href = basePath + '/admin/login.do';
		}
	});
}
