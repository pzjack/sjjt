/**
 * 
 */
package org.sj.oaprj.project.repository;

import org.sj.oaprj.project.domain.ProjectGroupUserUpdateDomain;
import org.sj.oaprj.project.entity.ProjectGroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author zhen.pan
 *
 */
public interface ProjectGroupUserRepository extends PagingAndSortingRepository<ProjectGroupUser, Long> {	
	@Query("select new org.sj.oaprj.project.domain.ProjectGroupUserUpdateDomain(p.id, p.name, p.role, p.post, p.projectgroup.name) from org.sj.oaprj.project.entity.ProjectGroupUser p where p.deleteFlag=:deleteFlag")
	Page<ProjectGroupUserUpdateDomain> findAll(Pageable page, @Param(value="deleteFlag") Integer deleteFlag);
	
	@Query("select new org.sj.oaprj.project.domain.ProjectGroupUserUpdateDomain(p.id, p.name, p.role, p.post, p.projectgroup.name) from org.sj.oaprj.project.entity.ProjectGroupUser p where p.deleteFlag=:deleteFlag and p.name like :name")
	Page<ProjectGroupUserUpdateDomain> findAll(Pageable page, @Param(value="name") String name, @Param(value="deleteFlag") Integer deleteFlag);
}