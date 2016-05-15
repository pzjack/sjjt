/**
 * 
 */
package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 陕建集团员工(包含其它公司用户)管理
 * @author zhen.pan
 *
 */
@Entity(name="T_EMPLOYEE")
@Cacheable
public class Employee extends ID {
	private static final long serialVersionUID = 5019960959371839014L;
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ACCOUNT_ID")
//	private Account account;//账号
	private String name;//姓名
	@Column(name = "EMPLOYEE_NO")
	private String employeeNo;//员工编号
	private String phone;//电话
	private String email;//邮箱
	private String post;//职位、职务
	@Column(name = "ID_NUMBER")
	private String idNumber;//证件号码
	private String remark;//备注说明
	@Column(name = "BAIDU_ID")
	private String baiduId;//百度ID
	@Column(name = "USER_TYPE")
	private Integer userType;//用户类型:0 陕建,10监理,30施工单位
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPART_ID")
	private Department depart;//部门
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;//供应商
//	public Account getAccount() {
//		return account;
//	}
//	public void setAccount(Account account) {
//		this.account = account;
//	}
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
	public String getBaiduId() {
		return baiduId;
	}
	public void setBaiduId(String baiduId) {
		this.baiduId = baiduId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
	public Department getDepart() {
		return depart;
	}
	public void setDepart(Department depart) {
		this.depart = depart;
	}
}
