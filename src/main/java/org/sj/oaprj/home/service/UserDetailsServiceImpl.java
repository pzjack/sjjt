/**
 * 
 */
package org.sj.oaprj.home.service;

import java.util.Collection;
import java.util.List;

import org.sj.oaprj.entity.Account;
import org.sj.oaprj.entity.Role;
import org.sj.oaprj.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhen.pan
 *
 */
@Transactional(readOnly=true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Account> list = accountRepository.findByAccount(username);
		Account account = null;
		if(null != list && list.size() > 0) {
			account = list.get(0);
		}
		if(null == account) {
			throw new UsernameNotFoundException("It's not found any user.");
		}
		Long userId = (null == account.getUser()?null:account.getUser().getId());
		return new CustomUserDetails(username, account.getPassword(), userId, account.getName(), account.getRoles());
	}

	final static class CustomUserDetails implements UserDetails {
		private static final long serialVersionUID = 8683102959103260527L;
		private String username;
		private String password;
		private Long userId;
		private String name;
		private String[] roleNames;
		private Long[] roleIds;
		private CustomUserDetails(String username, String password, Long userId, String name, List<Role> roles) {
			this.username = username;
			this.password = password;
			this.userId = userId;
			this.name = name;
			if(null != roles) {
				roleNames = new String[roles.size()];
				roleIds = new Long[roles.size()];
				for(int i = 0; i < roleNames.length; i++) {
					Role r = roles.get(i);
					roleNames[i] = "ROLE_" + r.getName();
					roleIds[i] = r.getId();
				}
			}
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			if("admin".equals(username)) {
				return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
			} 
			else {
				if(null != roleNames) {
					return AuthorityUtils.createAuthorityList(roleNames);
				}
			}
			
			return AuthorityUtils.createAuthorityList();
		}
		
		public String[] getRoleNames() {
			return roleNames;
		}
		
		public Long[] getRoleIds() {
			return roleIds;
		}
		
		@Override
		public boolean equals(Object rhs) {
			if (rhs instanceof CustomUserDetails) {
				return username.equals(((CustomUserDetails) rhs).getUsername());
			}
			return false;
		}

		/**
		 * Returns the hashcode of the {@code username}.
		 */
		@Override
		public int hashCode() {
			return username.hashCode();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(super.toString()).append(": ");
			sb.append("Username: ").append(this.username).append("; ");
			sb.append("Password: [PROTECTED]; ");
			sb.append("userId: ").append(this.userId).append("; ");
			sb.append("name: ").append(this.name).append("; ");
			sb.append("roleIds: ").append(this.roleIds).append("; ");
			sb.append("roleNames: ").append(this.roleNames).append("; ");
			sb.append("Enabled: ").append(this.isEnabled()).append("; ");
			sb.append("AccountNonExpired: ").append(this.isAccountNonExpired()).append("; ");
			sb.append("credentialsNonExpired: ").append(this.isCredentialsNonExpired())
					.append("; ");
			sb.append("AccountNonLocked: ").append(this.isAccountNonLocked()).append("; ");

			if (!getAuthorities().isEmpty()) {
				sb.append("Granted Authorities: ");

				boolean first = true;
				for (GrantedAuthority auth : getAuthorities()) {
					if (!first) {
						sb.append(",");
					}
					first = false;

					sb.append(auth);
				}
			}
			else {
				sb.append("Not granted any authorities");
			}

			return sb.toString();
		}

		@Override
		public String getUsername() {
			return username;
		}
		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}
}
