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
 * @author Jack.Alexander
 *
 */
@Entity(name="T_PROJECT_GROUP")
@Cacheable
public class ProjectGroup extends ID {
	private static final long serialVersionUID = -7830552612931542872L;
	@Column(name = "NAME")
	private String name;//项目组名称
	@Column(name = "DESC")
	private String desc;//项目组描述说明
//	@Column(name = "DEP_ID")
//	private Org department;//部门
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