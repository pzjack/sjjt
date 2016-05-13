/**
 * 
 */
package org.sj.oaprj.project.entity;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;

/**
 * 施工单位日报 
 * @author Jack.Alexander
 *
 */
@Entity(name="T_CONSTCT_UNIT_DAILY")
@Cacheable
public class ConstructionUnitDaily extends ID {
	private static final long serialVersionUID = 6813887246494176090L;
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
	private Project project;//工程项目
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNITPROJECT_ID")
	private UnitProject unitProject;//单位工程
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_GROUP_ID")
	private ProjectGroup projectGroup;//项目组
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_DEPT_GROUP_ID")
	private UnitProjectGroup projectDeptGroup;//单位工程项目组
//	@NotNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PROJECT_DEPART_ID")
//	private ProjectDepart projectDepart;//分部分项工程
	@Column(name = "PRJ_NO")
	private Integer prjno;//序号
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ITEM_ID")
	private ProjectItem item;//清单子项
	@Column(name = "NAME")
	private String name;//清单子项名称
	@Column(name = "ITEM_NO")
	private String itemNo;//清单子项编号
	@Column(name = "TOTAL_PROJECT")
	private BigDecimal totalproject;//总量
	@Column(name = "UNIT")
	private String unit;//单位
	@Column(name = "PLAN_COMPLETE")
	private BigDecimal planComplete;//计划完成
	@Column(name = "COMPLETE")
	private BigDecimal complete;//实际完成
	@Column(name = "COMPLETE_RATE")
	private Double completeRate;//完成率
	@Column(name = "AGGREGATED")
	private BigDecimal aggregated;//汇总
	@Column(name = "REPORTER_NAME")
	private String reporterName;//上报人姓名
	@Column(name = "YEAR")
	private Integer year;//年
	@Column(name = "MONTH")
	private Integer month;//月
	@Column(name = "DAY")
	private Integer day;//日
	@Column(name = "STATE")
	private Integer state;//状态(10保存，50提交)
	
//	private Org reporterOrg;//呈报单位
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Integer getPrjno() {
		return prjno;
	}
	public void setPrjno(Integer prjno) {
		this.prjno = prjno;
	}
	public UnitProject getUnitProject() {
		return unitProject;
	}
	public void setUnitProject(UnitProject unitProject) {
		this.unitProject = unitProject;
	}
	//	public ProjectDepart getProjectDepart() {
//		return projectDepart;
//	}
//	public void setProjectDepart(ProjectDepart projectDepart) {
//		this.projectDepart = projectDepart;
//	}
	public ProjectItem getItem() {
		return item;
	}
	public void setItem(ProjectItem item) {
		this.item = item;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public BigDecimal getTotalproject() {
		return totalproject;
	}
	public void setTotalproject(BigDecimal totalproject) {
		this.totalproject = totalproject;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getPlanComplete() {
		return planComplete;
	}
	public void setPlanComplete(BigDecimal planComplete) {
		this.planComplete = planComplete;
	}
	public BigDecimal getComplete() {
		return complete;
	}
	public void setComplete(BigDecimal complete) {
		this.complete = complete;
	}
	public Double getCompleteRate() {
		return completeRate;
	}
	public void setCompleteRate(Double completeRate) {
		this.completeRate = completeRate;
	}
	public BigDecimal getAggregated() {
		return aggregated;
	}
	public void setAggregated(BigDecimal aggregated) {
		this.aggregated = aggregated;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public UnitProjectGroup getProjectDeptGroup() {
		return projectDeptGroup;
	}
	public void setProjectDeptGroup(UnitProjectGroup projectDeptGroup) {
		this.projectDeptGroup = projectDeptGroup;
	}
	public ProjectGroup getProjectGroup() {
		return projectGroup;
	}
	public void setProjectGroup(ProjectGroup projectGroup) {
		this.projectGroup = projectGroup;
	}
}
