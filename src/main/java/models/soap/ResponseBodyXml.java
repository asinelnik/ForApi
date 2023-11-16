package models.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Body", namespace = "soap")
public class ResponseBodyXml {
    private String customerId;
    @XmlElement(name = "customerId", namespace = "")
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ResponseBodyXml{" +
                "customerId='" + customerId + '\'' +
                '}';
    }
}
