package models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
	private Long phone;
	private String name;
	private AdditionalParameters additionalParameters;
}
