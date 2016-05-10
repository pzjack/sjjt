package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="T_DEPARTMENT")
@Cacheable
public class Department extends ID {
	private static final long serialVersionUID = -2889062832236287380L;
	@Column(name = "DEPT_NAME")
	private String deptName;
	@Column(name = "DEPT_CODE")
	private String deptCode;
	@Column(name = "DEPT_DESC")
	private String deptDesc;
	@Column(name = "DEPT_TYPE")
	private int deptType;
	@Column(name = "PARENT_ID")
	private int parentId;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public int getDeptType() {
		return deptType;
	}
	public void setDeptType(int deptType) {
		this.deptType = deptType;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
 
}
