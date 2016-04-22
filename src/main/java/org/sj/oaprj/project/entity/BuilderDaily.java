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
 * 建设单位日报
 * @author Jack.Alexander
 *
 */
@Entity(name="T_BUILDER_DAILY")
@Cacheable
public class BuilderDaily extends ID {
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
	private ProjectDeptGroup projectDeptGroup;//分部分项目组
	@NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERVISION_DAILY_ID")
	private SupervisionDaily supervisionDaily;//对应的监理单位日报
	@Column(name = "CONSTUCTION_TOTAL")
	private Integer constuctionTotal;//施工总人数
	@Column(name = "CONSTUCTION_IN")
	private Integer constuctionIn;//施工新进人数
	@Column(name = "CONSTUCTION_OUT")
	private Integer constuctionOut;//施工离去人数
	@Column(name = "MANAGER_TOTAL")
	private Integer managerTotal;//管理人员总数
	@Column(name = "MANAGER_IN")
	private Integer managerIn;//管理新进人数
	@Column(name = "MANAGER_OUT")
	private Integer managerOut;//管理离去人数
	@Column(name = "FIRST_LINE_TOTAL")
	private Integer firstLineTotal;//一线施工人员总数
	@Column(name = "FIRST_LINE_IN")
	private Integer firstLineIn;//一线施工人员新进人数
	@Column(name = "FIRST_LINE_OUT")
	private Integer firstLineOut;//一线施工人员离去人数
	@Column(name = "JOB_GROUP_NAME")
	private String jobGroupName;//分包队名称
	@Column(name = "JOB_GROUP_TOTAL")
	private Integer jobGroupTotal;//分包队人员总数
	@Column(name = "JOB_GROUP_IN")
	private Integer jobGroupIn;//分包队新进人数
	@Column(name = "JOB_GROUP_OUT")
	private Integer jobGroupOut;//分包队离去人数
	@Column(name = "COMPLETE_DAILY")
	private Boolean completeDaily;//是否完成当日计划进度
	@Column(name = "DAILY_PROPORTION")
	private Double dailyProportion;//完成当日计划进度比例
	@Column(name = "NEXT_DAY_PLAN")
	private String nextDayPlan;//施工质量状况明天计划
	@Column(name = "QUALITY")
	private String quality;//施工质量状况
	@Column(name = "SAFE_CIVILIZATION")
	private String safeCivilization;//安全文明施工状况
	@Column(name = "PROBLEM")
	private String problem;//施工中存在的问题
	@Column(name = "SUGGEST")
	private String suggest;//问题处理措施及建议意见
	@Column(name = "BUILD_PROBLEM")
	private String buildProblem;//需建设单位解决的问题
	@Column(name = "PROJECT_MANAGER")
	private String projectManager;//项目经理
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
	public SupervisionDaily getSupervisionDaily() {
		return supervisionDaily;
	}
	public void setSupervisionDaily(SupervisionDaily supervisionDaily) {
		this.supervisionDaily = supervisionDaily;
	}
	public Integer getConstuctionTotal() {
		return constuctionTotal;
	}
	public void setConstuctionTotal(Integer constuctionTotal) {
		this.constuctionTotal = constuctionTotal;
	}
	public Integer getConstuctionIn() {
		return constuctionIn;
	}
	public void setConstuctionIn(Integer constuctionIn) {
		this.constuctionIn = constuctionIn;
	}
	public Integer getConstuctionOut() {
		return constuctionOut;
	}
	public void setConstuctionOut(Integer constuctionOut) {
		this.constuctionOut = constuctionOut;
	}
	public Integer getManagerTotal() {
		return managerTotal;
	}
	public void setManagerTotal(Integer managerTotal) {
		this.managerTotal = managerTotal;
	}
	public Integer getManagerIn() {
		return managerIn;
	}
	public void setManagerIn(Integer managerIn) {
		this.managerIn = managerIn;
	}
	public Integer getManagerOut() {
		return managerOut;
	}
	public void setManagerOut(Integer managerOut) {
		this.managerOut = managerOut;
	}
	public Integer getFirstLineTotal() {
		return firstLineTotal;
	}
	public void setFirstLineTotal(Integer firstLineTotal) {
		this.firstLineTotal = firstLineTotal;
	}
	public Integer getFirstLineIn() {
		return firstLineIn;
	}
	public void setFirstLineIn(Integer firstLineIn) {
		this.firstLineIn = firstLineIn;
	}
	public Integer getFirstLineOut() {
		return firstLineOut;
	}
	public void setFirstLineOut(Integer firstLineOut) {
		this.firstLineOut = firstLineOut;
	}
	public String getJobGroupName() {
		return jobGroupName;
	}
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}
	public Integer getJobGroupTotal() {
		return jobGroupTotal;
	}
	public void setJobGroupTotal(Integer jobGroupTotal) {
		this.jobGroupTotal = jobGroupTotal;
	}
	public Integer getJobGroupIn() {
		return jobGroupIn;
	}
	public void setJobGroupIn(Integer jobGroupIn) {
		this.jobGroupIn = jobGroupIn;
	}
	public Integer getJobGroupOut() {
		return jobGroupOut;
	}
	public void setJobGroupOut(Integer jobGroupOut) {
		this.jobGroupOut = jobGroupOut;
	}
	public Boolean getCompleteDaily() {
		return completeDaily;
	}
	public void setCompleteDaily(Boolean completeDaily) {
		this.completeDaily = completeDaily;
	}
	public Double getDailyProportion() {
		return dailyProportion;
	}
	public void setDailyProportion(Double dailyProportion) {
		this.dailyProportion = dailyProportion;
	}
	public String getNextDayPlan() {
		return nextDayPlan;
	}
	public void setNextDayPlan(String nextDayPlan) {
		this.nextDayPlan = nextDayPlan;
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
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
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