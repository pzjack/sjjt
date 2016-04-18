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
 * 清单子项
 * @author zhen.pan
 *
 */
@Entity(name="T_PROJECT_ITEM")
@Cacheable
public class ProjectItem extends ID {
	private static final long serialVersionUID = -6462204707814166706L;
	@Column(name = "PRJ_NO")
	private String prjno;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ITEM_NO")
	private String itemno;
	@Column(name = "FEATURE")
	private String feature;
	@Column(name = "CONTENT")
	private String content;
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_DEPART_ID")
	private ProjectDepart projectDepart;
	@Column(name = "UNIT")
	private String unit;
	@Column(name = "TOTAL_PROJECT")
	private BigDecimal totalproject;
	@Column(name = "UNIT_PRICE")
	private BigDecimal unitprice;
	@Column(name = "SUM_OF_BUSINESS")
	private BigDecimal sumofbusiness;
	@Column(name = "STATE")
	private Integer state;
	public String getPrjno() {
		return prjno;
	}
	public void setPrjno(String prjno) {
		this.prjno = prjno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItemno() {
		return itemno;
	}
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public BigDecimal getSumofbusiness() {
		return sumofbusiness;
	}
	public void setSumofbusiness(BigDecimal sumofbusiness) {
		this.sumofbusiness = sumofbusiness;
	}
	public ProjectDepart getProjectDepart() {
		return projectDepart;
	}
	public void setProjectDepart(ProjectDepart projectDepart) {
		this.projectDepart = projectDepart;
	}
	public BigDecimal getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}