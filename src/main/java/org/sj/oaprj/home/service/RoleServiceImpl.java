package org.sj.oaprj.home.service;

import java.util.HashMap;
import java.util.Map;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.entity.Role;
import org.sj.oaprj.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl {
	@Autowired
	private RoleRepository roleRepository;

	public Integer save(Role entity) {
		Role role = roleRepository.save(entity);
		return role == null ? 0 : 1;
	}

	public Role findOne(Long id) {
		return roleRepository.findOne(id);
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			Role entity = roleRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			roleRepository.save(entity);
		}
	}

	public Map<String, Object> findByFields(String roleName, Pageable pageable) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<Role> page = null;
		if(Utils.isEmpty(roleName)) {
			page = roleRepository.findByDeleteFlag(pageable, 0);
		} else {
			page = roleRepository.findByNameContainingAndDeleteFlag(pageable, roleName, 0);
		}
		result.put("total", page.getTotalElements());
		result.put("content", page.getContent());
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}
}
