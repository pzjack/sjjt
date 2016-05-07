/**
 * 
 */
package org.sj.oaprj.home.mvc;

import org.sj.oaprj.entity.Menu;
import org.sj.oaprj.home.service.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private static Menu menu = null;
	
	@ApiOperation(value="跳转到登录页", notes="跳转到登录页<br/>@auther 潘震")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "home/login";
	}

	@ApiOperation(value="首页", notes="到欢迎页面<br/>@auther 潘震")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home(Integer id) {
		
		if (null == menu) {
			menu = menuServiceImpl.findMenuByRoleId(Long.parseLong("1"));
		}
		ModelAndView modelAndView = new ModelAndView("home/index");
		modelAndView.addObject("menu", menu);
		return modelAndView;
	}
}
