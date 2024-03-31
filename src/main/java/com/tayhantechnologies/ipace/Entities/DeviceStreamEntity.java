package com.tayhantechnologies.ipace.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "DEVICE_STREAM", schema = "PUBLIC", catalog = "IPACE")
public class DeviceStreamEntity {
    private Integer id;
    private String deviceId;
    private String portDetails;
    private String dateScanned;
    private Integer displayFlag;

    @Id
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
    @Column(name = "DISPLAY_FLAG", nullable = true, precision = 0)
    public Integer getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(Integer displayFlag) {
        this.displayFlag = displayFlag;
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
        DeviceStreamEntity that = (DeviceStreamEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(portDetails, that.portDetails) &&
                Objects.equals(dateScanned, that.dateScanned) &&
                Objects.equals(displayFlag, that.displayFlag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, deviceId, portDetails, dateScanned, displayFlag);
    }
}
