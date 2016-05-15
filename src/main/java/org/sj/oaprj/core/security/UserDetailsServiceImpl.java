/**
 * 
 */
package org.sj.oaprj.core.security;

import java.util.List;

import org.sj.oaprj.entity.Account;
import org.sj.oaprj.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
		return new User(username, account.getPassword(), userId, account.getName(), account.getRoles());
	}
}
