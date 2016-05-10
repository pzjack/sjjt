package org.sj.oaprj.domain;

import org.sj.oaprj.entity.Supplier;

@SuppressWarnings("serial")
public class RespSupplier extends Supplier {
	private String supplierTypeName;

	public String getSupplierTypeName() {
		return supplierTypeName;
	}

	public void setSupplierTypeName(String supplierTypeName) {
		this.supplierTypeName = supplierTypeName;
	}

}
