/**
 * 
 */
package org.sj.oaprj.project.domain;

import java.util.List;

import org.sj.oaprj.entity.Department;

/**
 * @author zhen.pan
 *
 */
public class ProjectGroupUpdateDomain {
	protected Long id;
	private String name;//项目组名称
	private String remark;//项目组描述说明
	private Long departmentId;//部门
	private String departmentName;//部门
	
	public ProjectGroupUpdateDomain() {}
	
	public ProjectGroupUpdateDomain(Long id, String name , String remark, String deparmentName) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.departmentName = deparmentName;
	}
	
	private List<Department> deps;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Department> getDeps() {
		return deps;
	}
	public void setDeps(List<Department> deps) {
		this.deps = deps;
	}
}
