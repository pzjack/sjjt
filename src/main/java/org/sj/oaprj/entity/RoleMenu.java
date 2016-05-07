package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="T_ROLE_MENU")
@Cacheable
public class RoleMenu extends ID{
	private static final long serialVersionUID = -311273999581459124L;
	@Column(name = "ROLE_ID")
	private Long roleId;
	@Column(name = "MENU_ID")
	private Long menuId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
}
