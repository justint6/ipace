package com.tayhantechnologies.ipace.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Entity
@Table(name = "DEVICE_PORTS", schema = "PUBLIC", catalog = "IPACE")
public class DistinctPortsEntity implements Serializable {
    private String deviceId;
    private String portDetails;


    @Id
    @Basic
    @Column(name = "DEVICE_ID", nullable = true, precision = 0)
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    @Id
    @Basic
    @Column(name = "PORT_DETAILS", nullable = true, length = 32)
    public String getPortDetails() {
        return portDetails;
    }

    public void setPortDetails(String portDetails) {
        this.portDetails = portDetails;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistinctPortsEntity that = (DistinctPortsEntity) o;
        return Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(portDetails, that.portDetails);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deviceId, portDetails);
    }
}
