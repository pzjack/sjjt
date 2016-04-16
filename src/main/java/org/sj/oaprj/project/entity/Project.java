/**
 * 
 */
package org.sj.oaprj.project.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;

/**
 * 工程项目
 * @author zhen.pan
 *
 */
@Entity(name="T_PROJECT")
@Cacheable
public class Project extends ID {
	private static final long serialVersionUID = -9097263749859320776L;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PROJECT_NO")
	private String projectno;
	@Column(name = "DESC")
	private String desc;
	@Column(name = "PRE_START_DATE")
	private Date prestartdate;
	@Column(name = "PRE_END_DATE")
	private Date preenddate;
	@Column(name = "START_DATE")
	private Date startdate;
	@Column(name = "END_DATE")
	private Date enddate;
	@Column(name = "PRE_COST")
	private BigDecimal precost;
	@Column(name = "COST")
	private BigDecimal cost;
//	@Column(name = "DEP_ID")
//	private Org department;
	@Column(name = "DEP_ID")
	private Long depId;
	@Column(name = "STATE")
	private Integer state;
	
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJ_GRP_ID")
	private ProjectGroup projectgroup;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getPrestartdate() {
		return prestartdate;
	}
	public void setPrestartdate(Date prestartdate) {
		this.prestartdate = prestartdate;
	}
	public Date getPreenddate() {
		return preenddate;
	}
	public void setPreenddate(Date preenddate) {
		this.preenddate = preenddate;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public BigDecimal getPrecost() {
		return precost;
	}
	public void setPrecost(BigDecimal precost) {
		this.precost = precost;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public ProjectGroup getProjectgroup() {
		return projectgroup;
	}
	public void setProjectgroup(ProjectGroup projectgroup) {
		this.projectgroup = projectgroup;
	}
	public Long getDepId() {
		return depId;
	}
	public void setDepId(Long depId) {
		this.depId = depId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
