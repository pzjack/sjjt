package org.sj.oaprj.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.sj.oaprj.entity.Department;
import org.sj.oaprj.entity.Role;

/**
 *  普通用户新增、修改对象
 * @author Jack.Alexander
 *
 */
public class UserUpdateDomain {
private Long id;
@NotNull
@Size(min=2, max=10)
private String name;
private String employeeNo;
@NotNull
@Size(min=6, max=15)
private String account;
private String pwd;
@NotNull
@Size(min=6, max=15)
private String phone;//电话
private String email;//邮箱
private String post;//职位、职务
private String idNumber;//证件号码
private String remark;//备注说明
private String rolestr;
private Long departId;
private Long supplierId;

private List<Department> deps;
private List<Role> roles;
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
public String getEmployeeNo() {
	return employeeNo;
}
public void setEmployeeNo(String employeeNo) {
	this.employeeNo = employeeNo;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPost() {
	return post;
}
public void setPost(String post) {
	this.post = post;
}
public String getIdNumber() {
	return idNumber;
}
public void setIdNumber(String idNumber) {
	this.idNumber = idNumber;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getRolestr() {
	return rolestr;
}
public void setRolestr(String rolestr) {
	this.rolestr = rolestr;
}
public Long getDepartId() {
	return departId;
}
public void setDepartId(Long departId) {
	this.departId = departId;
}
public Long getSupplierId() {
	return supplierId;
}
public void setSupplierId(Long supplierId) {
	this.supplierId = supplierId;
}
public List<Department> getDeps() {
	return deps;
}
public void setDeps(List<Department> deps) {
	this.deps = deps;
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
}
}
