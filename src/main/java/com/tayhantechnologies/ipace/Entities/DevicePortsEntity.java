package com.tayhantechnologies.ipace.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "DEVICE_PORTS", schema = "PUBLIC", catalog = "IPACE")
public class DevicePortsEntity {
    private Integer id;
    private String deviceId;
    private String portDetails;
    private String dateScanned;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = true, precision = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DEVICE_ID", nullable = true, precision = 0)
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "PORT_DETAILS", nullable = true, length = 32)
    public String getPortDetails() {
        return portDetails;
    }

    public void setPortDetails(String portDetails) {
        this.portDetails = portDetails;
    }

    @Basic
    @Column(name = "DATE_SCANNED", nullable = true, length = 32)
    public String getDateScanned() {
        return dateScanned;
    }

    public void setDateScanned(String dateScanned) {
        this.dateScanned = dateScanned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePortsEntity that = (DevicePortsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(portDetails, that.portDetails) &&
                Objects.equals(dateScanned, that.dateScanned);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, deviceId, portDetails, dateScanned);
    }
}
