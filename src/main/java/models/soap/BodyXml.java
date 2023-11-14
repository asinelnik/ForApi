package models.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Body", namespace = "soap")
public class BodyXml {
    private Long phoneNumber;

    @XmlElement(name = "phoneNumber", namespace = "")
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "BodyXml{" +
                "phoneNumber=" + phoneNumber +
                '}';
    }
}
