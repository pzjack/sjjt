var uploadDir = "/resources/upload/";
var csrf = "";
/**
 * 调节宽度
 */
$(function() {
	csrf = $(window.parent.document).find("input[name='_csrf']").val();
	// $("div[class='datagrid-wrap panel-body
	// panel-body-noheader']").css("width",
	// "auto");
	// // 去掉所有checkbox的边框
	// $("input:checkbox").css("border", "none");
	//
	// // 取消grid下边框
	// $(".datagrid").find("div").first().css("border-bottom", "0px");
	//
	// // 使IE浏览器下，回车可以换行
	// $("textarea").focus(function() {
	// $(this).css("white-space", "pre-wrap");
	// })
	// // 窗体大小改变时，重置grid的大小
	//
	// $(window).resize(function() {
	// alert(33)
	// $('#dataList').datagrid('resize', {
	// width : $(window).width() - 10,
	// height : $(window).height() - 35
	// });
	// });

	// var toolbar = $("#toolbar");
	// if (toolbar != undefined) {
	// if ($.trim($(toolbar).html()) == "") {
	// $("#dataList").datagrid({
	// toolbar : ''
	// });
	// }
	// }
});

/**
 * Grid初始化
 */
function dataGridInit() {
	var pager = $('#pagination');
	pager.pagination({
		// 每页显示的记录条数，默认为20
		pageSize : 20,
		// 可以设置每页记录条数的列表
		pageList : [ 20, 40, 60, 100 ],
		onSelectPage : loadData
	});
}
/**
 * 查询Grid数据
 */
function findGridData() {
	var pager = $('#pagination');
	var options = pager.data("pagination").options;
	loadData(1, options.pageSize);
}

/**
 * 重新加载数据
 */
function reload() {
	var pager = $('#pagination');
	var options = pager.data("pagination").options;
	reloadData(options.pageNumber, options.pageSize);
}

/**
 * Ajax提交前执行
 */
function onBeforeSend() {
	var win = $.messager.progress({
		title : "请稍等...",
		text : "",
		msg : "正在处理中，请稍等..."
	});
}

/**
 * 查询成功
 * 
 * @param data
 */
function onSuccess(data) {
	$('#dataList').datagrid('loadData', data.rows);
	var pager = $('#pagination');
	pager.pagination({
		pageNumber : data.pageNumber,
		total : data.total
	});
}
/**
 * 保存后提示信息
 * 
 * @param data
 */
function onSaveSuccess(data) {
	if (data != null) {
		$.messager.alert("提示信息", message.byId(data), "info");
		if (data == "SAVE_SUCCESS") {
			// 编辑完成，直接关闭
			closeWindow();
			reload();
		}
	}
}
/**
 * 判断是否是由于session超时引起的，如果是，跳转到登陆页面
 * 
 * @param xhr
 * @param status
 * @param e
 */
function onError(xhr, status, e) {
	var sessionStatus = xhr.getResponseHeader('sessionstatus');
	if (sessionStatus == 'timeout') {
		var top = getTopWinow();
		var msg = "由于您长时间没有操作, session已过期, 请重新登录!";
		$.messager.confirm("提示信息", msg, function(r) {
			if (r) {
				top.location.href = basePath + '/admin/login.do';
			}
		});
	} else {
		$.messager
				.alert('提示信息', message.SYSTEM_ERROR + " status:" + xhr.status);
	}
}

/**
 * Ajax执行完执行
 * 
 * @param event
 * @param xhr
 * @param settings
 */
function onComplete(event, xhr, settings) {
	$.messager.progress('close');
}

/**
 * 删除后执行的操作
 * 
 * @param data
 */
function afterDelete(data) {
	$.messager.alert('提示信息', message.DELETE_SUCCESS, "info");
	reload();
}
/**
 * 操作成功
 * 
 * @param data
 */
function afterOperate(data) {
	if (data != null) {
		if (data == "OPERATE_SUCCESS") {
			$.messager.alert('提示信息', message.OPERATE_SUCCESS, "info");
			reload();
		} else {
			$.messager.alert("提示信息", message.byId(data), "info");
		}
	}
}
/**
 * 初始化下拉列表数据
 * 
 * @param comboId
 *            控件ID
 * @param codeType
 *            代码类型
 * @param hiddenId
 *            隐藏控件ID
 */
