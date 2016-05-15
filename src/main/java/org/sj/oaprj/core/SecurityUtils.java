package org.sj.oaprj.core;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {
	
	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails springSecurityUser = null;
        String userName = null;
        if (authentication != null) {
        /*
         * 获取用户的角色ID列表、角色名称列表、用户姓名、用户ID信息，可以参考下边注释掉的这部分代码
         * 	SecurityUserDetail securityUser = null;
        	if(authentication.getPrincipal() instanceof SecurityUserDetail) {
        		securityUser = (SecurityUserDetail)authentication.getPrincipal();
        		StringBuilder sb = new StringBuilder();
        		if(null != securityUser.getRoleIds()) {
        			Long[] ids = securityUser.getRoleIds();
        			for(Long id : ids) {
        				sb.append(id).append(",");
        			}
        		}
        		StringBuilder sb2 = new StringBuilder();
        		if(null != securityUser.getRoleNames()) {
        			String[] names = securityUser.getRoleNames();
        			for(String name : names) {
        				sb2.append(name).append(",");
        			}
        		}
        		System.out.println(sb.toString() + "\t" + securityUser.getUserId() + "\t" + sb2.toString());
        	}*/
            if (authentication.getPrincipal() instanceof UserDetails) {
                springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
        if (authorities != null) {
//            for (GrantedAuthority authority : authorities) {
//                if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
//                    return false;
//                }
//            }
        }
        return true;
    }


    /**
     * If the current user has a specific security role.
     */
    public static boolean isUserInRole(String role) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(role));
            }
        }
        return false;
    }
}
