package org.sj.oaprj.core.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.sj.oaprj.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class User implements UserDetails, SecurityUserDetail {
	private static final long serialVersionUID = -7468257751185874227L;
	private String password;
	private final String username;
	private final Set<GrantedAuthority> authorities;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;
	private Long userId;
	private String name;
	private String[] roleNames;
	private Long[] roleIds;
	
	public User(String username, String password, Long userId, String name, List<Role> roles) {
		this.username = username;
		this.password = password;
		this.userId = userId;
		this.name = name;
		if(null != roles) {
			roleNames = new String[roles.size()];
			roleIds = new Long[roles.size()];
			for(int i = 0; i < roleNames.length; i++) {
				Role r = roles.get(i);
				roleNames[i] = "ROLE_" + r.getName().toUpperCase();
				roleIds[i] = r.getId();
			}
		}
		this.authorities = Collections.unmodifiableSet(sortAuthorities(AuthorityUtils.createAuthorityList(roleNames)));
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
		enabled = true;
	}
	public Long getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String[] getRoleNames() {
		return roleNames;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(
				new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority,
					"GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>,
			Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before adding it to
			// the set.
			// If the authority is null, it is a custom authority and should precede
			// others.
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}
	
	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof User) {
			return username.equals(((User) rhs).username);
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
		sb.append("credentialsNonExpired: ").append(this.isCredentialsNonExpired()).append("; ");
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
}
