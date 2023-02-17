package com.example.tourismAgency.business.abstracts;

import java.util.List;

import com.example.tourismAgency.business.requests.hostelTypeRequests.CreateHostelTypeRequest;
import com.example.tourismAgency.business.requests.hostelTypeRequests.UpdateHostelTypeRequest;
import com.example.tourismAgency.business.responses.hostelTypeResponses.GetAllHostelTypeResponse;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;

public interface HostelTypeService {
	Result add(CreateHostelTypeRequest hostelTypeRequest);

	Result update(UpdateHostelTypeRequest hostelTypeRequest);

	Result deleteById(int id);

	DataResult<GetAllHostelTypeResponse> getHostelTypeById(int id);

	DataResult<List<GetAllHostelTypeResponse>> getAll();
}
