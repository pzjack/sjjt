package org.sj.oaprj.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name="T_SUPPLIER")
@Cacheable
public class Supplier extends ID {
	@Column(name = "SUPPLIER_NAME")
	private String  supplierName;
	@Column(name = "SUPPLIER_TYPE")
	private Integer supplierType;
	@Column(name = "SUPPLIER_DESC")
	private String supplierDesc;
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	public String getSupplierDesc() {
		return supplierDesc;
	}
	public void setSupplierDesc(String supplierDesc) {
		this.supplierDesc = supplierDesc;
	}
	
}