function comboInit(comboId, codeType) {
	var count = 0;
	var combobox = $("#" + comboId);
	combobox.combobox({
		url : '/codeData/findByCodeType?codeType=' + codeType,
		method : 'get',
		valueField : 'dataType',
		textField : 'dataName',
		panelHeight : 'auto',
		onLoadSuccess : function(data) {
			count = data.length;
			// 设置下拉列表空白项高度
			$(".combobox-item").height("16px");
		},
		onShowPanel : function() {
			if (count > 8) {
				$(this).combobox('panel').height(200);
			}
		}
	});
}

function comboBoxInit(comboId, url, vField, tField) {
	var count = 0;
	var combobox = $("#" + comboId);
	combobox.combobox({
		url : url,
		method : 'get',
		valueField : vField,
		textField : tField,
		panelHeight : 'auto',
		onLoadSuccess : function(data) {
			count = data.length;
			// 设置下拉列表空白项高度
			$(".combobox-item").height("16px");
		},
		onShowPanel : function() {
			if (count > 8) {
				$(this).combobox('panel').height(200);
			}
		}
	});
}

/**
 * 省市区街道四级联动
 */
function addressInit() {
	var array = new Array();
	array.push({
		id : 'addressProvince',
		type : '100',
		hid : 'hidProvince'
	});
	array.push({
		id : 'addressCity',
		type : '101',
		hid : 'hidCity'
	});
	array.push({
		id : 'addressDistrict',
		type : '102',
		hid : 'hidDistrict'
	});
	array.push({
		id : 'addressStreet',
		type : '103',
		hid : 'hidStreet'
	});
	// 初始化区域下拉列表
	comboboxInit(array, 0, "");
}

/**
 * 初始化区域下拉列表
 * 
 * @param array
 * @param index
 * @param newValue
 */
function comboboxInit(array, index, newValue) {
	var item = array[index];
	var combobox = $("#" + item.id);
	combobox.combobox({
		disabled : false
	});
	combobox.combobox({
		url : basePath + '/xtbm/getXtbmListHadNull.do?bmlx=' + item.type
				+ '&code2=' + newValue,
		valueField : 'code1',
		textField : 'value',
		onLoadSuccess : function() {
			var value = $("#" + item.hid).val();
			if (value != "") {
				combobox.combobox('setValue', value);
			}
			// 设置下拉列表空白项高度
			$(".combobox-item").height("16px");
		},
		onChange : function(newValue, oldValue) {
			$("#" + item.hid).val("");
			if (index < array.length - 1) {
				for (var i = index + 1; i < array.length; i++) {
					$("#" + array[i].id).combobox("clear").combobox("disable");
				}
				if (newValue != "") {
					comboboxInit(array, index + 1, newValue);
				}
			}
		}
	});
}
/**
 * 上传控件
 * 
 * @param id
 * @param url
 * @param limit
 * @param w
 * @param h
 * @param image
 * @param imageId
 * @param hasImage
 * @param hid
 */
function uploadifyInit(id, url, limit, w, h, image, imageId, hasImage, hid) {
	var text = "";
	var deleteId = "del_" + imageId;
	text += '<div class="imgDiv">';
	text += '<img id="' + imageId + '" class="btn-img" src="' + image + '"/>';
	text += '<img style="cursor:pointer;" id="'
			+ deleteId
			+ '" src="'
			+ basePath
			+ '/resources/images/delete.png" class="delete" onclick="deleteImage(\''
			+ deleteId + '\',\'' + imageId + '\',\'' + hid + '\')" />';
	text += '</div>';

	$("#" + id)
			.uploadify(
					{
						swf : basePath + '/resources/uploadify/uploadify.swf',
						uploader : url,
						buttonText : text,
						queueSizeLimit : limit,
						fileSizeLimit : "1MB",
						width : w,
						height : h,
						auto : true,
						// 在浏览窗口底部的文件类型下拉菜单中显示的文本
						fileTypeDesc : '支持的格式：',
						// 允许上传的文件后缀
						fileTypeExts : '*.jpg;*.jpeg;*.png;*.bmp',
						overrideEvents : [ 'onUploadError', 'onSelectError' ],
						onSelectError : uploadify_onSelectError,
						onUploadError : uploadify_onUploadError,

						// 上传到服务器，服务器返回相应信息到data里
						'onUploadSuccess' : function(file, data, response) {
							if (data) {
								if (limit > 1) {
									imageId = "image_" + file.id;
									deleteId = "delete_" + file.id;
									image = basePath + uploadDir + data;
									var content = "";
									content += '<div class="imgDiv">';
									content += '<img id="' + imageId
											+ '" class="btn-img" src="' + image
											+ '"/>';
									content += '<img style="cursor:pointer;" id="'
											+ deleteId
											+ '" src="'
											+ basePath
											+ '/resources/images/delete.png" class="delete" onclick="deleteLi(\''
											+ file.id + '\')" />';
									content += '<input type="hidden" name="articleImage" value="'
											+ data + '"/>';
									content += '</div>';
									$('#j_pic70 > li:last-child').before(
											'<li id="li_' + file.id + '">'
													+ content + '</li>')

									addImageCss(imageId, w, h, deleteId, true);

									var count = $(".imgDiv").length;
									if (count > 9) {
										$("#btnUserImage").hide();
									}
								} else {
									var src = basePath + uploadDir + data;
									var img = $("#" + imageId);
									img.attr("src", src);
									$("#" + deleteId).show();
									$("#" + hid).val(data);
								}
							}
						}
					});
	addImageCss(imageId, w, h, deleteId, hasImage);
}

