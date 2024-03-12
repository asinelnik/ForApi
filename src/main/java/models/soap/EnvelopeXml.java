package models.soap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Getter
@Setter
@NoArgsConstructor


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

    public EnvelopeXml(HeaderXml headerXml, BodyXml bodyXml) {
        this.headerXml = headerXml;
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
