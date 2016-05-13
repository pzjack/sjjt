package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name="T_CODE_TYPE")
@Cacheable
public class CodeType extends ID {
	@Column(name = "CODE_TYPE")
	private Integer  codeType;
	@Column(name = "TYPE_NAME")
	private String typeName;
	public Integer getCodeType() {
		return codeType;
	}
	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
