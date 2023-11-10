package models.soap;

import javax.xml.bind.annotation.XmlElement;


public class BodyXml {
    private Long phoneNumber;

    @XmlElement
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Body{" +
                "phoneNumber=" + phoneNumber +
                '}';
    }
}
