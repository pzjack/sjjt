/**
 * 
 */
package org.sj.oaprj.project.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.sj.oaprj.entity.ID;

/**
 * 项目组
 * @author zhen.pan
 *
 */
@Entity(name="T_PROJECT_GROUP")
@Cacheable
public class ProjectGroup extends ID {
	private static final long serialVersionUID = -7830552612931542872L;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESC")
	private String desc;
//	@Column(name = "DEP_ID")
//	private Org department;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}