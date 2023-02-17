package com.example.tourismAgency.business.requests.facilityRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFacilityRequest {
	private int id;
	private String name;
}
