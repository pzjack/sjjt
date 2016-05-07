package org.sj.oaprj.home.mvc;

import java.util.Map;

import org.sj.oaprj.entity.Department;
import org.sj.oaprj.home.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {
	@Autowired
	private DepartmentServiceImpl deptartmentServiceImpl;

	@ApiOperation(value = "部门列表", notes = "部门列表<br/>@auther dzhifang")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "organization/departmentList";
	}

	@ApiOperation(value = "部门列表", notes = "部门列表<br/>@auther dzhifang")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(Department org, Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = deptartmentServiceImpl.findByFields(org, new PageRequest(pageIndex, pageSize));
		return result;
	}
	
	@ApiOperation(value = "部门新增画面", notes = "部门新增画面<br/>@auther dzhifang")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public String formInit() {
		return "organization/departmentForm";
	}
}
