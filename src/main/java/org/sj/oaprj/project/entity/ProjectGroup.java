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

import org.sj.oaprj.entity.Department;
import org.sj.oaprj.entity.ID;

/**
 * 项目组
 * @author Jack.Alexander
 *
 */
@Entity(name="T_PROJECT_GROUP")
@Cacheable
public class ProjectGroup extends ID {
	private static final long serialVersionUID = -7830552612931542872L;
	@Column(name = "NAME")
	private String name;//项目组名称
	@Column(name = "REMARK")
	private String remark;//项目组描述说明
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEP_ID")
	private Department department;//所属部门
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}