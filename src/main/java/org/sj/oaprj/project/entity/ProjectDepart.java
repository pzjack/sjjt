/**
 * 
 */
package org.sj.oaprj.project.entity;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;

/**
 * 分部分项工程
 * @author zhen.pan
 *
 */
@Entity(name="T_PROJECT_DEPART")
@Cacheable
public class ProjectDepart extends ID {
	private static final long serialVersionUID = -6462204707814166706L;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DEPART_NO")
	private String departno;
	@NotNull
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
	private Project project;
	@Column(name = "PROFESSION")
	private String profession;
	@Column(name = "TOTAL_PROJECT")
	private BigDecimal totalproject;
	@Column(name = "UNIT")
	private String unit;
	@Column(name = "STANDARD")
	private String standard;
	@Column(name = "SUM_OF_BUSINESS")
	private BigDecimal sumofbusiness;
	@Column(name = "STATE")
	private Integer state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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