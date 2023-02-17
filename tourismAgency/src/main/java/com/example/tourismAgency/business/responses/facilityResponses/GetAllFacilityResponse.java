package com.example.tourismAgency.business.responses.facilityResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFacilityResponse {
	
	private int id;
	private String name;
}
