package com.tayhantechnologies.ipace.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmbeddedResponse {
    @JsonProperty("_embedded")
    private EmbeddedDevices embeddedDevices;

    public EmbeddedDevices getEmbeddedDevices() {
        return embeddedDevices;
    }

    public void setEmbeddedDevices(EmbeddedDevices embeddedDevices) {
        this.embeddedDevices = embeddedDevices;
    }

}



