package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="T_MENU")
@Cacheable
public class Menu extends ID {
	private static final long serialVersionUID = -6332708383254412958L;
	@Column(name = "NAME")
	private String name;
	@Column(name = "URL")
	private String url;
	@Column(name = "PARENT_ID")
	private Integer parentId;
	@Column(name = "ICON")
	private String icon;
	@Column(name = "ORDER")
	private Integer order;
	@Column(name = "IS_LEAF")
	private Integer isLeaf;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
