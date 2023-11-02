package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseMemberReturn {
    private String pd;
    private Long phone;
    private String customerId;
    private String name;
    private AdditionalParameters additionalParameters;
    private String status;

}
