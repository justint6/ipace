package com.tayhantechnologies.ipace.Entities;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DevicePortRepository extends PagingAndSortingRepository<DevicePortsEntity, Integer>, JpaSpecificationExecutor<DevicePortsEntity> {
    List<DevicePortsEntity> findDistinctByDeviceId(@Param("deviceID") String deviceID); //http://127.0.0.1:8080/devicePortsEntities/search/findByDeviceId?deviceID=192.168.0.1
    List<DevicePortsEntity> findByDeviceId(@Param("deviceID") String deviceID);
    List<DevicePortsEntity> findByPortDetails(@Param("portDetails") String portDetails);

    @Query(value = "select distinct port_details, device_id from device_ports where device_id = ?", nativeQuery = true)
    List<DistinctPortsEntity> findByDeviceIdOnly(@Param("deviceID") String deviceID);

    @Query(value = "select count(*) from device_ports where device_id = ? and port_details = ?", nativeQuery = true)
    String findByDeviceIdPort(@Param("deviceID") String deviceID, @Param("portDetails") String portDetails);


}
