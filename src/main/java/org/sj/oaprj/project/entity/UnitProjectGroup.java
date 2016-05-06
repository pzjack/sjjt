/**
 * 
 */
package org.sj.oaprj.project.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;

/**
 * 单位工程审批环节单位人员及角色
 * @author Jack.Alexander
 *
 */
@Entity(name="T_UNIT_PROJECT_GROUP")
@Cacheable
public class UnitProjectGroup extends ID {
	private static final long serialVersionUID = -671166582114778306L;
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIT_PROJECT_ID")
	private UnitProject UnitProject;//单位工程
	@Column(name = "ROLE_NAME")
	private String rolename;//角色名称
	@Column(name = "ROLE_SIGN")
	private Integer rolesign;//角色标识
	@Column(name = "ORG_TYPE")
	private Integer orgtype;//单位类型(施工单位、监理单位、建设单位)
	
	public UnitProject getUnitProject() {
		return UnitProject;
	}
	public void setUnitProject(UnitProject unitProject) {
		UnitProject = unitProject;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Integer getRolesign() {
		return rolesign;
	}
	public void setRolesign(Integer rolesign) {
		this.rolesign = rolesign;
	}
	public Integer getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(Integer orgtype) {
		this.orgtype = orgtype;
	}
}