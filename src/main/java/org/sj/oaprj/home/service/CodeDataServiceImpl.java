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
import org.sj.oaprj.entity.CodeData;
import org.sj.oaprj.repository.CodeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CodeDataServiceImpl {
	@Autowired
	private CodeDataRepository codeDataRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public int save(CodeData entity) {
		CodeData codeData = codeDataRepository.save(entity);
		return codeData == null ? 0 : 1;
	}

	public CodeData findOne(Long id) {
		return codeDataRepository.findOne(id);
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			CodeData entity = codeDataRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			codeDataRepository.save(entity);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findByFields(CodeData codeData, Pageable pageable) {
		long total = findCount(codeData);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageable, total);
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" a.ID, ");
		sb.append(" a.CODE_TYPE, ");
		sb.append(" a.DATA_TYPE, ");
		sb.append(" a.DATA_NAME ");
		sb.append(" from T_CODE_DATA as a ");
		sb.append(" where a.DELETE_FLAG=0 ");
		if (!Utils.isEmpty(codeData.getDataType())) {
			sb.append(" and DATA_TYPE = :dataType ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(codeData.getDataType())) {
			query.setParameter("dataType", codeData.getDataType());
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageable.getPageSize());
		List<Object[]> list = query.getResultList();
		List<CodeData> content = new ArrayList<CodeData>();
		for (Object[] array : list) {
			CodeData item = new CodeData();
			item.setId(Utils.toLong(array[0]));
			item.setCodeType(Utils.toInteger(array[1]));
			item.setDataType(Utils.toInteger(array[2]));
			item.setDataName(Utils.toString(array[3]));
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
	 * @param codeData
	 * @return
	 */
	private long findCount(CodeData codeData) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" count(id) ");
		sb.append(" from T_CODE_DATA ");
		sb.append(" where DELETE_FLAG=0 ");
		if (!Utils.isEmpty(codeData.getDataType())) {
			sb.append(" and DATA_TYPE = :dataType ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(codeData.getDataType())) {
			query.setParameter("dataType", codeData.getDataType());
		}
		BigInteger obj = (BigInteger) query.getSingleResult();
		return obj.longValue();
	}

	public List<CodeData> findByCodeType(Integer codeType) {
		return codeDataRepository.findByCodeType(codeType);
	}
}
