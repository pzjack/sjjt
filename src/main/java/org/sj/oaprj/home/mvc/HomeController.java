/**
 * 
 */
package org.sj.oaprj.home.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

/**
 * @author zhen.pan
 *
 */

@Controller
public class HomeController {

	@ApiOperation(value="跳转到登录页", notes="跳转到登录页<br/>@auther 潘震")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "home/login";
	}

	@ApiOperation(value="首页", notes="到欢迎页面<br/>@auther 潘震")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "home/index";
	}
}
