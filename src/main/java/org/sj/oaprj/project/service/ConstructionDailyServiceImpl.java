package org.sj.oaprj.project.service;

import java.util.HashMap;
import java.util.Map;

import org.sj.oaprj.core.Constants;
import org.sj.oaprj.project.entity.ConstructionUnitDaily;
import org.sj.oaprj.project.repository.ConstructionDailyRepository;
import org.sj.oaprj.project.repository.ConstructionMemberDailyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ConstructionDailyServiceImpl {
	@Autowired
	private ConstructionDailyRepository constructionDailyRepository;
	@Autowired
	private ConstructionMemberDailyRepository constructionMemberDailyRepository;

	public Integer save(ConstructionUnitDaily entity) {
		ConstructionUnitDaily constructionUnitDaily = constructionDailyRepository.save(entity);
		return constructionUnitDaily == null ? 0 : 1;
	}

	public ConstructionUnitDaily findOne(Long id) {
		return constructionDailyRepository.findOne(id);
	}

	public void delete(Long[] idArray) {
		for (Long id : idArray) {
			ConstructionUnitDaily entity = constructionDailyRepository.findOne(id);
			entity.setDeleteFlag(Constants.DELETE_FLAG_1);
			constructionDailyRepository.save(entity);
		}
	}

	public Map<String, Object> findByFields(String roleName, Pageable pageable) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<ConstructionUnitDaily> page = null;
		
		page = constructionDailyRepository.findAll(pageable);
		result.put("total", page.getTotalElements());
		result.put("content", page.getContent());
		result.put("pageNumber", pageable.getPageNumber());
		return result;
	}
}
