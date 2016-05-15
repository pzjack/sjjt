package org.sj.oaprj.repository;

import java.util.List;

import org.sj.oaprj.domain.BaseComboBoxData;
import org.sj.oaprj.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

	List<Department> findByDeleteFlag(Integer deleteFlag);
	
	@Query("select new org.sj.oaprj.domain.BaseComboBoxData(d.id, d.deptName)  from org.sj.oaprj.entity.Department d where d.deleteFlag=:deleteFlag")
	Page<BaseComboBoxData> findComboBoxData(@Param(value="deleteFlag") Integer deleteFlag, Pageable page);
	
	@Query("select new org.sj.oaprj.domain.BaseComboBoxData(d.id, d.deptName)  from org.sj.oaprj.entity.Department d where d.deleteFlag=:deleteFlag and (d.deptName like :deptName or d.deptCode like :deptName)")
	Page<BaseComboBoxData> findComboBoxData(@Param(value="deleteFlag") Integer deleteFlag, @Param(value="deptName") String deptName, Pageable page);
}
