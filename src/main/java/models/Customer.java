package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
	private Long phone;
	private String name;
	private AdditionalParameters additionalParameters;
}