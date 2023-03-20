package com.example.tourismAgency.business.requests.facilityRequests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFacilityRequest {
	@NotNull
	private String name;
}
