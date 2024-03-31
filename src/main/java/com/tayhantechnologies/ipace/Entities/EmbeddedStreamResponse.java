package com.tayhantechnologies.ipace.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmbeddedStreamResponse {
    @JsonProperty("_embedded")
    private EmbeddedStream embeddedStream;

    public EmbeddedStream getEmbeddedStream() {
        return embeddedStream;
    }

    public void setEmbeddedStream(EmbeddedStream embeddedStream) {
        this.embeddedStream = embeddedStream;
    }

}



