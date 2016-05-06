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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 监理日报
 * @author Jack.Alexander
 *
 */
@Entity(name="T_SUPERVISION_DAILY")
@Cacheable
public class SupervisionDaily extends ID {
	private static final long serialVersionUID = 5570006176468482966L;
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
	private UnitProjectGroup projectDeptGroup;//分部分项目组
	@NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONSTRUCTION_UNIT_DAILY_ID")
	private ConstructionUnitDaily constructionUnitDaily;//对应的施工单位日报
	@Column(name = "SUPERVISION_TOTAL")
	private Integer supervisionTotal;//监理人员总数
	@Column(name = "EARTHWORK_SUPERVISION")
	private Integer earthworkSupervision;//土建监理人员
	@Column(name = "ASSEMBLY")
	private Integer assembly;//安装监理人员
	@Column(name = "CONSTUCTION_TOTAL")
	private Integer constuctionTotal;//施工单位总人数
	@Column(name = "EARTHWORK_CONSTUCTION")
	private Integer earthworkConstuction;//施工单位土建总人数
	@Column(name = "ASSEMBLY_CONSTUCTION")
	private Integer assemblyConstuction;//施工单位安装总人数
	@Column(name = "EARTHWORK_MANAGER")
	private Integer earthworkManager;//施工单位土建管理人员
	@Column(name = "ASSEMBLY_MANAGER")
	private Integer assemblyManager;//施工单位安装管理人员
	@Column(name = "QUALITY")
	private String quality;//工程质量状况
	@Column(name = "SAFE_CIVILIZATION")
	private String safeCivilization;//安全文明施工状况
	@Column(name = "COMPLETE_DAILY")
	private Boolean completeDaily;//是否完成当日计划进度
	@Column(name = "REASON")
	private String reason;//超前或滞后原因
	@Column(name = "PROBLEM")
	private String problem;//施工中存在的问题
	@Column(name = "SUGGEST")
	private String suggest;//针对问题采取的措施及建议意见
	@Column(name = "BUILD_PROBLEM")
	private String buildProblem;//需建设单位解决的问题
	@Column(name = "SENIOR_SUPERVIS_ENGINEER")
	private String seniorSupervisEngineer;//总监理师
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
	public UnitProjectGroup getProjectDeptGroup() {
		return projectDeptGroup;
	}
	public void setProjectDeptGroup(UnitProjectGroup projectDeptGroup) {
		this.projectDeptGroup = projectDeptGroup;
	}
	public ConstructionUnitDaily getConstructionUnitDaily() {
		return constructionUnitDaily;
	}
	public void setConstructionUnitDaily(ConstructionUnitDaily constructionUnitDaily) {
		this.constructionUnitDaily = constructionUnitDaily;
	}
	public Integer getSupervisionTotal() {
		return supervisionTotal;
	}
	public void setSupervisionTotal(Integer supervisionTotal) {
		this.supervisionTotal = supervisionTotal;
	}
	public Integer getEarthworkSupervision() {
		return earthworkSupervision;
	}
	public void setEarthworkSupervision(Integer earthworkSupervision) {
		this.earthworkSupervision = earthworkSupervision;
	}
	public Integer getAssembly() {
		return assembly;
	}
	public void setAssembly(Integer assembly) {
		this.assembly = assembly;
	}
	public Integer getConstuctionTotal() {
		return constuctionTotal;
	}
	public void setConstuctionTotal(Integer constuctionTotal) {
		this.constuctionTotal = constuctionTotal;
	}
	public Integer getEarthworkConstuction() {
		return earthworkConstuction;
	}
	public void setEarthworkConstuction(Integer earthworkConstuction) {
		this.earthworkConstuction = earthworkConstuction;
	}
	public Integer getAssemblyConstuction() {
		return assemblyConstuction;
	}
	public void setAssemblyConstuction(Integer assemblyConstuction) {
		this.assemblyConstuction = assemblyConstuction;
	}
	public Integer getEarthworkManager() {
		return earthworkManager;
	}
	public void setEarthworkManager(Integer earthworkManager) {
		this.earthworkManager = earthworkManager;
	}
	public Integer getAssemblyManager() {
		return assemblyManager;
	}
	public void setAssemblyManager(Integer assemblyManager) {
		this.assemblyManager = assemblyManager;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getSafeCivilization() {
		return safeCivilization;
	}
	public void setSafeCivilization(String safeCivilization) {
		this.safeCivilization = safeCivilization;
	}
	public Boolean getCompleteDaily() {
		return completeDaily;
	}
	public void setCompleteDaily(Boolean completeDaily) {
		this.completeDaily = completeDaily;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String getBuildProblem() {
		return buildProblem;
	}
	public void setBuildProblem(String buildProblem) {
		this.buildProblem = buildProblem;
	}
	public String getSeniorSupervisEngineer() {
		return seniorSupervisEngineer;
	}
	public void setSeniorSupervisEngineer(String seniorSupervisEngineer) {
		this.seniorSupervisEngineer = seniorSupervisEngineer;
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