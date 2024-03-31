package com.tayhantechnologies.ipace.Entities;

import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
@Component
@Entity
@Table(name = "IPACE_DEVICES", schema = "PUBLIC", catalog = "IPACE")
public class IpaceDevicesEntity {
    private Integer id;
    private Integer online;
    private String deviceName;
    private String ipAddress;
    private String dateScanned;
    private String description;

    @Basic
    @Column(name = "ID", nullable = true, precision = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DEVICE_NAME", nullable = true, length = 64)
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Id
    @Column(name = "IP_ADDRESS", nullable = true, length = 64)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Basic
    @Column(name = "DATE_SCANNED", nullable = true, length = 64)
    public String getDateScanned() {
        return dateScanned;
    }

    public void setDateScanned(String dateScanned) {
        this.dateScanned = dateScanned;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 64)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ONLINE", nullable = true, precision = 0)
    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpaceDevicesEntity that = (IpaceDevicesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deviceName, that.deviceName) &&
                Objects.equals(ipAddress, that.ipAddress) &&
                Objects.equals(dateScanned, that.dateScanned) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, deviceName, ipAddress, dateScanned, description);
    }
}
