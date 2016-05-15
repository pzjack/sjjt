package org.sj.oaprj.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.domain.RespTreeNode;
import org.sj.oaprj.entity.Role;
import org.sj.oaprj.entity.RoleMenu;
import org.sj.oaprj.repository.RoleMenuRepository;
import org.sj.oaprj.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RoleServiceImpl {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public String save(Role entity) {
//		Role role = roleRepository.save(entity);
//		return Utils.isNull(role) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;

		List<Role> roleList = roleRepository.findByName(entity.getName());
		Role r = new Role();
		if (!Utils.isNull(roleList) && roleList.size() > 0) {
			r = roleList.get(0);
		}
		if (Utils.isEmpty(entity.getId())) {
			// 无相应名称的角色时，新增
			if (Utils.isEmpty(r.getId())) {
				Role role = roleRepository.save(entity);
				return Utils.isNull(role.getId()) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
			}
			return Constants.ROLE_NAME_EXISTS;
		} else {
			if (Utils.isEmpty(r.getId()) || r.getId().equals(entity.getId())) {
				Role role = roleRepository.save(entity);
				return Utils.isNull(role.getId()) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
			} else {
				return Constants.ROLE_NAME_EXISTS;
			}
		}
	}

	public Role findOne(Long id) {
		return roleRepository.findOne(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public String delete(Long id) {
		// 删除角色
		roleRepository.delete(id);
		// 删除角色授权
		String sql = " delete from T_ROLE_MENU where ROLE_ID=:id";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		// 删除用户角色
		String userRoleSql = " delete from T_ACCOUNT_ROLE where ROLE_ID=:id";
		Query userRoleQuery = entityManager.createNativeQuery(userRoleSql);
		userRoleQuery.setParameter("id", id);
		userRoleQuery.executeUpdate();
		return Constants.DELETE_SUCCESS;
	}
	
	public List<Role> findAllRole() {
		return roleRepository.findByDeleteFlag(Constants.DELETE_FLAG_0);
	}
	
	public List<Role> findRoleByIds(List<Long> roleIds) {
		return roleRepository.findByIdIn(roleIds);
	}

	public Map<String, Object> findByFields(String roleName, Pageable pageable) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<Role> page = null;
		if (Utils.isEmpty(roleName)) {
			page = roleRepository.findByDeleteFlag(pageable, 0);
		} else {
			page = roleRepository.findByNameContainingAndDeleteFlag(pageable, roleName, 0);
		}
		result.put("total", page.getTotalElements());
		result.put("content", page.getContent());
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}

	public List<RespTreeNode> findAll() {
		Iterable<Role> roleList = roleRepository.findAll();
		RespTreeNode root = new RespTreeNode();
		root.setId("");
		root.setText("角色列表");
		for (Role item : roleList) {
			RespTreeNode children = new RespTreeNode();
			children.setId(Utils.toString(item.getId()));
			children.setText(item.getName());
			root.getChildren().add(children);
		}
		// 树型菜单结构列表
		List<RespTreeNode> nodeList = new ArrayList<RespTreeNode>();
		nodeList.add(root);
		return nodeList;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public String saveRoleAuth(Long roleId, Long[] menuIdArray) {
		// 删除角色授权
		String sql = " delete from T_ROLE_MENU where ROLE_ID=:id";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", roleId);
		query.executeUpdate();
		// 新增授权
		for (Long menuId : menuIdArray) {
			RoleMenu entity = new RoleMenu();
			entity.setMenuId(menuId);
			entity.setRoleId(roleId);
			roleMenuRepository.save(entity);
		}
		return Constants.SAVE_SUCCESS;
	}
}