var uploadify_onSelectError = function(file, errorCode, errorMsg) {
	var msgText = "上传失败\n";
	switch (errorCode) {
	case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
		msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
		break;
	case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
		msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
		break;
	case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
		msgText += "文件大小为0";
		break;
	case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
		msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
		break;
	default:
		msgText += "错误代码：" + errorCode + "\n" + errorMsg;
	}
	$.messager.alert('提示信息', msgText);
};

var uploadify_onUploadError = function(file, errorCode, errorMsg, errorString) {
	// 手工取消不弹出提示
	if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED
			|| errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
		return;
	}
	var msgText = "上传失败\n";
	switch (errorCode) {
	case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
		msgText += "HTTP 错误\n" + errorMsg;
		break;
	case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
		msgText += "上传文件丢失，请重新上传";
		break;
	case SWFUpload.UPLOAD_ERROR.IO_ERROR:
		msgText += "IO错误";
		break;
	case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
		msgText += "安全性错误\n" + errorMsg;
		break;
	case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
		msgText += "每次最多上传 " + this.settings.uploadLimit + "个";
		break;
	case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
		msgText += errorMsg;
		break;
	case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
		msgText += "找不到指定文件，请重新操作";
		break;
	case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
		msgText += "参数错误";
		break;
	default:
		msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n" + errorMsg
				+ "\n" + errorString;
	}
	$.messager.alert('提示信息', msgText);
}

/**
 * 从画面上删除上传的图片
 * 
 * @param deleteId
 * @param orgImage
 * @param imageId
 * @param hId
 */
function deleteImage(deleteId, imageId, hId) {
	var buttonImage = basePath + '/resources/images/btn/btn_image.png';
	$("#" + imageId).attr("src", buttonImage);
	$("#" + deleteId).hide();
	$("#" + hId).val("");

}
/**
 * 添加图片样式
 * 
 * @param imageId
 * @param w
 * @param h
 * @param deleteId
 * @param hasImage
 */
function addImageCss(imageId, w, h, deleteId, hasImage) {
	var img = $("#" + imageId);
	img.css("max-width", w + "px");
	img.css("max-height", h + "px");
	img.css("_width", 'expression(this.width>=' + w + '&&this.width/' + w
			+ '>=this.height/' + w + '?' + w + 'px":"auto")');
	img.css("_height", 'expression(this.width>=' + h + '&&this.width/' + h
			+ '>=this.height/' + h + '?' + h + 'px":"auto")');
	if (hasImage) {
		$("#" + deleteId).show();
	}
}
/**
 * 是否列格式化
 * 
 * @param value
 * @param row
 * @param index
 */
function booleanFormatter(value, row, index) {
	if (value == null) {
		return "";
	}
	return value ? "是" : "否";
}
/**
 * 日期格式化
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function dateFormatter(value, row, index) {
	if (isNull(value) == "") {
		return "";
	}
	var registerDate = new Date(value);
	return registerDate.format("yyyy-MM-dd");
}
/**
 * 时间格式化
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function timeFormatter(value, row, index) {
	if (isNull(value) == "") {
		return "";
	}
	var registerDate = new Date(value);
	return registerDate.format("yyyy-MM-dd hh:mm:ss");
}
/**
 * 日期格式化
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		// month
		"M+" : this.getMonth() + 1,
		// day
		"d+" : this.getDate(),
		// hour
		"h+" : this.getHours(),
		// minute
		"m+" : this.getMinutes(),
		// second
		"s+" : this.getSeconds(),
		// quarter
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		// millisecond
		"S" : this.getMilliseconds()
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
/**
 * 打开弹出窗口
 * 
 * @param href
 * @param width
 * @param height
 */
