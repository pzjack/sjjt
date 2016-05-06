/**
 * 
 */
package org.sj.oaprj.project.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;

/**
 * 单位工程
 * @author Jack.Alexander
 *
 */
@Entity(name="T_UNIT_PROJECT")
@Cacheable
public class UnitProject extends ID {
	private static final long serialVersionUID = 6821754467477423687L;
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
	private Project project;//所属工程项目
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INDIVIDUAL_PROJECT_ID")
	private IndividualProject individualProject;//单项工程
	@Column(name = "NAME")
	private String name;//单位工程名称（一般土建工程）
	@Column(name = "COMPLETE_NAME")
	private String completeName;//单位工程完整名称（翠园锦绣9#楼一般土建工程）
	@Column(name = "DESC")
	private String desc;//描述说明
	@Column(name = "STATE")
	private Integer state;//状态
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public IndividualProject getIndividualProject() {
		return individualProject;
	}
	public void setIndividualProject(IndividualProject individualProject) {
		this.individualProject = individualProject;
	}
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
