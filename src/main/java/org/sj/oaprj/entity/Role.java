/**
 * 
 */
package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author zhen.pan
 *
 */
@Entity(name="T_ROLE")
@Cacheable
public class Role extends ID {
	private static final long serialVersionUID = -490573508594772434L;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ROLE_DESC")
	private String desc;
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
