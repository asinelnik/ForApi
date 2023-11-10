package models.soap;


import javax.xml.bind.annotation.XmlElement;

public class HeaderXml {
    private String authToken;
    @XmlElement
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "Header{" +
                "authToken='" + authToken + '\'' +
                '}';
    }
}
