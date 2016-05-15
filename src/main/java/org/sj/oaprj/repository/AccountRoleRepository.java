package org.sj.oaprj.repository;

import org.sj.oaprj.entity.AccountRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface AccountRoleRepository extends CrudRepository<AccountRole, Long> {
	@Modifying
	Integer deleteByAccountId(Long accountId);
}
