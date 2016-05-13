package org.sj.oaprj.repository;

import java.util.List;

import org.sj.oaprj.entity.CodeType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CodeTypeRepository extends PagingAndSortingRepository<CodeType, Long> {

	public List<CodeType> findByCodeType(Integer codeType);

}
