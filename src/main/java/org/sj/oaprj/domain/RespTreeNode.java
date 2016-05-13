package org.sj.oaprj.domain;

import java.util.ArrayList;
import java.util.List;

public class RespTreeNode {

	private String id;
	private String text;
	private String iconCls;
	private String state;
	private boolean checked;
	private List<RespTreeNode> children;

	public RespTreeNode() {
		children = new ArrayList<RespTreeNode>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<RespTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<RespTreeNode> children) {
		this.children = children;
	}

}
