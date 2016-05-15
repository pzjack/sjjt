package org.sj.oaprj.core.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 扩展@UserDetails接口，方便获取自定义的角色ID列表、用户ID、姓名、角色列表等信息
 * @author zhen.pan
 *
 */
public interface SecurityUserDetail extends UserDetails {
	Long[] getRoleIds();
	String[] getRoleNames();
	String getName();
	Long getUserId();
}