function openWindow(title, href, width, height, windowId, func) {
	windowId = windowId == undefined ? "myWindow" : windowId;
	var window = $('#' + windowId);
	/* window.remove(); */
	var window = '<div id="' + windowId
			+ '" class="easyui-window" closed="true"></div>';

	$('body').append(window);
	$('#' + windowId).window({
		title : title,
		width : width === undefined ? 600 : width,
		height : height === undefined ? 400 : height,
		href : href,
		modal : true,
		minimizable : false,
		maximizable : false,
		shadow : false,
		cache : false,
		closed : false,
		collapsible : false,
		resizable : false,
		onLoad : function() {
			if (func == undefined) {
				initForm();
			} else {
				func();
			}
		},
		onClose : function() {
			setTimeout(function() {
				$('#' + windowId).window('destroy');
			}, 100);
		}
	});
}

/**
 * 关闭弹出窗口
 */
function closeWindow(windowId) {
	windowId = windowId == undefined ? "myWindow" : windowId;
	$('#' + windowId).window('close')
}

/**
 * 删除li
 * 
 * @param fileId
 */
function deleteLi(fileId) {
	$("#li_" + fileId).remove();
	var count = $(".imgDiv").length;
	if (count > 9) {
		$("#btnUserImage").hide();
	} else {
		$("#btnUserImage").show();
	}
}
/**
 * 将null转换为空字符串
 */
function isNull(value) {
	if (value == null) {
		return "";
	}
	return value;
}
/**
 * 日期相减
 * 
 * @param startDate
 * @param endDate
 * @returns {Number}
 */
function dateDiff(startDate, endDate) {
	return (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
}

/**
 * indexOf方法，hack IE8 BUG
 */
Array.prototype.indexOf = function(item) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == item)
			return i;
	}
	return -1;
}

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * 
 * @return 当前页面的顶层窗口对象
 */
function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}

/**
 * 重置确认框按钮文本
 */
function resetConfirmButtonText() {
	$.messager.defaults.ok = "确定";
	$.messager.defaults.cancel = "取消";
}
/**
 * 显示确认框，确认框按钮文本分别为是，否
 * 
 * @param message
 *            显示的确认信息
 * @param okFunc
 *            点击“是”，调用的函数
 * @param cancelFunc
 *            点击“否”，调用的桉树
 */
function showYesNoConfirm(message, okFunc, cancelFunc) {
	$.messager.defaults.ok = "是";
	$.messager.defaults.cancel = "否";
	$.messager.confirm('提示信息', message, function(r) {
		if (r) {
			okFunc();
		} else {
			cancelFunc();
		}
	});
}

/**
 * 用户添加画面初始化
 */
function openPasswordWindow() {
	var href = basePath + '/user/updatePasswordInit.do';
	openWindow("修改密码", href, 320, 150);
}
/**
 * 将input中的值同步到隐藏域中
 * 
 * @param obj
 * @param id
 */
function syncValue(obj, id) {
	$("#" + id).val($(obj).val());
}
/**
 * 创建年月控件
 * 
 * @param id
 */
function monthbox(id) {
	var p = $('#' + id).datebox('panel'), // 日期选择对象
	tds = false, // 日期选择对象中月份
	span = p.find('span.calendar-text'); // 显示月份层的触发控件

	$('#' + id).datebox({
		onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
			span.trigger('click'); // 触发click事件弹出月份层
			if (!tds)
				setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
					tds = p.find('div.calendar-menu-month-inner td');
					tds.click(function(e) {
						e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件
						var year = /\d{4}/.exec(span.html())[0]// 得到年份
						, month = parseInt($(this).attr('abbr'), 10); // 月份
						$('#' + id).datebox('hidePanel')// 隐藏日期对象
						.datebox('setValue', year + '-' + month); // 设置日期的值
					});
				}, 0)
		},
		parser : function(s) {// 配置parser，返回选择的日期
			if (!s)
				return new Date();
			var arr = s.split('-');
			return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		},
		formatter : function(d) {
			return d.format("yyyy-MM");
		}// 配置formatter，只返回年月
	});
}

