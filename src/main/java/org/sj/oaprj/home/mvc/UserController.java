package org.sj.oaprj.home.mvc;

import java.util.Map;

import org.sj.oaprj.domain.UserUpdateDomain;
import org.sj.oaprj.home.service.DepartmentServiceImpl;
import org.sj.oaprj.home.service.RoleServiceImpl;
import org.sj.oaprj.home.service.UserServiceImpl;
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
@RequestMapping(value = "/user")
@Api(value = "用户管理API", description = "用户管理<br>@author Jack.Alexander")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@ApiOperation(value = "用户列表页面", notes = "用户列表页面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "system/userList";
	}
	
	@ApiOperation(value = "用户列表", notes = "用户列表<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(String userName, String employeeNo, String userPhone, Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = userServiceImpl.findByFields(userName, employeeNo, userPhone, buildPageRequest(pageIndex, pageSize));
		return result;
	}
	
	private PageRequest buildPageRequest(final int page, final int size) {
        return new PageRequest(page - 1, size);
    }

	@ApiOperation(value = "用户新增画面", notes = "用户新增画面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("system/userForm");
		UserUpdateDomain user = new UserUpdateDomain();
		user.setRoles(roleServiceImpl.findAllRole());
		user.setDeps(departmentServiceImpl.findAll());
		user.setPwd("66668888");
		//部门和角色，可以修改成点击下拉的时候动态获取
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@ApiOperation(value = "用户信息新增", notes = "用户信息新增<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(UserUpdateDomain domain) {
		return userServiceImpl.save(domain);
	}

	@ApiOperation(value = "查询单个用户信息", notes = "查询单个用户信息<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		UserUpdateDomain user = userServiceImpl.findUpdate(id);
		ModelAndView modelAndView = new ModelAndView("system/userUpdateForm");
//		user.setRoles(roleServiceImpl.findAllRole());
		user.setDeps(departmentServiceImpl.findAll());
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@ApiOperation(value = "用户信息删除", notes = "用户信息删除<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(value = "idArray[]") Long[] idArray) {
		userServiceImpl.delete(idArray);
	}
}
