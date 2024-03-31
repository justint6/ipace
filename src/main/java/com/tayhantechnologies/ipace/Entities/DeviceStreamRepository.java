package com.tayhantechnologies.ipace.Entities;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public interface DeviceStreamRepository extends PagingAndSortingRepository<DeviceStreamEntity, Integer>, JpaSpecificationExecutor<DeviceStreamEntity> {
    List<DeviceStreamEntity> findByDeviceId(@Param("deviceID") String deviceID); //http://127.0.0.1:8080/deviceStreamEntities/search/findByDeviceId?deviceID=192.168.0.1

    List<DeviceStreamEntity> findByPortDetails(@Param("portDetails") String portDetails);

    @Query(value = "select * from DEVICE_STREAM where ID > ?", nativeQuery = true)
    List<DeviceStreamEntity> findByIdGreaterThan(@Param("id") Integer id);

    @Query(value = "select count(*) from DEVICE_STREAM product", nativeQuery = true)
    long findById();

    @Query(value = "select * from DEVICE_STREAM where DISPLAY_FLAG = 1 and ID > ?", nativeQuery = true)
    List<DeviceStreamEntity> findByFlag(@Param("id") Integer id);


   // DeviceStreamEntity findTopById();

    @Transactional
    @Modifying
    @Query(
            value = "truncate table DEVICE_STREAM",
            nativeQuery = true
    )
    void truncateDeviceStream();
}

