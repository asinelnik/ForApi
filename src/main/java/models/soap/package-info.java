@XmlSchema(
        namespace = "http://schemas.xmlsoap.org/soap/envelope",
        elementFormDefault = XmlNsForm.QUALIFIED,

        xmlns = {
                @XmlNs(prefix = "ns2", namespaceURI = "soap"),
                @XmlNs(prefix = "ns3", namespaceURI = "http://schemas.xmlsoap.org/soap/envelope")
        }
)
package models.soap;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;