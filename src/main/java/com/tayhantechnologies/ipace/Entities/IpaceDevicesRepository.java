package com.tayhantechnologies.ipace.Entities;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IpaceDevicesRepository extends PagingAndSortingRepository<IpaceDevicesEntity, String>, JpaSpecificationExecutor<IpaceDevicesEntity> {

    @Query(value = "select count(*) from IPACE_DEVICES", nativeQuery = true)
    long findByCount();

    @Query(value = "select count(*) from IPACE_DEVICES where IPACE_DEVICES.ONLINE = 1", nativeQuery = true)
    long findOnlineByCount();

    @Query(value = "select count(*) from IPACE_DEVICES where IPACE_DEVICES.ONLINE = 0", nativeQuery = true)
    long findOfflineByCount();
}
