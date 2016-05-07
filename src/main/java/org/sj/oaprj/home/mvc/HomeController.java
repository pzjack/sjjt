/**
 * 
 */
package org.sj.oaprj.home.mvc;

import java.util.ArrayList;
import java.util.List;

import org.sj.oaprj.entity.Menu;
import org.sj.oaprj.home.service.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;

/**
 * @author zhen.pan
 *
 */

@Controller
public class HomeController {
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	private static List<Menu> menuList = null;
	
	@ApiOperation(value="跳转到登录页", notes="跳转到登录页<br/>@auther 潘震")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "home/login";
	}

	@ApiOperation(value="首页", notes="到欢迎页面<br/>@auther 潘震")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home(Integer id) {
		if (null == menuList) {
			menuList = menuServiceImpl.findMenuAll();
		}
		List<Menu> mainList = new ArrayList<Menu>();
		List<Menu> subList = new ArrayList<Menu>();
		for(Menu item : menuList){
			if (item.getParentId()==null){
				mainList.add(item);
			}else if (item.getParentId()==id){
				subList.add(item);
			}
		}
		ModelAndView modelAndView = new ModelAndView("home/index");
		modelAndView.addObject("mainList", mainList);
		modelAndView.addObject("subList", subList);
		return modelAndView;
	}
	
	@ApiOperation(value = "首页", notes = "到欢迎页面<br/>@auther 潘震")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "home/list";
	}

	@ApiOperation(value = "首页", notes = "到欢迎页面<br/>@auther 潘震")
	@RequestMapping(value = "/popup", method = RequestMethod.GET)
	public String popup() {
		return "home/dialog";
	}

	@ApiOperation(value = "首页", notes = "到欢迎页面<br/>@auther 潘震")
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String query() {
		return "home/list";
	}
	
	@RequestMapping(value = "/role/getRoleList", method = RequestMethod.POST)
	public @ResponseBody Menu testQuery(Integer pageIndex) {
		Menu menu = new Menu();
		menu.setId(1L);
		menu.setName("测试");
		menu.setUrl("url");
		return menu;
	}
}
