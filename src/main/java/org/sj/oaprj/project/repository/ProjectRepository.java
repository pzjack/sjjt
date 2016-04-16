/**
 * 
 */
package org.sj.oaprj.project.repository;

import org.sj.oaprj.project.entity.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zhen.pan
 *
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

}