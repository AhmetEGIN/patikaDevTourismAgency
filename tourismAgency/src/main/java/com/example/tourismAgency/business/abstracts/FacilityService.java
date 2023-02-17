package com.example.tourismAgency.business.abstracts;

import java.util.List;

import com.example.tourismAgency.business.requests.facilityRequests.CreateFacilityRequest;
import com.example.tourismAgency.business.requests.facilityRequests.UpdateFacilityRequest;
import com.example.tourismAgency.business.responses.facilityResponses.GetAllFacilityResponse;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;

public interface FacilityService {
	Result add(CreateFacilityRequest facilityRequest);

	Result update(UpdateFacilityRequest facilityRequest);

	Result deleteById(int id);

	DataResult<GetAllFacilityResponse> getFacilityById(int id);

	DataResult<List<GetAllFacilityResponse>> getAll();

	DataResult<List<GetAllFacilityResponse>> getByHotelId(int id);
}
