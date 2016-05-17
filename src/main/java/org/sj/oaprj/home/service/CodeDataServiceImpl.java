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
import org.sj.oaprj.domain.RespCodeData;
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

	public String save(CodeData entity) {
		List<CodeData> cdList = codeDataRepository.findByCodeTypeAndDataTypeAndDeleteFlag(entity.getCodeType(),
				entity.getDataType(), Constants.DELETE_FLAG_0);
		// 新增
		if (Utils.isEmpty(entity.getId())) {
			if (Utils.isNull(cdList) || cdList.size() == 0) {
				CodeData codeData = codeDataRepository.save(entity);
				return Utils.isNull(codeData) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
			} else {
				return Constants.DATA_TYPE_EXISTS;
			}
			// 更新
		} else {
			// 如果是同一对象,或者还未有当前要更改的codeData的值
			if (Utils.isNull(cdList) || cdList.size() == 0 || cdList.get(0).getId().equals(entity.getId())) {
				CodeData codeData = codeDataRepository.save(entity);
				return Utils.isNull(codeData) ? Constants.SAVE_FAIL : Constants.SAVE_SUCCESS;
			} else {
				return Constants.DATA_TYPE_EXISTS;
			}
		}
	}

	public RespCodeData findOne(Long id) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" a.ID, ");
		sb.append(" a.CODE_TYPE, ");
		sb.append(" a.DATA_TYPE, ");
		sb.append(" a.DATA_NAME, ");
		sb.append(" b.TYPE_NAME ");
		sb.append(" from T_CODE_DATA as a ");
		sb.append(" left join T_CODE_TYPE b ");
		sb.append(" on a.CODE_TYPE=b.CODE_TYPE ");
		sb.append(" where a.ID=:id ");

		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter("id", id);
		Object[] array = (Object[]) query.getSingleResult();
		RespCodeData item = new RespCodeData();
		item.setId(Utils.toLong(array[0]));
		item.setCodeType(Utils.toInteger(array[1]));
		item.setDataType(Utils.toInteger(array[2]));
		item.setDataName(Utils.toString(array[3]));
		item.setTypeName(Utils.toString(array[4]));
		return item;
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
		sb.append(" a.DATA_NAME, ");
		sb.append(" b.TYPE_NAME ");
		sb.append(" from T_CODE_DATA as a ");
		sb.append(" left join T_CODE_TYPE b ");
		sb.append(" on a.CODE_TYPE=b.CODE_TYPE ");
		sb.append(" where a.DELETE_FLAG=0 ");
		if (!Utils.isEmpty(codeData.getCodeType())) {
			sb.append(" and a.CODE_TYPE = :codeType ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(codeData.getCodeType())) {
			query.setParameter("codeType", codeData.getCodeType());
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageable.getPageSize());
		List<Object[]> list = query.getResultList();
		List<RespCodeData> content = new ArrayList<RespCodeData>();
		for (Object[] array : list) {
			RespCodeData item = new RespCodeData();
			item.setId(Utils.toLong(array[0]));
			item.setCodeType(Utils.toInteger(array[1]));
			item.setDataType(Utils.toInteger(array[2]));
			item.setDataName(Utils.toString(array[3]));
			item.setTypeName(Utils.toString(array[4]));
			content.add(item);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", content);
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
		if (!Utils.isEmpty(codeData.getCodeType())) {
			sb.append(" and CODE_TYPE = :codeType ");
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		if (!Utils.isEmpty(codeData.getCodeType())) {
			query.setParameter("codeType", codeData.getCodeType());
		}
		BigInteger obj = (BigInteger) query.getSingleResult();
		return obj.longValue();
	}

	public List<CodeData> findByCodeType(Integer codeType) {
		return codeDataRepository.findByCodeTypeAndDeleteFlag(codeType, Constants.DELETE_FLAG_0);
	}
}
