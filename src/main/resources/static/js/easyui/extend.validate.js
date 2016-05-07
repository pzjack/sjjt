$.extend($.fn.validatebox.defaults.rules, {
	// 最大长度
	maxlength : {
		validator : function(value, param) {
			return $.trim(value).length <= param[0];
		},
		message : '请输入一个长度最多是 {0} 的字符串！'
	},
	// 最小长度
	minlength : {
		validator : function(value, param) {
			return $.trim(value).length >= param[0];
		},
		message : '请输入一个长度最少是 {0} 的字符串！'
	},
	// 指定字符串的长度范围
	rangelength : {
		validator : function(str, MinLength, MaxLength) {
			if ((str.value.length > MaxLength)
					|| (str.value.length < MinLength)) {
				return false;
			}
		},
		message : '请输入一个长度介于 {0} 和 {1} 之间的字符串！'
	},
	// 验证输入是否为中文
	chinese : {
		validator : function(str) {
			var reg = new RegExp("^[\u4E00-\u9FA5]+$");
			return reg.test(str);
		},
		message : '请输入中文！'
	},
	telephone : {// 匹配国内电话号码评注：匹配形式如 0511-4405222 或 021-87888822
		validator : function(str) {
			var reg = new RegExp(
					"^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$");
			return reg.test(str);
		},
		message : '请输入正确的电话号码！'
	},
	age : {
		validator : function(str) {
			var reg = new RegExp("^[1-7]+[0-9]");
			if (!reg.test(str)) {
				return false;
			}
			if (str < 18) {
				return false;
			} else if (str > 65) {
				return false;
			} else {
				return true;
			}
		},
		message : '年龄应该在18-65周岁之间！'

	},
	// 指定字符串的长度范围
	range : {
		validator : function(str, MinValue, MaxValue) {
			if ((Number(str) > MaxValue) || (Number(str) < MinValue)) {
				return false;
			}
		},
		message : '请输入一个介于 {0} 和 {1} 之间的数字！'
	},
	gold : {
		validator : function(str) {
			var reg = new RegExp("^[0-9]{1,12}([.][0-9])?$");
			return reg.test(str);
		},
		message : '只允许输入整数或者一位小数位的小数！'
	},
	integer : {
		validator : function(str) {
			var reg = new RegExp("^[1-9][0-9]{0,10}$");
			return reg.test(str);
		},
		message : '只允许输入大于0的整数！'
	},
	long : {
		validator : function(str) {
			var reg = new RegExp("^[1-9][0-9]{0,18}$");
			return reg.test(str);
		},
		message : '只允许输入大于0的整数！'
	},
	day : {
		validator : function(str) {
			var reg = new RegExp("^[1-9][0-9]{0,9}$");
			return reg.test(str);
		},
		message : '请输入大于0的整数！'
	},
	before : {
		validator : function(str) {
			var start = new Date(Date.parse(str.replace(/-/g, "/")));
			var diff = dateDiff(new Date(), start);
			return diff < 0;
		},
		message : '必须小于等于系统当前日期！'
	},
	after : {
		validator : function(str) {
			var start = new Date(Date.parse(str.replace(/-/g, "/")));
			var currt = new Date();
			var year = currt.getFullYear();
			var month = currt.getMonth() + 1;
			var date = currt.getDate();
			var diff = dateDiff(new Date(Date.parse(year + "/" + month + "/"
					+ date)), start);
			return diff >= 0;
		},
		message : '必须大于等于系统当前时间！'
	},
	returnNumber : {
		validator : function(value, param) {
			var borrowNum = $(param[0]).val();
			return Number(borrowNum) == Number(value);
		},
		message : '归还数量必须与借出数量一致！'
	},
	end : {
		validator : function(value, param) {
			var s = $(param[0]).datebox("getValue");
			var start = new Date(Date.parse(s.replace(/-/g, "/")));
			var end = new Date(Date.parse(value.replace(/-/g, "/")));
			var diff = dateDiff(start, end);
			return diff > 0;
		},
		message : '结束日期必须大于开始日期！'
	},
	date : {// 匹配日期格式
		validator : function(str) {
			var pat_hd = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
			try {
				if (!pat_hd.test(str)) {
					return false;
				}
				var arr_hd = str.split("-");
				var dateTmp;
				dateTmp = new Date(arr_hd[0], parseFloat(arr_hd[1]) - 1,
						parseFloat(arr_hd[2]));
				if (dateTmp.getFullYear() != parseFloat(arr_hd[0])
						|| dateTmp.getMonth() != parseFloat(arr_hd[1]) - 1
						|| dateTmp.getDate() != parseFloat(arr_hd[2])) {
					return false;
				}
			} catch (ex) {
				if (ex.description) {
					return false;
				} else {
					return false;
				}
			}
			return true;
		},
		message : '日期格式错误！'
	},
	code : {
		validator : function(str) {
			var reg = new RegExp("^[A-Za-z0-9]+$");
			return reg.test(str);
		},
		message : '只允许输入字母和数字！'
	},
	mobile : {
		validator : function(str) {
			var reg = new RegExp("^[1][0-9]{10}$");
			return reg.test(str);
		},
		message : '请输入正确的手机号！'
	},
	idCard : {
		validator : function(str) {
			var reg = new RegExp("^[0-9]{17}[X0-9]$");
			return reg.test(str);
		},
		message : '请输入正确的身份证号！'
	},
	zipCode : {
		validator : function(str) {
			var reg = new RegExp("^[0-9]{6}$");
			return reg.test(str);
		},
		message : '请输入正确的邮政编码！'
	},
	dead : {
		validator : function(str) {
			var start = new Date(Date.parse(str.replace(/-/g, "/")));
			var diff = dateDiff(start, new Date());
			return diff < 1;
		},
		message : '截止日期必须大于系统当前日期！'
	},
	idcard : {
		validator : function(str) {
			var reg = new RegExp("^[0-9]{17}([0-9]|X)$");
			return reg.test(str);
		},
		message : '身份证号输入不合法！'
	},
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '确认密码与密码不一致！'
	}
});