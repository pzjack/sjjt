/**
 * 
 */
package org.sj.oaprj.core;

/**
 * 用户类型
 * @author Jack.Alexander
 *
 */
public enum UserType {

	EMPLOYEE(0),	//建设方员工
	SUPERVISION(10),//监理方用户
	CONSTRUCTION(30);//施工方用户
	
	private Integer intState;
	private String strName;
	
	UserType(Integer usertype) {
		switch(usertype) {
		case 1:
			this.intState = usertype;
			this.strName = "employee";
			break;
		case 10:
			this.intState = usertype;
			this.strName = "supervision";
			break;
		default:
			this.intState = 30;
			this.strName = "construction";
		}
	}
	public Integer getIntState() {
		return intState;
	}
	public String getStrName() {
		return strName;
	}
}
