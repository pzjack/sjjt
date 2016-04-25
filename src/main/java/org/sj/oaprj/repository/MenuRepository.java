package org.sj.oaprj.repository;

import java.util.List;

import org.sj.oaprj.entity.Menu;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MenuRepository extends PagingAndSortingRepository<Menu, Long> {
	List<Menu> findAll();
}
