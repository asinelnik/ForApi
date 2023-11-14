package models.rest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateCustomerModel {
    private Long phone;
    private String name;
    private AdditionalParameters additionalParameters;

}
