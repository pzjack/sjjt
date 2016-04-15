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
 * 项目组人员
 * @author zhen.pan
 *
 */
@Entity(name="T_PROJECT_GROUP_USER")
@Cacheable
public class ProjectGroupUser extends ID {
	private static final long serialVersionUID = -7830552612931542872L;
	@NotNull
	@ManyToOne
    @JoinColumn(name = "PROJECT_GROUP_ID")
	private ProjectGroup projectgroup;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "PROJECT_ID")
	private Project project;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "POST")
	private String post;

	public ProjectGroup getProjectgroup() {
		return projectgroup;
	}

	public void setProjectgroup(ProjectGroup projectgroup) {
		this.projectgroup = projectgroup;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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