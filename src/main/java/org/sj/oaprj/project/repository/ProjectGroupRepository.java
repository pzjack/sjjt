/**
 * 
 */
package org.sj.oaprj.project.repository;

import org.sj.oaprj.project.domain.ProjectGroupUpdateDomain;
import org.sj.oaprj.project.entity.ProjectGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author zhen.pan
 *
 */
public interface ProjectGroupRepository extends PagingAndSortingRepository<ProjectGroup, Long> {	
	@Query("select new org.sj.oaprj.project.domain.ProjectGroupUpdateDomain(p.id, p.name, p.remark, p.department.deptName) from org.sj.oaprj.project.entity.ProjectGroup p where p.deleteFlag=:deleteFlag")
	Page<ProjectGroupUpdateDomain> findAll(Pageable page, @Param(value="deleteFlag") Integer deleteFlag);
	
	@Query("select new org.sj.oaprj.project.domain.ProjectGroupUpdateDomain(p.id, p.name, p.remark, p.department.deptName) from org.sj.oaprj.project.entity.ProjectGroup p where p.deleteFlag=:deleteFlag and p.name like :name")
	Page<ProjectGroupUpdateDomain> findAll(Pageable page, @Param(value="name") String name, @Param(value="deleteFlag") Integer deleteFlag);
}