package org.sj.oaprj.project.repository;

import org.sj.oaprj.project.entity.ProjectItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectItemRepository extends PagingAndSortingRepository<ProjectItem, Long> {

}
