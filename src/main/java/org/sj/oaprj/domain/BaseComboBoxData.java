/**
 * 
 */
package org.sj.oaprj.domain;

/**
 * 下拉框的缺省数据模型
 * @author zhen.pan
 *
 */
public class BaseComboBoxData {
	protected Long id;
	protected String name;
	public BaseComboBoxData () {}
	public BaseComboBoxData (Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
