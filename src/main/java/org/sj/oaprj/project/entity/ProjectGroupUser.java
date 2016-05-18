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

import org.sj.oaprj.entity.Employee;
import org.sj.oaprj.entity.ID;

/**
 * 项目组人员
 * @author Jack.Alexander
 *
 */
@Entity(name="T_PROJECT_GROUP_USER")
@Cacheable
public class ProjectGroupUser extends ID {
	private static final long serialVersionUID = -7830552612931542872L;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_GROUP_ID")
	private ProjectGroup projectgroup;//工程项目组
	
//	@NotNull
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PROJECT_ID")
//	private Project project;//工程项目
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
	private Employee user;

	@Column(name = "NAME")
	private String name;//名称
	
	@Column(name = "ROLE")
	private String role;//角色
	
	@Column(name = "POST")
	private String post;//职位

	public ProjectGroup getProjectgroup() {
		return projectgroup;
	}

	public void setProjectgroup(ProjectGroup projectgroup) {
		this.projectgroup = projectgroup;
	}

//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

}