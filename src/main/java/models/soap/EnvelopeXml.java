package models.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://schemas.xmlsoap.org/soap/envelope", name = "Envelope")
public class EnvelopeXml {

    private HeaderXml headerXml;

    private BodyXml bodyXml;

    @XmlElement(name = "Header", namespace = "soap")
    public HeaderXml getHeaderXml() {
        return headerXml;
    }

    @XmlElement(name = "Body", namespace = "soap")
    public BodyXml getBodyXml() {
        return bodyXml;
    }

    public void setHeaderXml(HeaderXml headerXml) {
        this.headerXml = headerXml;
    }

    public void setBodyXml(BodyXml bodyXml) {
        this.bodyXml = bodyXml;
    }

    @Override
    public String toString() {
        return "EnvelopeXml{" +
                "headerXml=" + headerXml +
                ", bodyXml=" + bodyXml +
                '}';
    }
}
