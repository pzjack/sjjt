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
 * 分部分工程审批各单位人员及角色
 * @author Jack.Alexander
 *
 */
@Entity(name="T_PROJECT_DEPT_GRP")
@Cacheable
public class ProjectDeptGroup extends ID {
	private static final long serialVersionUID = -671166582114778306L;
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_DEPART_ID")
	private ProjectDepart projectDepart;//分部分项工程
	@Column(name = "ROLE_NAME")
	private String rolename;//角色名称
	@Column(name = "ROLE_SIGN")
	private Integer rolesign;//角色标识
	@Column(name = "ORG_TYPE")
	private Integer orgtype;//组织类型(施工单位、监理单位、建设单位)
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