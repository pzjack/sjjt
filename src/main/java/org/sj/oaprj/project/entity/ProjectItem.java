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
 * @author Jack.Alexander
 *
 */
@Entity(name="T_PROJECT_ITEM")
@Cacheable
public class ProjectItem extends ID {
	private static final long serialVersionUID = -6462204707814166706L;
	@Column(name = "PRJ_NO")
	private String prjno;//序号
	@Column(name = "NAME")
	private String name;//名称
	@Column(name = "ITEM_NO")
	private String itemno;//编号
	@Column(name = "FEATURE")
	private String feature;//项目特征
	@Column(name = "CONTENT")
	private String content;//工程内容
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_DEPART_ID")
	private ProjectDepart projectDepart;//分部分项工程
	@Column(name = "UNIT")
	private String unit;//计量单位
	@Column(name = "TOTAL_PROJECT")
	private BigDecimal totalproject;//工程数量
	@Column(name = "UNIT_PRICE")
	private BigDecimal unitprice;//综合单价
	@Column(name = "SUM_OF_BUSINESS")
	private BigDecimal sumofbusiness;//合价
	@Column(name = "STATE")
	private Integer state;//状态
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