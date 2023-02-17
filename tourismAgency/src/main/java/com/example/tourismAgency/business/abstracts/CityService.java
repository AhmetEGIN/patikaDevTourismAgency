package com.example.tourismAgency.business.abstracts;

import java.util.List;

import com.example.tourismAgency.business.requests.cityRequests.CreateCityRequest;
import com.example.tourismAgency.business.requests.cityRequests.UpdateCityRequest;
import com.example.tourismAgency.business.responses.cityResponses.GetAllCityResponse;
import com.example.tourismAgency.business.responses.cityResponses.GetCityResponse;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;

public interface CityService {
	
	Result add(CreateCityRequest cityRequest);
	Result deleteById(int id);
	Result update(UpdateCityRequest cityRequest);
	DataResult<GetCityResponse> getCityById(int id);
	DataResult<List<GetAllCityResponse>> getAll();
	
}
