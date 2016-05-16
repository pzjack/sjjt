/**
 * 
 */
package org.sj.oaprj.project.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目新增、修改及查询显示等前端所需JSON传递对象
 * @author Jack.Alexander
 *
 */
public class ProjectUpdateDomain {
	private Long id;
	private String name;//工程项目名称
	private String projectno;//工程项目编号
	private String remark;//工程项目描述说明
	private Long departmentId;//所属部门
	private String departmentName;//所属部门
	private Long projectgroupId;//项目组
	private String projectgroupName;//项目组
	private Integer state;//状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createtime = null;
	
	public ProjectUpdateDomain () {}
	public ProjectUpdateDomain (Long id, String name, String projectno, String remark, String departmentName, String projectgroupName, Integer state, Date createtime) {
		this.id = id;
		this.name = name;
		this.projectno = projectno;
		this.remark = remark;
		this.departmentName = departmentName;
		this.projectgroupName = projectgroupName;
		this.state = state;
		this.createtime = createtime;
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
	public String getProjectno() {
		return projectno;
	}
	public void setProjectno(String projectno) {
		this.projectno = projectno;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}