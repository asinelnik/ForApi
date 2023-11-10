package models.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateCustomerResponse {
    private String customerId;
    private String name;
    private String status;
    private Long phone;
    private Map<String, String> additionalParameters;
    private String pd;
}
