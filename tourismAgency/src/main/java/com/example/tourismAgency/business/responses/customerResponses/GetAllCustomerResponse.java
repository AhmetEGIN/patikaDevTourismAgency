package com.example.tourismAgency.business.responses.customerResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCustomerResponse {

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate birth;

}
