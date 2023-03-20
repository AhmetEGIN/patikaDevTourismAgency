package com.example.tourismAgency.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.FacilityService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.requests.facilityRequests.CreateFacilityRequest;
import com.example.tourismAgency.business.requests.facilityRequests.UpdateFacilityRequest;
import com.example.tourismAgency.business.responses.facilityResponses.GetAllFacilityResponse;
import com.example.tourismAgency.business.rules.FacilityBusinessRules;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.FacilityRepository;
import com.example.tourismAgency.entities.concretes.Facility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacilityManager implements FacilityService {
	
	private FacilityBusinessRules facilityBusinessRules;
	private FacilityRepository facilityRepository;
	private MapperService mapperService;

	@Override
	public Result add(CreateFacilityRequest facilityRequest) {
		facilityBusinessRules.existsFacilityByName(facilityRequest.getName());
		Facility facility = this.mapperService.forRequest().map(facilityRequest, Facility.class);
		this.facilityRepository.save(facility);

		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result update(UpdateFacilityRequest facilityRequest) {
		Facility facility = this.facilityRepository.getReferenceById(facilityRequest.getId());
		facilityBusinessRules.existsFacilityByName(facilityRequest.getName());
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
		GetAllFacilityResponse facilityResponse = this.mapperService.forResponse()
				.map(facilityRepository.getReferenceById(id), GetAllFacilityResponse.class);
		return new SuccessDataResult<GetAllFacilityResponse>(facilityResponse, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllFacilityResponse>> getAll() {
		List<GetAllFacilityResponse> allFacilityResponses = this.facilityRepository.findAll().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllFacilityResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllFacilityResponse>>(allFacilityResponses,
				Message.GlobalMessages.DATA_LISTED);
	}


	@Override
	public DataResult<List<GetAllFacilityResponse>> getByHotelId(int id) {
		List<GetAllFacilityResponse> facilityResponses = this.facilityRepository.getByHotel_id(id).stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllFacilityResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllFacilityResponse>>(facilityResponses,
				Message.GlobalMessages.DATA_LISTED);
	}

}
