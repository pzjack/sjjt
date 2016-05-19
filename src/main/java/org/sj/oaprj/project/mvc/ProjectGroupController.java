package org.sj.oaprj.project.mvc;

import java.util.Map;

import org.sj.oaprj.project.domain.ProjectGroupUpdateDomain;
import org.sj.oaprj.project.service.ProjectGroupServiceImpl;
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
@RequestMapping(value = "/projects/prjgrp")
@Api(value = "项目组管理API", description = "项目组管理<br>@author Jack.Alexander")
public class ProjectGroupController {
	@Autowired
	private ProjectGroupServiceImpl projectGroupServiceImpl;

	@ApiOperation(value = "项目组列表页面", notes = "项目组列表页面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "projects/projectgroup/ProjectGroupList";
	}
	
	@ApiOperation(value = "项目组列表", notes = "项目组列表<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(String name,  Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = projectGroupServiceImpl.findByFields("%" + name + "%", buildPageRequest(pageIndex, pageSize));
		return result;
	}
	
	private PageRequest buildPageRequest(final int page, final int size) {
        return new PageRequest(page - 1, size);
    }

	@ApiOperation(value = "项目组新增画面", notes = "项目组新增画面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("projects/projectgroup/ProjectGroupForm");
		ProjectGroupUpdateDomain pg = new ProjectGroupUpdateDomain();
		modelAndView.addObject("projectgroup", pg);
		return modelAndView;
	}

	@ApiOperation(value = "项目组信息新增", notes = "项目组信息新增<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(ProjectGroupUpdateDomain domain) {
		return projectGroupServiceImpl.save(domain);
	}

	@ApiOperation(value = "查询单个项目组信息", notes = "查询单个项目组信息<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		ProjectGroupUpdateDomain pg = projectGroupServiceImpl.findUpdate(id);
		ModelAndView modelAndView = new ModelAndView("projects/projectgroup/ProjectGroupForm");
		modelAndView.addObject("projectgroup", pg);
		return modelAndView;
	}

	@ApiOperation(value = "项目组信息删除", notes = "项目组信息删除<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		projectGroupServiceImpl.delete(idArray);
	}
}
