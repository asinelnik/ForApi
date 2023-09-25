package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Phone {
    private Long phone;
    private String locale;

    @JsonCreator
    public Phone(@JsonProperty("phone") Long phone, @JsonProperty("locale") String locale) {
        this.phone = phone;
        this.locale = locale;
    }
}
