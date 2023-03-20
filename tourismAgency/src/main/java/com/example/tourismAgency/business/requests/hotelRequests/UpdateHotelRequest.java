package com.example.tourismAgency.business.requests.hotelRequests;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelRequest {
	
	private int id;
	
	@NotNull
	@NotBlank
	private String name;

	private String address;

	@Email
	private String email;

	@NotNull
	@NotBlank
	private String password;
	
	private String phoneNumber;

	@Min(value = 1)
	@Max(value = 5)
	private int star;
	
	private int cityId;
	
	private List<Integer> facilityIds;
	
	private List<Integer> hostelTypeIds;
}
