package com.example.tourismAgency.business.requests.hostelTypeRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHostelTypeRequest {
	
	private int id;
	private String name;
}
