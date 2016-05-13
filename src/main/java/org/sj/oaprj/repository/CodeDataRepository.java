package org.sj.oaprj.repository;

import java.util.List;

import org.sj.oaprj.entity.CodeData;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CodeDataRepository extends PagingAndSortingRepository<CodeData, Long> {

	@SuppressWarnings("unchecked")
	public CodeData save(CodeData entity);

	public List<CodeData> findByCodeTypeAndDeleteFlag(Integer codeType, Integer deleteFlag);

	public List<CodeData> findByCodeTypeAndDataTypeAndDeleteFlag(Integer codeType, Integer dataType,
			Integer deleteFlag);
}
