package com.lezhin.api.data.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "RESULT")
public class XmlPropertyResult {

    @JacksonXmlProperty(localName = "code")
    private String code;

    @JacksonXmlProperty(localName = "developerMessage")
    private String developerMessage;

    @JacksonXmlProperty(localName = "link")
    private String link;

    @JacksonXmlProperty(localName = "message")
    private String message;

    @JacksonXmlProperty(localName = "status")
    private Integer status;

    @JacksonXmlProperty(localName = "total")
    private Integer total;
}
