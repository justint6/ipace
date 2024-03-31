package com.tayhantechnologies.ipace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListIpaceDevices {
    @JsonSetter(value="ipaceDevicesEntities")
    List<IpaceDevicesEntity> listOfIpaceDevices;

    public List<IpaceDevicesEntity> getListOfIpaceDevices() {
    return listOfIpaceDevices;
}

    public void setListOfIpaceDevices(List<IpaceDevicesEntity> listOfIpaceDevices) {
        this.listOfIpaceDevices = listOfIpaceDevices;
    }
}
