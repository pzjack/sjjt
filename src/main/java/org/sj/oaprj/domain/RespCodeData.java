package org.sj.oaprj.domain;

import org.sj.oaprj.entity.CodeData;

@SuppressWarnings("serial")
public class RespCodeData extends CodeData {
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
