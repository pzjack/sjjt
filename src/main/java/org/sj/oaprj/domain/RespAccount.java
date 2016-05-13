package org.sj.oaprj.domain;

import org.sj.oaprj.entity.Account;

@SuppressWarnings("serial")
public class RespAccount extends Account {

	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
