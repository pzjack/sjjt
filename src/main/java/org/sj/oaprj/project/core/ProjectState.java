/**
 * 
 */
package org.sj.oaprj.project.core;

/**
 * 项目的状态
 * @author Jack.Alexander
 *
 */
public enum ProjectState {
CREATED(10),//创建
INIT(20),//初始化
BUILDING(30),//建设中
COMPLETE(40),//完成
ARCHIVE(50); //归档

private Integer intValue;
private String strValue;

ProjectState(int intValue) {
	switch(intValue) {
	case 10:
		this.intValue = intValue;
		this.strValue = "created";
		break;
	case 20:
		this.intValue = intValue;
		this.strValue = "init";
		break;
	case 30:
		this.intValue = intValue;
		this.strValue = "building";
		break;
	case 40:
		this.intValue = intValue;
		this.strValue = "complete";
		break;
	default:
		this.intValue = 50;
		this.strValue = "archive";
	}
}
public Integer getIntValue() {
	return intValue;
}
public String getStrValue() {
	return strValue;
}
}