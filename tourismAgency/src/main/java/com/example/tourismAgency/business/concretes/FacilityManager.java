package com.example.tourismAgency.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.FacilityService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.requests.facilityRequests.CreateFacilityRequest;
import com.example.tourismAgency.business.requests.facilityRequests.UpdateFacilityRequest;
import com.example.tourismAgency.business.responses.facilityResponses.GetAllFacilityResponse;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.business.BusinessRules;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.ErrorResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.FacilityRepository;
import com.example.tourismAgency.entities.concretes.Facility;

@Service
public class FacilityManager implements FacilityService {

	private FacilityRepository facilityRepository;
	private MapperService mapperService;

	@Autowired
	public FacilityManager(FacilityRepository facilityRepository, MapperService mapperService) {
		this.facilityRepository = facilityRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateFacilityRequest facilityRequest) {
		Result response = BusinessRules.run(existsFacilityByName(facilityRequest.getName()));
		if (response != null) {
			return response;
		}
		Facility facility = this.mapperService.forRequest().map(facilityRequest, Facility.class);
		this.facilityRepository.save(facility);

		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result update(UpdateFacilityRequest facilityRequest) {
		Facility facility = this.facilityRepository.getReferenceById(facilityRequest.getId());
		facility.setName(facilityRequest.getName());
		this.facilityRepository.save(facility);
		return new SuccessResult(Message.GlobalMessages.DATA_UPDATED);
	}

	@Override
	public Result deleteById(int id) {
		Facility facility = this.facilityRepository.getReferenceById(id);
		this.facilityRepository.delete(facility);
		
		return new SuccessResult(Message.GlobalMessages.DATA_DELETED);
	}

	@Override
	public DataResult<GetAllFacilityResponse> getFacilityById(int id) {
		GetAllFacilityResponse facilityResponse = this.mapperService.forResponse().map(facilityRepository.getReferenceById(id), GetAllFacilityResponse.class);
		return new SuccessDataResult<GetAllFacilityResponse>(facilityResponse, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllFacilityResponse>> getAll() {
		List<GetAllFacilityResponse> allFacilityResponses = this.facilityRepository.findAll().stream().map(item -> this.mapperService.forResponse().map(item, GetAllFacilityResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllFacilityResponse>>(allFacilityResponses, Message.GlobalMessages.DATA_LISTED);
	}

	// private codes
	private Result existsFacilityByName(String name) {
		return (this.facilityRepository.existsFacilityByNameEquals(name))
				? new ErrorResult(Message.FacilityMessages.FACILITY_ALREADY_EXISTS)
				: new SuccessResult();

	}

	@Override
	public DataResult<List<GetAllFacilityResponse>> getByHotelId(int id) {
		List<GetAllFacilityResponse> facilityResponses = this.facilityRepository.getByHotel_id(id).stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllFacilityResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllFacilityResponse>>(facilityResponses, Message.GlobalMessages.DATA_LISTED);
	}

}
