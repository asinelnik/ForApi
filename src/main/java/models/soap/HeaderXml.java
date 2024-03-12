package models.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Header", namespace = "soap")
public class HeaderXml {
    private String authToken;

    @XmlElement(name = "authToken", namespace = "")
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public HeaderXml() {
    }

    public HeaderXml(String authToken) {
        this.authToken = authToken;
    }
    @Override
    public String toString() {
        return "HeaderXml{" +
                "authToken='" + authToken + '\'' +
                '}';
    }
}
