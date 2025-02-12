package com.lezhin.api.data.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "realtimeStationArrival")
public class XmlResult {
    @JacksonXmlProperty(localName = "RESULT")
    private String RESULT;
}