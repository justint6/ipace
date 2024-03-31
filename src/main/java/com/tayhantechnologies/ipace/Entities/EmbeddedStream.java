package com.tayhantechnologies.ipace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddedStream {
    @JsonProperty("deviceStreamEntities")
    List<DeviceStreamEntity> deviceStreamEntityArrayList = new ArrayList<DeviceStreamEntity>();

    public EmbeddedStream(){

    }

    public List<DeviceStreamEntity> getDevicesStreamEntityArrayList() {
        return deviceStreamEntityArrayList;
    }

    public void setDeviceStreamEntityArrayList(List<DeviceStreamEntity> deviceStreamEntityArrayList) {
        this.deviceStreamEntityArrayList = deviceStreamEntityArrayList;
    }
}
