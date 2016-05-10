package org.sj.oaprj.repository;

import org.sj.oaprj.entity.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {

	@SuppressWarnings("unchecked")
	public Supplier save(Supplier entity);
	
	public Supplier findOne(Long id);

}
