package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="T_ACCOUNT_ROLE")
@Cacheable
public class AccountRole extends ID {
	private static final long serialVersionUID = 9030718434048563782L;
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	@Column(name = "ROLE_ID")
	private Long roleId;
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}