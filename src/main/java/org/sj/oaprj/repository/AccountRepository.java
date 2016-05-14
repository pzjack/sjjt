package org.sj.oaprj.repository;

import java.util.List;

import org.sj.oaprj.entity.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	List<Account> findByAccount(String account);
	Long countByAccount(String account);
}
