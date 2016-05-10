package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name = "T_CODE_DATA")
@Cacheable
public class CodeData extends ID {
	@Column(name = "CODE_TYPE")
	private Integer codeType;
	@Column(name = "DATA_TYPE")
	private Integer dataType;
	@Column(name = "DATA_NAME")
	private String dataName;
	public Integer getCodeType() {
		return codeType;
	}
	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

}
