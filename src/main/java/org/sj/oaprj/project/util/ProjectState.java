/**
 * 
 */
package org.sj.oaprj.project.util;

/**
 * 项目状态，暂时适用于所有各级项目
 * @author Jack.Alexander
 *
 */
public enum ProjectState {
	CREATE(0),//创建
	INIT(10),//初始化
	BUILDING(50),//建设中
	COMPLETE(100);//已经完成
	private Integer state = 0;
	ProjectState(Integer state) {
		if(null == state) {
			state = 0;
		} else {
			switch(state) {
			case 0  :
			case 10 :
			case 50 :
			case 100:
				this.state = state;
				break;
			default :
				this.state = 0;
			}
		}
	}
	public Integer getState() {
		return state;
	}
}
