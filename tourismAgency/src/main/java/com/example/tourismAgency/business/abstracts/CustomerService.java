package com.example.tourismAgency.business.abstracts;

import java.util.List;

import com.example.tourismAgency.business.requests.customerRequests.CreateCustomerRequest;
import com.example.tourismAgency.business.requests.customerRequests.UpdateCustomerRequest;
import com.example.tourismAgency.business.responses.customerResponses.GetAllCustomerResponse;
import com.example.tourismAgency.business.responses.customerResponses.GetCustomerResponse;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;

public interface CustomerService {
	
	Result add(CreateCustomerRequest customerRequest);
	Result deleteById(int id);
	Result update(UpdateCustomerRequest customerRequest);
	DataResult<GetCustomerResponse> getCustomerById(int id);
	DataResult<List<GetAllCustomerResponse>> getAll();
	
}
