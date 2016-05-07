package org.sj.oaprj.home.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.sj.oaprj.core.Utils;
import org.sj.oaprj.domain.RespDepartment;
import org.sj.oaprj.entity.Department;
import org.sj.oaprj.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DepartmentServiceImpl {
	@Autowired
	private DepartmentRepository departmentRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public Department save(Department entity) {
		entity.setDeptName("开发二部");
		entity.setParentId(0);
		entity.setDeptType(1);
		entity.setDeleteFlag("0");
		entity.setDeptDesc("开发二部主要做vb.net开发");
		entity.setDeptCode("BU2");
		entity.setCreatetime(new Date());
		return departmentRepository.save(entity);
	}

	/**
	 * 查询部门信息
	 * 
	 * @param deptName
	 * @param pageable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findByFields(Department org, Pageable pageable) {
		long total = findCount(org);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageable, total);
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" a.ID, ");
		sb.append(" a.DEPT_NAME, ");
		sb.append(" a.DEPT_CODE, ");
		sb.append(" a.DEPT_DESC, ");
		sb.append(" b.DEPT_NAME AS PARENT_NAME ");
		sb.append(" from T_DEPARTMENT as a ");
		sb.append(" left join T_DEPARTMENT as b on a.PARENT_ID=b.id ");
		sb.append(" where 1=1 ");
		if (!Utils.isEmpty(org.getDeptName())) {
			sb.append(" and a.DEPT_NAME like :deptName ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(org.getDeptName())) {
			query.setParameter("deptName", "%" + org.getDeptName() + "%");
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageable.getPageSize());
		List<Object[]> list = query.getResultList();
		List<RespDepartment> content = new ArrayList<RespDepartment>();
		for (Object[] array : list) {
			RespDepartment item = new RespDepartment();
			item.setId(Utils.toLong(array[0]));
			item.setDeptName(Utils.toString(array[1]));
			item.setDeptCode(Utils.toString(array[2]));
			item.setParentName(Utils.toString(array[4]));
			content.add(item);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("content", content);
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}

	/**
	 * 查询数据笔数
	 * 
	 * @param deptName
	 * @return
	 */
	private long findCount(Department org) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" count(a.id) ");
		sb.append(" from T_DEPARTMENT as a ");
		sb.append(" left join T_DEPARTMENT as b on a.PARENT_ID=b.id ");
		sb.append(" where 1=1 ");
		if (!Utils.isEmpty(org.getDeptName())) {
			sb.append(" and a.DEPT_NAME like :deptName ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(org.getDeptName())) {
			query.setParameter("deptName", "%" + org.getDeptName() + "%");
		}
		BigInteger obj = (BigInteger) query.getSingleResult();
		return obj.longValue();
	}

	/**
	 * 查询部门列表
	 * 
	 * @return
	 */
	public List<Department> findOrgList(Department org) {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("deptName", "test");
		return findByField(mp);
	}

	@SuppressWarnings("unchecked")
	public List<Department> findByField(Map<String, Object> params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> query = builder.createQuery(Object.class);
		Root<Department> root = query.from(Department.class);
		Predicate[] predicates = params.entrySet().stream().filter(p -> p.getValue() != null).map(p -> builder
				.equal(root.get(p.getKey()), isBoolean(p.getValue()) ? booleanValue(p.getValue()) : p.getValue()))
				.toArray(Predicate[]::new);
		query.where(builder.and(predicates));
		Query countQuery = entityManager.createQuery(query.select(builder.count(root)));
		int count = Integer.parseInt(countQuery.getSingleResult().toString());
		Query createQuery = entityManager.createQuery(query.select(root));
		createQuery.setFirstResult(1);
		createQuery.setMaxResults(2);
		List<Department> orgList = createQuery.getResultList();
		return orgList;
	}

	private boolean isBoolean(Object obj) {
		if (obj instanceof String) {
			String str = (String) obj;
			return "true".equals(str) || "false".equals(str);
		}

		return false;
	}

	private Boolean booleanValue(Object obj) {
		return (obj instanceof String) ? Boolean.valueOf((String) obj) : null;
	}

}
