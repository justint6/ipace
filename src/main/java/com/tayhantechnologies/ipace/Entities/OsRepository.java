package com.tayhantechnologies.ipace.Entities;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OsRepository extends PagingAndSortingRepository<OsEntity, Integer>, JpaSpecificationExecutor<OsEntity>{
}
