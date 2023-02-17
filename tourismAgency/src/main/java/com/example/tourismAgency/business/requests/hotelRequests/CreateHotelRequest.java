package com.example.tourismAgency.business.requests.hotelRequests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelRequest {

	private String name;

	private String address;

	private String email;

	private String phoneNumber;

	private int star;
	
	private int cityId;
	
	private List<Integer> facilityIds;
	
	private List<Integer> hostelTypeIds;

}
