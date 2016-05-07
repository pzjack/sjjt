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
 * 分部分项工程
 * @author Jack.Alexander
 *
 */
@Entity(name="T_PROJECT_DEPART")
@Cacheable
public class ProjectDepart extends ID {
	private static final long serialVersionUID = -6462204707814166706L;
	@Column(name = "NAME")
	private String name;//分部分项工程名称
	@Column(name = "COMPLETE_NAME")
	private String completeName;//完整名称
	@Column(name = "DEPART_SUPER")
	private String departSuper;//分部分项的上一级名称
	@Column(name = "DEPART_NO")
	private String departno;//编号
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
	private Project project;//所属工程项目
//	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIT_PROJECT_ID")
	private UnitProject unitProject;//所属单位工程项目
	@Column(name = "PROFESSION")
	private String profession;//专业
	@Column(name = "TOTAL_PROJECT")
	private BigDecimal totalproject;//工程总量
	@Column(name = "UNIT")
	private String unit;//计量单位
	@Column(name = "STANDARD")
	private String standard;//规格
	@Column(name = "SUM_OF_BUSINESS")
	private BigDecimal sumofbusiness;//累计完成营业额
	@Column(name = "STATE")
	private Integer state;//状态
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	public String getDepartSuper() {
		return departSuper;
	}
	public void setDepartSuper(String departSuper) {
		this.departSuper = departSuper;
	}
	public String getDepartno() {
		return departno;
	}
	public void setDepartno(String departno) {
		this.departno = departno;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public UnitProject getUnitProject() {
		return unitProject;
	}
	public void setUnitProject(UnitProject unitProject) {
		this.unitProject = unitProject;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
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
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public BigDecimal getSumofbusiness() {
		return sumofbusiness;
	}
	public void setSumofbusiness(BigDecimal sumofbusiness) {
		this.sumofbusiness = sumofbusiness;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}