/**
 * 
 */
package org.sj.oaprj.home.service;

import java.util.Collection;
import java.util.List;

import org.sj.oaprj.entity.Account;
import org.sj.oaprj.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zhen.pan
 *
 */
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
		return new CustomUserDetails(username, account.getPassword(), account.getRole(), account.getName());
	}

	private final static class CustomUserDetails implements UserDetails {
		private static final long serialVersionUID = 8683102959103260527L;
		private String username;
		private String password;
		private String role;
		@SuppressWarnings("unused")
		private Integer roleid;
		private String name;
		private CustomUserDetails(String username, String password, Integer roleId, String name) {
			this.username = username;
			this.password = password;
			this.roleid = roleId;
//			this.role = Contants.ROLES.get(this.roleid);
			this.name = name;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
//			if("admin".equals(username)) {
//				return AuthorityUtils.createAuthorityList(Contants.ROLE_USER, Contants.ROLE_ADMIN);
//			} else {
//				switch(roleid) {
//				case 0: return AuthorityUtils.createAuthorityList(Contants.ROLE_USER);
//				case 10: return AuthorityUtils.createAuthorityList(Contants.ROLE_MEMBER);
//				case 20: return AuthorityUtils.createAuthorityList(Contants.ROLE_LEADER);
//				case 30: return AuthorityUtils.createAuthorityList(Contants.ROLE_CONTROLLER);
//				default:
//					return AuthorityUtils.createAuthorityList(Contants.ROLE_USER);
//				}
//			}
			return AuthorityUtils.createAuthorityList();
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
			sb.append("role: ").append(this.role).append("; ");
			sb.append("name: ").append(this.name).append("; ");
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
