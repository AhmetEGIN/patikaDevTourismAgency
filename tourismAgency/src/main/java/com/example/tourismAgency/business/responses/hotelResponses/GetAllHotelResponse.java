package com.example.tourismAgency.business.responses.hotelResponses;

import java.util.List;

import com.example.tourismAgency.business.responses.cityResponses.GetAllCityResponse;
import com.example.tourismAgency.business.responses.facilityResponses.GetAllFacilityResponse;
import com.example.tourismAgency.business.responses.hostelTypeResponses.GetAllHostelTypeResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllHotelResponse {

	private int id;

	private String name;

	private String address;

	private String email;

	private String phoneNumber;

	private int star;

	private GetAllCityResponse city;

	private List<GetAllHostelTypeResponse> getAllHostelTypeResponse;
	
	private List<GetAllFacilityResponse> getAllFacilityResponse;
	
	private List<GetRoomResponse> getRoomResponses;
}
