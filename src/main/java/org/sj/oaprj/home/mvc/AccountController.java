package org.sj.oaprj.home.mvc;

import org.sj.oaprj.domain.RespAccount;
import org.sj.oaprj.home.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private AccountServiceImpl accountServiceImpl;

	@ApiOperation(value = "个人密码修改画面", notes = "个人密码修改画面<br/>@auther dzhifang")
	@RequestMapping(value = "/pwdFormInit", method = RequestMethod.GET)
	public String pwdFormInit() {
		return "system/passwordForm";
	}
	
	@ApiOperation(value = "修改个人密码", notes = "修改个人密码<br/>@auther dzhifang")
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public @ResponseBody String updatePwd(RespAccount entity) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		entity.setAccount(userDetails.getUsername());
		return accountServiceImpl.updatePwd(entity);
	}
	 
}
