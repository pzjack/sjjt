package org.sj.oaprj.home.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.core.Utils;
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

	public String save(Department entity) {
		Department department = departmentRepository.save(entity);
		return Utils.isNull(department) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
	}

	public Department findOne(Long id) {
		return departmentRepository.findOne(id);
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			Department entity = departmentRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			departmentRepository.save(entity);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findByFields(Department department, Pageable pageable) {
		long total = findCount(department);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageable, total);
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" a.ID, ");
		sb.append(" a.DEPT_NAME, ");
		sb.append(" a.DEPT_CODE, ");
		sb.append(" a.DEPT_DESC ");
		sb.append(" from T_DEPARTMENT as a ");
		sb.append(" where DELETE_FLAG=0 ");
		if (!Utils.isEmpty(department.getDeptName())) {
			sb.append(" and a.DEPT_NAME like :deptName ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(department.getDeptName())) {
			query.setParameter("deptName", "%" + department.getDeptName() + "%");
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageable.getPageSize());
		List<Object[]> list = query.getResultList();
		List<Department> content = new ArrayList<Department>();
		for (Object[] array : list) {
			Department item = new Department();
			item.setId(Utils.toLong(array[0]));
			item.setDeptName(Utils.toString(array[1]));
			item.setDeptCode(Utils.toString(array[2]));
			item.setDeptDesc(Utils.toString(array[3]));
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
	private long findCount(Department department) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" count(a.id) ");
		sb.append(" from T_DEPARTMENT as a ");
		sb.append(" where DELETE_FLAG=0 ");
		if (!Utils.isEmpty(department.getDeptName())) {
			sb.append(" and a.DEPT_NAME like :deptName ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(department.getDeptName())) {
			query.setParameter("deptName", "%" + department.getDeptName() + "%");
		}
		BigInteger obj = (BigInteger) query.getSingleResult();
		return obj.longValue();
	}
}
