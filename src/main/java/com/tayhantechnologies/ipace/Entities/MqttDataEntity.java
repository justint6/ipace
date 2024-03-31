package com.tayhantechnologies.ipace.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "MQTT_DATA", schema = "PUBLIC", catalog = "IPACE")
public class MqttDataEntity {
    private Integer id;
    private String topicName;
    private String topicValue;
    private String dateScanned;

    @Id
    @Column(name = "ID", nullable = true, precision = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Basic
    @Column(name = "TOPIC_NAME", nullable = true, length = 64)
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Basic
    @Column(name = "TOPIC_VALUE", nullable = true, length = 32)
    public String getTopicValue() {
        return topicValue;
    }

    public void setTopicValue(String topicValue) {
        this.topicValue = topicValue;
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
        MqttDataEntity that = (MqttDataEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(topicName, that.topicName) &&
                Objects.equals(topicValue, that.topicValue) &&
                Objects.equals(dateScanned, that.dateScanned);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, topicName, topicValue, dateScanned);
    }
}
