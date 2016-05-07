package org.sj.oaprj.repository;

import org.sj.oaprj.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
	
	public Page<Department> findAll(Pageable page);
	
	public Department save(Department entity);
	
	public Department findOne(Long id);
	
	public void delete(Long id);
	
}
