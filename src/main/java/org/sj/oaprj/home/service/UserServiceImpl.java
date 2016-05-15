package org.sj.oaprj.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.UserType;
import org.sj.oaprj.core.Utils;
import org.sj.oaprj.domain.UserListDomain;
import org.sj.oaprj.domain.UserUpdateDomain;
import org.sj.oaprj.entity.Account;
import org.sj.oaprj.entity.Department;
import org.sj.oaprj.entity.Employee;
import org.sj.oaprj.entity.Role;
import org.sj.oaprj.repository.AccountRepository;
import org.sj.oaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserServiceImpl {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	public Employee save(Employee entity) {
		return userRepository.save(entity);
	}

	public String save(UserUpdateDomain domain) {
		Employee emp = null;
		if (null == domain.getId()) {
			Long accSize = accountRepository.countByAccount(domain.getAccount());
			if (accSize > 0) {
				return null;
			}
			try {
				Employee user = new Employee();
				user.setEmail(domain.getEmail());
				user.setPhone(domain.getPhone());
				user.setName(domain.getName());
				user.setPost(domain.getPost());
				user.setRemark(domain.getRemark());
				user.setEmployeeNo(domain.getEmployeeNo());
				Long depId = domain.getDepartId();
				if (null != depId) {
					Department dep = departmentServiceImpl.findOne(depId);
					user.setDepart(dep);
				}
				emp = save(user);

				user.setUserType(0);
				Account account = new Account();
				account.setUser(user);
				account.setName(user.getName());
				account.setAccount(domain.getAccount());
				account.setPassword(domain.getPwd());

				String rolestr = domain.getRolestr();
				if (null != rolestr && !rolestr.isEmpty()) {
					String[] rls = rolestr.split(",");
					List<Long> roleIds = new ArrayList<Long>();
					for (String r : rls) {
						roleIds.add(new Long(r));
					}
					List<Role> roles = roleServiceImpl.findRoleByIds(roleIds);
					account.setRoles(roles);
				}
				accountRepository.save(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Employee user = userRepository.findOne(domain.getId());
			if(null != user) {
				if(null == user.getDepart()) {
					if(null != domain.getDepartId()) {
						Department dep = departmentServiceImpl.findOne(domain.getDepartId());
						user.setDepart(dep);
					}
				}
				user.setEmail(domain.getEmail());
				user.setName(domain.getName());
				user.setPost(domain.getPost());
				user.setRemark(domain.getRemark());
				user.setEmployeeNo(domain.getEmployeeNo());
				emp = save(user);
			}
		}
		return Utils.isNull(emp) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
	}

	@Transactional(readOnly = true)
	public Employee findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public UserUpdateDomain findUpdate(Long id) {
		Employee e = userRepository.findOne(id);
		UserUpdateDomain d = new UserUpdateDomain();
		if (null != e) {
			d.setId(e.getId());
			d.setName(e.getName());
			d.setEmployeeNo(e.getEmployeeNo());
			d.setPhone(e.getPhone());
			d.setEmail(e.getEmail());
			d.setPost(e.getPost());
			d.setRemark(e.getRemark());
			d.setRolestr("1,2");
		 
			if (null != e.getDepart()) {
				d.setDepartId(e.getDepart().getId());
			}
		}
		return d;
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			Employee entity = userRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			userRepository.save(entity);
		}
		accountRepository.deleteByUser_IdIn(idArray);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> findByFields(final String name, final String employeeNo, final String phone,
			Pageable pageable) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<Employee> page = userRepository.findAll(new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!Utils.isEmpty(name)) {
					list.add(builder.like(root.get("name"), "%" + name + "%"));
				}
				if (!Utils.isEmpty(employeeNo)) {
					list.add(builder.like(root.get("employeeNo"), "%" + employeeNo + "%"));
				}
				if (!Utils.isEmpty(phone)) {
					list.add(builder.like(root.get("phone"), "%" + phone + "%"));
				}
				list.add(builder.equal(root.get("userType"), UserType.EMPLOYEE.getIntState()));
				list.add(builder.equal(root.get("deleteFlag"), new Integer(Constants.DELETE_FLAG_0)));
				Predicate[] p = new Predicate[list.size()];
				return builder.and(list.toArray(p));
			}
		}, pageable);
		List<Employee> list = page.getContent();
		List<UserListDomain> outList = new ArrayList<UserListDomain>();
		for (Employee e : list) {
			UserListDomain d = new UserListDomain();
			d.setId(e.getId());
			d.setName(e.getName());
			d.setEmployeeNo(e.getEmployeeNo());
			d.setPhone(e.getPhone());
			d.setEmail(e.getEmail());
			d.setPost(e.getPost());
			d.setRemark(e.getRemark());
			if (null != e.getDepart()) {
				d.setDepartId(e.getDepart().getId());
				d.setDepartName(e.getDepart().getDeptName());
			}
			outList.add(d);
		}
		result.put("total", page.getTotalElements());
		result.put("content", outList);
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}
}
