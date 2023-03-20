package com.example.tourismAgency.business.requests.hostelTypeRequests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHostelTypeRequest {

	@NotNull
	private String name;
	
}
