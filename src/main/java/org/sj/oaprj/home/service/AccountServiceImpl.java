package org.sj.oaprj.home.service;

import java.util.List;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.domain.RespAccount;
import org.sj.oaprj.entity.Account;
import org.sj.oaprj.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl {
	@Autowired
	private AccountRepository accountRepository;

	public String updatePwd(RespAccount respAccount) {
		if (!respAccount.getPassword().equals(respAccount.getConfirmPassword())) {
			return "";
		}
		List<Account> accountList = accountRepository.findByAccount(respAccount.getAccount());
		if (!Utils.isNull(accountList) && accountList.size() > 0) {
			Account account = accountList.get(0);
			account.setPassword(respAccount.getPassword());
			Account acc = accountRepository.save(account);
			return Utils.isNull(acc.getId()) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
		} else {
			return Constants.ACCOUNT_NOT_EXISTS;
		}
	}
}
