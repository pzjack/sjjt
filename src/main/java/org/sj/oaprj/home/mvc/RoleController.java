package org.sj.oaprj.home.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.sj.oaprj.core.Utils;
import org.sj.oaprj.domain.RespTreeNode;
import org.sj.oaprj.entity.Menu;
import org.sj.oaprj.entity.Role;
import org.sj.oaprj.entity.RoleMenu;
import org.sj.oaprj.home.service.MenuServiceImpl;
import org.sj.oaprj.home.service.RoleServiceImpl;
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
@RequestMapping(value = "/role")
@Api(value = "角色管理API", description = "角色管理<br>@author Jack.Alexander")
public class RoleController {
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	@Autowired
	private MenuServiceImpl menuServiceImpl;

	@ApiOperation(value = "角色列表页面", notes = "角色列表页面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/listInit", method = RequestMethod.GET)
	public String listInit() {
		return "system/roleList";
	}

	@ApiOperation(value = "角色列表", notes = "角色列表<br/>@auther dzhifang")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> list(String roleName, Integer pageIndex, Integer pageSize) {
		Map<String, Object> result = roleServiceImpl.findByFields(roleName, buildPageRequest(pageIndex, pageSize));
		return result;
	}

	private PageRequest buildPageRequest(final int page, final int size) {
		return new PageRequest(page - 1, size);
	}

	@ApiOperation(value = "角色新增画面", notes = "角色新增画面<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/formInit", method = RequestMethod.GET)
	public ModelAndView formInit() {
		ModelAndView modelAndView = new ModelAndView("system/roleForm");
		modelAndView.addObject("model", new Role());
		return modelAndView;
	}

	@ApiOperation(value = "角色信息新增", notes = "角色信息新增<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(Role entity) {
		return roleServiceImpl.save(entity);
	}

	@ApiOperation(value = "查询单个角色信息", notes = "查询单个角色信息<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findOne(Long id) {
		Role role = roleServiceImpl.findOne(id);
		ModelAndView modelAndView = new ModelAndView("system/roleForm");
		modelAndView.addObject("model", role);
		return modelAndView;
	}

	@ApiOperation(value = "角色信息删除", notes = "角色信息删除<br/>@auther Jack.Alexander")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody String delete(Long id) {
		return roleServiceImpl.delete(id);
	}

	@ApiOperation(value = "查询所有角色", notes = "查询所有角色<br/>@auther dzhifang")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody List<RespTreeNode> findAll() {
		return roleServiceImpl.findAll();
	}

	@ApiOperation(value = "显示菜单画面", notes = "显示菜单画面<br/>@auther dzhifang")
	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	public ModelAndView menuList(Long roleId) {
		ModelAndView modelAndView = new ModelAndView("system/menuList");
		modelAndView.addObject("roleId", roleId);
		return modelAndView;
	}

	@ApiOperation(value = "查询所有菜单", notes = "查询所有菜单<br/>@auther dzhifang")
	@RequestMapping(value = "/findMenuAll", method = RequestMethod.GET)
	public @ResponseBody List<RespTreeNode> findMenuAll(Long roleId) {
		List<Menu> menuList = menuServiceImpl.findMenuAll();
		List<RoleMenu> roleMenuList = menuServiceImpl.findByRoleId(roleId);
		RespTreeNode root = new RespTreeNode();
		root.setId("");
		root.setText("选择全部");
		for (Menu item : menuList) {
			long count = roleMenuList.stream().filter(p -> p.getMenuId() == item.getId()).count();
			item.setChecked(count > 0 ? "1" : "0");
		}
		for (Menu item : menuList) {
			if (item.getParentId() == null) {
				RespTreeNode children = new RespTreeNode();
				children.setId(Utils.toString(item.getId()));
				children.setText(item.getName());
				children.setChecked("1".equals(item.getChecked()));
				root.getChildren().add(children);
				Iterator<Menu> it = menuList.stream().filter(p -> p.getParentId() == item.getId()).iterator();
				while (it.hasNext()) {
					Menu m = it.next();
					RespTreeNode subNode = new RespTreeNode();
					subNode.setId(Utils.toString(m.getId()));
					subNode.setText(m.getName());
					children.setChecked("1".equals(m.getChecked()));
					children.getChildren().add(subNode);
				}
			}
		}
		// 树型菜单结构列表
		List<RespTreeNode> nodeList = new ArrayList<RespTreeNode>();
		nodeList.add(root);
		return nodeList;
	}

	@ApiOperation(value = "角色授权", notes = "角色授权<br/>@auther dzhifang")
	@RequestMapping(value = "/saveRoleAuth", method = RequestMethod.POST)
	public @ResponseBody String saveRoleAuth(Long roleId, @RequestParam(value = "idArray[]") Long[] idArray) {
		return roleServiceImpl.saveRoleAuth(roleId, idArray);
	}
}
