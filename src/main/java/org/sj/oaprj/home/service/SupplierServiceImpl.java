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
import org.sj.oaprj.domain.RespSupplier;
import org.sj.oaprj.entity.Supplier;
import org.sj.oaprj.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class SupplierServiceImpl {
	@Autowired
	private SupplierRepository supplierRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public String save(Supplier entity) {
		Supplier supplier = supplierRepository.save(entity);
		return Utils.isNull(supplier) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
	}

	public Supplier findOne(Long id) {
		return supplierRepository.findOne(id);
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			Supplier entity = supplierRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			supplierRepository.save(entity);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findByFields(Supplier supplier, Pageable pageable) {
		long total = findCount(supplier);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageable, total);
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" a.ID, ");
		sb.append(" a.SUPPLIER_NAME, ");
		sb.append(" a.SUPPLIER_TYPE, ");
		sb.append(" a.SUPPLIER_DESC, ");
		sb.append(" b.DATA_NAME ");
		sb.append(" from T_SUPPLIER as a ");
		sb.append(" left join T_CODE_DATA as b ");
		sb.append(" on a.SUPPLIER_TYPE=b.DATA_TYPE and b.CODE_TYPE=1 ");
		sb.append(" where a.DELETE_FLAG=0 ");
		if (!Utils.isEmpty(supplier.getSupplierName())) {
			sb.append(" and SUPPLIER_NAME like :supplierName ");
		}
		if (!Utils.isEmpty(supplier.getSupplierType())) {
			sb.append(" and SUPPLIER_TYPE = :supplierType ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(supplier.getSupplierName())) {
			query.setParameter("supplierName", "%" + supplier.getSupplierName() + "%");
		}
		if (!Utils.isEmpty(supplier.getSupplierType())) {
			query.setParameter("supplierType", supplier.getSupplierType());
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageable.getPageSize());
		List<Object[]> list = query.getResultList();
		List<RespSupplier> content = new ArrayList<RespSupplier>();
		for (Object[] array : list) {
			RespSupplier item = new RespSupplier();
			item.setId(Utils.toLong(array[0]));
			item.setSupplierName(Utils.toString(array[1]));
			item.setSupplierType(Utils.toInteger(array[2]));
			item.setSupplierDesc(Utils.toString(array[3]));
			item.setSupplierTypeName(Utils.toString(array[4]));
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
	private long findCount(Supplier supplier) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" count(id) ");
		sb.append(" from T_SUPPLIER ");
		sb.append(" where DELETE_FLAG=0 ");
		if (!Utils.isEmpty(supplier.getSupplierName())) {
			sb.append(" and SUPPLIER_NAME like :supplierName ");
		}
		if (!Utils.isEmpty(supplier.getSupplierType())) {
			sb.append(" and SUPPLIER_TYPE like :supplierType ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(supplier.getSupplierName())) {
			query.setParameter("supplierName", "%" + supplier.getSupplierName() + "%");
		}
		if (!Utils.isEmpty(supplier.getSupplierType())) {
			query.setParameter("supplierType", supplier.getSupplierType());
		}
		BigInteger obj = (BigInteger) query.getSingleResult();
		return obj.longValue();
	}
}