/**
 * 重置代码表下拉列表
 * 
 * @param id
 */
function resetCXSelect(id) {
	var data = $('#' + id).combobox('getData');
	if (data != null && data.length > 0) {
		$('#' + id).combobox('setValue', data[0].value);
	}
}
/**
 * 清除combogrid
 * 
 * @param id
 */
function combogridClear(id) {
	$("#" + id).textbox('setValue', '');
	$("#" + id).combogrid('grid').datagrid('clearSelections');
}

function combogridPager(grid) {
	var pager = grid.combogrid("grid").datagrid('getPager');
	$(pager).pagination({
		showPageList : false,
		displayMsg : '共{total}条记录'
	});
	//文本赋值
	var text = $(grid).attr("text");
	if (text != undefined && $.trim(text) != "") {
		grid.combogrid('setText', text);
	}
}

function projectComboGrid(comboId, mult, panelWidth) {
	var tb = "<div class='datagrid-toolbar' id='toolbar_"
			+ comboId
			+ "' ><table><tr><td style='padding:0 5px;'>项目组名称:</td><td><input class='easyui-textbox' style='height:18px;' id='grpName_"
			+ comboId
			+ "'></td>"
			+ "<td><a class='easyui-linkbutton c0 l-btn l-btn-small' href='javascript:void(0);' onclick='findProjectGroup(\""
			+ comboId
			+ "\")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text'>查&nbsp;&nbsp;询</span><span class='l-btn-icon icon-search'>&nbsp;</span></span></a></td></tr></table></div>"
	var tbb = $(tb);
	var grid = $('#' + comboId);
	grid.combogrid({
		panelWidth : panelWidth,
		panelHeight : 217,
		pagination : true,
		editable : false,
		idField : 'id',
		textField : 'name',
		rownumbers : true,
		multiple : mult,
		toolbar : tbb,
		pagination : true,
		pageSize : 10,
		pageList : [ 10 ],
		method : 'post',
		url : '/common/projectgroup/list?_csrf=' + csrf,
		columns : [ [ {
			field : 'name',
			title : '项目组名称',
			width : '250'
		}, {
			field : 'departmentName',
			title : '所属部门',
			width : '200'
		} ] ],
		onLoadSuccess : function() {
			combogridPager(grid);
		}
	});
}
/**
 * 重新加载combogrid的数据
 * 
 * @param comboId
 */
function findProjectGroup(comboId) {
	$('#' + comboId).combogrid("grid").datagrid("reload", {
		'name' : $('#grpName_' + comboId).val()
	});
}
/**
 * 查询员工信息
 * 
 * @param comboId
 * @param mult
 * @param panelWidth
 */
function employeeComboGrid(comboId, mult, panelWidth) {
	var tb = "<div class='datagrid-toolbar' id='toolbar_"
			+ comboId
			+ "' ><table><tr><td style='padding:0 5px;'>员工姓名:</td><td><input class='easyui-textbox' style='height:18px;' id='empName_"
			+ comboId
			+ "'></td>"
			+ "<td><a class='easyui-linkbutton c0 l-btn l-btn-small' href='javascript:void(0);' onclick='findEmployee(\""
			+ comboId
			+ "\")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text'>查&nbsp;&nbsp;询</span><span class='l-btn-icon icon-search'>&nbsp;</span></span></a></td></tr></table></div>"
	var tbb = $(tb);
	var grid = $('#' + comboId);
	grid.combogrid({
		panelWidth : panelWidth,
		panelHeight : 217,
		pagination : true,
		editable : false,
		idField : 'id',
		textField : 'name',
		rownumbers : true,
		multiple : mult,
		toolbar : tbb,
		pagination : true,
		pageSize : 10,
		pageList : [ 10 ],
		method : 'post',
		url : '/common/employee/list?_csrf=' + csrf,
		columns : [ [ {
			field : 'accountId',
			title : '账号',
			width : '100'
		}, {
			field : 'name',
			title : '员工姓名',
			width : '250'
		}, {
			field : 'phone',
			title : '电话号码',
			width : '100'
		} ] ],
		onLoadSuccess : function() {
			combogridPager(grid);
		}
	});
}
/**
 * 重新加载combogrid的数据
 * 
 * @param comboId
 */
function findEmployee(comboId) {
	$('#' + comboId).combogrid("grid").datagrid("reload", {
		'name' : $('#empName_' + comboId).val()
	});
}