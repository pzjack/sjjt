/**
 * 
 */
package org.sj.oaprj.project.repository;

import org.sj.oaprj.project.domain.ProjectUpdateDomain;
import org.sj.oaprj.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author zhen.pan
 *
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	@Query("select new org.sj.oaprj.project.domain.ProjectUpdateDomain(p.id, p.name, p.projectno, p.remark, p.department.deptName, p.projectgroup.name, p.state, p.createtime) from org.sj.oaprj.project.entity.Project p where p.deleteFlag=:deleteFlag")
	Page<ProjectUpdateDomain> findAll(Pageable page, @Param(value="deleteFlag") Integer deleteFlag);
	
	@Query("select new org.sj.oaprj.project.domain.ProjectUpdateDomain(p.id, p.name, p.projectno, p.remark, p.department.deptName, p.projectgroup.name, p.state, p.createtime) from org.sj.oaprj.project.entity.Project p where p.deleteFlag=:deleteFlag and p.name like :name")
	Page<ProjectUpdateDomain> findAll(Pageable page, @Param(value="name") String name, @Param(value="deleteFlag") Integer deleteFlag);

}