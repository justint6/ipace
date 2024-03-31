package com.tayhantechnologies.ipace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddedDevices {
    @JsonProperty("ipaceDevicesEntities")
    List<IpaceDevicesEntity> ipaceDevicesEntityArrayList = new ArrayList<IpaceDevicesEntity>();

    public EmbeddedDevices(){

    }

    public List<IpaceDevicesEntity> getIpaceDevicesEntityArrayList() {
        return ipaceDevicesEntityArrayList;
    }

    public void setIpaceDevicesEntityArrayList(List<IpaceDevicesEntity> ipaceDevicesEntityArrayList) {
        this.ipaceDevicesEntityArrayList = ipaceDevicesEntityArrayList;
    }
}
