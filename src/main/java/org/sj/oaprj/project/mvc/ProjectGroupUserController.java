package org.sj.oaprj.project.mvc;

import java.util.Map;

import org.sj.oaprj.project.domain.ProjectGroupUserUpdateDomain;
import org.sj.oaprj.project.service.ProjectGroupUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/projects/prjgrpuser")
@Api(value = "项目组成员管理API", description = "项目组成员管理<br>@author dzfang")
public class ProjectGroupUserController {
	@Autowired
	private ProjectGroupUserServiceImpl projectGroupUserServiceImpl;

	@ApiOperation(value = "项目组成员列表页面", notes = "项目组成员列表页面<br/>@auther dzfang")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "projects/projectgroupuser/projectGroupUserList";
	} 
	
	@ApiOperation(value = "项目组成员列表", notes = "项目组成员列表<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(String name,  Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = projectGroupUserServiceImpl.findByFields(name, buildPageRequest(pageIndex, pageSize));
		return result;
	}
	
	private PageRequest buildPageRequest(int page, int size) {
		page = page -1;
		if(page < 0) {
			page = 0;
		}
        return new PageRequest(page, size);
    }

	@ApiOperation(value = "项目组成员新增画面", notes = "项目组成员新增画面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("projects/projectgroupuser/projectGroupUserForm");
		ProjectGroupUserUpdateDomain pg = new ProjectGroupUserUpdateDomain();
		modelAndView.addObject("projectgroupuser", pg);
		return modelAndView;
	}

	@ApiOperation(value = "项目组成员信息新增", notes = "项目组成员信息新增<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(ProjectGroupUserUpdateDomain domain) {
		return projectGroupUserServiceImpl.save(domain);
	}

	@ApiOperation(value = "查询单个成员信息", notes = "查询单个项目组成员信息<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		ProjectGroupUserUpdateDomain pg = projectGroupUserServiceImpl.findUpdate(id);
		ModelAndView modelAndView = new ModelAndView("projects/projectgroupuser/projectGroupUserForm");
		modelAndView.addObject("projectgroupuser", pg);
		return modelAndView;
	}

	@ApiOperation(value = "项目组成员信息删除", notes = "项目组成员信息删除<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		projectGroupUserServiceImpl.delete(idArray);
	}
}
