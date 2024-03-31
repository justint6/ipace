package com.tayhantechnologies.ipace.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "OS_DETAILS", schema = "PUBLIC", catalog = "IPACE")
public class OsEntity {
    private Integer id;
    private String osDetails;
    private String osType;

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
    @Column(name = "OS_DETAILS", nullable = true, precision = 0)
    public String getOsDetails() {
        return osDetails;
    }

    public void setOsDetails(String osDetails) {
        this.osDetails = osDetails;
    }



    @Basic
    @Column(name = "OS_TYPE", nullable = true, precision = 0)
    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OsEntity that = (OsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(osDetails, that.osDetails) &&
                Objects.equals(osType, that.osType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, osDetails, osType);
    }
}
