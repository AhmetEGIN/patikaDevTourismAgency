package com.example.tourismAgency.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.HostelTypeService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.requests.hostelTypeRequests.CreateHostelTypeRequest;
import com.example.tourismAgency.business.requests.hostelTypeRequests.UpdateHostelTypeRequest;
import com.example.tourismAgency.business.responses.hostelTypeResponses.GetAllHostelTypeResponse;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.business.BusinessRules;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.ErrorResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.HostelTypeRepository;
import com.example.tourismAgency.entities.concretes.HostelType;

@Service
public class HostelTypeManager implements HostelTypeService {

	private HostelTypeRepository hostelTypeRepository;
	private MapperService mapperService;
	
	@Autowired
	public HostelTypeManager(HostelTypeRepository hostelTypeRepository, MapperService mapperService) {
		this.hostelTypeRepository = hostelTypeRepository;
		this.mapperService = mapperService;
	}
	

	@Override
	public Result add(CreateHostelTypeRequest hostelTypeRequest) {
		HostelType hostelType = mapperService.forRequest().map(hostelTypeRequest, HostelType.class);
		hostelTypeRepository.save(hostelType);
		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result update(UpdateHostelTypeRequest hostelTypeRequest) {
		Result response = BusinessRules.run(
				checkIfIdExists(hostelTypeRequest.getId())
				);
		if (response != null) {
			return response;
		}
		HostelType hostelType = hostelTypeRepository.getReferenceById(hostelTypeRequest.getId());
		updateHostelTypeInfo(hostelType, hostelTypeRequest);
		this.hostelTypeRepository.save(hostelType);
		return new SuccessResult(Message.GlobalMessages.DATA_UPDATED);
	}

	@Override
	public Result deleteById(int id) {
		HostelType hostelType = this.hostelTypeRepository.getReferenceById(id);
		this.hostelTypeRepository.delete(hostelType);
		return new SuccessResult(Message.GlobalMessages.DATA_DELETED);
	}

	@Override
	public DataResult<GetAllHostelTypeResponse> getHostelTypeById(int id) {
		GetAllHostelTypeResponse response = this.mapperService.forResponse().map(this.hostelTypeRepository.getReferenceById(id), GetAllHostelTypeResponse.class); 
		
		return new SuccessDataResult<GetAllHostelTypeResponse>(response, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllHostelTypeResponse>> getAll() {
		
		List<GetAllHostelTypeResponse> responses = this.hostelTypeRepository.findAll().stream().map(item -> this.mapperService.forResponse().map(item, GetAllHostelTypeResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllHostelTypeResponse>>(responses, Message.GlobalMessages.DATA_LISTED);
	}
	
	
	// private codes
	
	private void updateHostelTypeInfo(HostelType hostelType, UpdateHostelTypeRequest updateHostelTypeRequest) {

		hostelType.setName(updateHostelTypeRequest.getName());		
	}
	
	private Result checkIfIdExists(int id) {
		return (this.hostelTypeRepository.existsById(id)) ?  new SuccessResult() : new ErrorResult(Message.HostelTypeMessages.HOSTEL_TYPE_NOT_FOUND);
	}

}
