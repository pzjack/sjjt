package org.sj.oaprj.repository;

import java.util.List;

import org.sj.oaprj.entity.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

	List<Department> findByDeleteFlag(Integer deleteFlag);
}
