package com.tayhantechnologies.ipace.Entities;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MqttDataRepository extends PagingAndSortingRepository <MqttDataEntity, Integer>, JpaSpecificationExecutor<MqttDataEntity> {
}
