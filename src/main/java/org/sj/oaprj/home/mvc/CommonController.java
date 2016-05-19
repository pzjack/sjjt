package org.sj.oaprj.home.mvc;

import java.util.Map;

import org.sj.oaprj.core.Utils;
import org.sj.oaprj.home.service.UserServiceImpl;
import org.sj.oaprj.project.service.ProjectGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/common")
@Api(value = "公共查询API", description = "公共查询<br>@author dzfang")
public class CommonController {
	@Autowired
	private ProjectGroupServiceImpl projectGroupServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@ApiOperation(value = "项目组列表", notes = "项目组列表<br/>@auther dzfang")
	@RequestMapping(value = "/projectgroup/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(String name, int page, int rows) {
		if(!Utils.isEmpty(name)) {
			name = "%" + name + "%";
		}
		Map<String, Object> result = projectGroupServiceImpl.findByFields(name, buildPageRequest(page, rows));
		return result;
	}

	@ApiOperation(value = "员工列表", notes = "员工列表<br/>@auther dzfang")
	@RequestMapping(value = "/employee/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> employeeList(String userName, String employeeNo, String userPhone,
			int page, int rows) {
		Map<String, Object> result = userServiceImpl.findByFields(userName, employeeNo, userPhone,
				buildPageRequest(page, rows));
		return result;
	}

	private PageRequest buildPageRequest(final int page, final int size) {
		int pageIndex = page - 1;
		if (pageIndex < 0) {
			pageIndex = 0;
		}
		return new PageRequest(pageIndex, size);
	}
}
