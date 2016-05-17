package org.sj.oaprj.project.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/projects/prjgrpuser")
@Api(value = "项目组成员管理API", description = "项目组成员管理<br>@author dzfang")
public class ProjectGroupUserController {

	@ApiOperation(value = "项目组列表页面", notes = "项目组列表页面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "projects/projectgroupuser/ProjectGroupUserList";
	} 
}
