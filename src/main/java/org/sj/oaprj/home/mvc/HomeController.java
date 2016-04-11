/**
 * 
 */
package org.sj.oaprj.home.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

/**
 * @author zhen.pan
 *
 */

@Controller
public class HomeController {

	@ApiOperation(value="登录接口", notes="PC端登录接口")
	@RequestMapping("/login")
	public String login() {
		return "home/login";
	}

	@ApiOperation(value="首页", notes="欢迎页面")
	@RequestMapping("/")
	public String home() {
		return "home/index";
	}
}
