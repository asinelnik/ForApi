package models.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope")
public class EnvelopeResponse {
    private HeaderXml headerXml;
    private ResponseBodyXml responseBodyXml;

    @XmlElement(name = "Header", namespace = "soap")
    public HeaderXml getHeaderXml() {
        return headerXml;
    }
    @XmlElement(name = "Body", namespace = "soap")
    public ResponseBodyXml getResponseBodyXml() {
        return responseBodyXml;
    }

    public void setHeaderXml(HeaderXml headerXml) {
        this.headerXml = headerXml;
    }

    public void setResponseBodyXml(ResponseBodyXml responseBodyXml) {
        this.responseBodyXml = responseBodyXml;
    }

    @Override
    public String toString() {
        return "EnvelopeResponse{" +
                "headerXml=" + headerXml +
                ", responseBodyXml=" + responseBodyXml +
                '}';
    }
}
