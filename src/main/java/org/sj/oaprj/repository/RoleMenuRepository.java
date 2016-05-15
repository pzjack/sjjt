package org.sj.oaprj.repository;

import java.util.List;

import org.sj.oaprj.entity.RoleMenu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleMenuRepository extends PagingAndSortingRepository<RoleMenu, Long> {
	public List<RoleMenu> findByRoleId(Long roleId);
	
	@Modifying
	public Integer deleteByRoleId(Long RoleId);
}
