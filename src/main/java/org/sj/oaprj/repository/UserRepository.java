package org.sj.oaprj.repository;

import org.sj.oaprj.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<Employee, Long>,JpaSpecificationExecutor<Employee> {
	Page<Employee> findAll(Specification<Employee> spec, Pageable page);
}
