import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Phone {
    private Long phone;
    private String locale;

    @JsonCreator
    public Phone(@JsonProperty("phone") Long phone, @JsonProperty("locale") String locale) {
        this.phone = phone;
        this.locale = locale;
    }

    public Long getPhone() {
        return phone;
    }

    public String getLocale() {
        return locale;
    }
}
