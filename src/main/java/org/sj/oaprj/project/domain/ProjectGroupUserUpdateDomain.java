/**
 * 
 */
package org.sj.oaprj.project.domain;

/**
 * @author zhen.pan
 *
 */
public class ProjectGroupUserUpdateDomain {
	protected Long id;
	private String name;//用户名称（冗余）
	private Long projectgroupId;//项目组ID
	private String projectgroupName;//项目组名称	
	private Long userId;//用户ID
	private String role;//角色
	private String post;//职位
	
	public ProjectGroupUserUpdateDomain() {}
	
	public ProjectGroupUserUpdateDomain(Long id, String name, String role, String post, String projectgroupName) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.post = post;
		this.projectgroupName = projectgroupName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getProjectgroupId() {
		return projectgroupId;
	}

	public void setProjectgroupId(Long projectgroupId) {
		this.projectgroupId = projectgroupId;
	}

	public String getProjectgroupName() {
		return projectgroupName;
	}

	public void setProjectgroupName(String projectgroupName) {
		this.projectgroupName = projectgroupName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
