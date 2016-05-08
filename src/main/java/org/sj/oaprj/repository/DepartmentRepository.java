package org.sj.oaprj.repository;

import org.sj.oaprj.entity.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

	@SuppressWarnings("unchecked")
	public Department save(Department entity);
	
	public Department findOne(Long id);

}
