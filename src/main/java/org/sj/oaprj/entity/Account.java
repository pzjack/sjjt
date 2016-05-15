package org.sj.oaprj.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name="T_ACCOUNT")
@Cacheable
public class Account extends ID {
	private static final long serialVersionUID = 4162869365157041576L;
	private String account;
	private String name;
	private String salt;
	private String password;
	private Integer state;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Employee user;
	@ManyToMany(targetEntity=org.sj.oaprj.entity.Role.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(
            name = "T_ACCOUNT_ROLE", 
            joinColumns = @JoinColumn(name = "ACCOUNT_ID"), 
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
	private List<Role> roles;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Employee getUser() {
		return user;
	}
	public void setUser(Employee user) {
		this.user = user;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}