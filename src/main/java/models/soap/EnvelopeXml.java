package models.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope")
public class EnvelopeXml {
    private HeaderXml headerXml;
    private BodyXml bodyXml;

    @XmlElement(name = "Header", namespace = "soap")
    public HeaderXml getHeader() {
        return headerXml;
    }

    public void setHeader(HeaderXml headerXml) {
        this.headerXml = headerXml;
    }

    @XmlElement(name = "Body", namespace = "soap")
    public BodyXml getBody() {
        return bodyXml;
    }

    public void setBody(BodyXml bodyXml) {
        this.bodyXml = bodyXml;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "header=" + headerXml +
                ", body=" + bodyXml +
                '}';
    }
}
