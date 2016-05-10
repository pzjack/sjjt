package org.sj.oaprj.home.mvc;

import org.sj.oaprj.entity.Role;
import org.sj.oaprj.home.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/sys/role")
@Api(value = "角色管理API", description = "角色管理<br>@author Jack.Alexander")
public class RoleController {
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@ApiOperation(value = "角色列表", notes = "角色列表<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "sys/roleList";
	}

	@ApiOperation(value = "角色新增画面", notes = "角色新增画面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("sys/roleForm");
		modelAndView.addObject("role", new Role());
		return modelAndView;
	}

	@ApiOperation(value = "角色信息新增", notes = "角色信息新增<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody int save(Role entity) {
		return roleServiceImpl.save(entity);
	}

	@ApiOperation(value = "查询单个角色信息", notes = "查询单个角色信息<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		Role role = roleServiceImpl.findOne(id);
		ModelAndView modelAndView = new ModelAndView("sys/roleForm");
		modelAndView.addObject("role", role);
		return modelAndView;
	}

	@ApiOperation(value = "角色信息删除", notes = "角色信息删除<br/>@auther dzhifang")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		roleServiceImpl.delete(idArray);
	}
}
