/**
 * 
 */
package org.sj.oaprj.project.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 施工单位现场人员名称日报
 * @author Jack.Alexander
 *
 */
@Entity(name="T_CONSTCT_MEM_DAILY")
@Cacheable
public class ConstructionMemberDaily extends ID {
	private static final long serialVersionUID = 7104464272498289815L;
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
	private Project project;//工程项目
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_DEPART_ID")
	private ProjectDepart projectDepart;//分部分项工程
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_GROUP_ID")
	private ProjectGroup projectGroup;//项目组
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_DEPT_GROUP_ID")
	private ProjectDeptGroup projectDeptGroup;//分部分项目组
	@Column(name = "PRJ_NO")
	private Integer prjno;//序号
	@Column(name = "NAME")
	private String name;//姓名
	@Column(name = "SEX")
	private String sex;//性别
	@Column(name = "AGE")
	private Integer age;//年龄
	@Column(name = "IDENTITY_NO")
	private String identityNo;//身份证号
	@Column(name = "CONSTRUCT_ORG")
	private String constructOrg;//隶属单位
	@Column(name = "POST")
	private String post;//岗位或工种

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "IN_DATE")
	private Date inDate;//进入现场时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "REPORT_DATE")
	private Date reportDate;//上报日期
//	private Org reporterOrg;//呈报单位
	@Column(name = "STATE")
	private Integer state;//状态(10保存，50提交)

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectDepart getProjectDepart() {
		return projectDepart;
	}

	public void setProjectDepart(ProjectDepart projectDepart) {
		this.projectDepart = projectDepart;
	}

	public ProjectGroup getProjectGroup() {
		return projectGroup;
	}

	public void setProjectGroup(ProjectGroup projectGroup) {
		this.projectGroup = projectGroup;
	}

	public ProjectDeptGroup getProjectDeptGroup() {
		return projectDeptGroup;
	}

	public void setProjectDeptGroup(ProjectDeptGroup projectDeptGroup) {
		this.projectDeptGroup = projectDeptGroup;
	}

	public Integer getPrjno() {
		return prjno;
	}

	public void setPrjno(Integer prjno) {
		this.prjno = prjno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getConstructOrg() {
		return constructOrg;
	}

	public void setConstructOrg(String constructOrg) {
		this.constructOrg = constructOrg;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
