package com.example.tourismAgency.business.responses.hotelResponses;

import java.util.List;

import com.example.tourismAgency.business.responses.cityResponses.GetCityResponse;
import com.example.tourismAgency.business.responses.facilityResponses.GetFacilityResponse;
import com.example.tourismAgency.business.responses.hostelTypeResponses.GetHostelTypeResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetHotelResponse {

	private String name;

	private String address;

	private String email;

	private String phoneNumber;

	private int star;
	
	private GetCityResponse city;
	
	private List<GetFacilityResponse> getFacilityResponse;
	
	private List<GetHostelTypeResponse> getHostelTypeResponse;
	
	private List<GetRoomResponse> getRoomResponses;
}
