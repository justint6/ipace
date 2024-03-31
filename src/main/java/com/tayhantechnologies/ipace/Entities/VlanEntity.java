package com.tayhantechnologies.ipace.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "VLAN_DETAILS", schema = "PUBLIC", catalog = "IPACE")
public class VlanEntity {
    private Integer id;
    private String vlanId;
    private String ipRange;

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
    @Column(name = "VLAN_ID", nullable = true, precision = 0)
    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

    @Basic
    @Column(name = "IP_RANGE", nullable = true, precision = 0)
    public String getIpRange() {
        return ipRange;
    }

    public void setIpRange(String ipRange) {
        this.ipRange = ipRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VlanEntity that = (VlanEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(vlanId, that.vlanId) &&
                Objects.equals(ipRange, that.ipRange);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, vlanId, ipRange);
    }
}
