/**
 * 
 */
package org.sj.oaprj.project.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;

/**
 * 分部分工程审批各单位人员及角色
 * @author zhen.pan
 *
 */
@Entity(name="T_PROJECT_DEPT_GRP")
@Cacheable
public class ProjectDeptGroup extends ID {
	private static final long serialVersionUID = -671166582114778306L;
	@NotNull
    @ManyToOne
    @JoinColumn(name = "PROJECT_DEPART_ID")
	private ProjectDepart projectDepart;
	@Column(name = "ROLE_NAME")
	private String rolename;
	@Column(name = "ROLE_SIGN")
	private Integer rolesign;
	@Column(name = "ORG_TYPE")
	private Integer orgtype;
	public ProjectDepart getProjectDepart() {
		return projectDepart;
	}
	public void setProjectDepart(ProjectDepart projectDepart) {
		this.projectDepart = projectDepart;
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