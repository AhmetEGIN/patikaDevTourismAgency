package com.example.tourismAgency.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.CityService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.requests.cityRequests.CreateCityRequest;
import com.example.tourismAgency.business.requests.cityRequests.UpdateCityRequest;
import com.example.tourismAgency.business.responses.cityResponses.GetAllCityResponse;
import com.example.tourismAgency.business.responses.cityResponses.GetCityResponse;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.CityRepository;
import com.example.tourismAgency.entities.concretes.City;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CityManager implements CityService {

	private CityRepository cityRepository;
	private MapperService mapperService;

	@Override
	public Result add(CreateCityRequest cityRequest) {
		City city = this.mapperService.forRequest().map(cityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result deleteById(int id) {
		City city = this.cityRepository.getReferenceById(id);
		this.cityRepository.delete(city);
		return new SuccessResult(Message.GlobalMessages.DATA_DELETED);
	}

	@Override
	public Result update(UpdateCityRequest cityRequest) {
		City cityToUpdate = this.cityRepository.getReferenceById(cityRequest.getId());
		cityToUpdate.setName(cityRequest.getName());
		this.cityRepository.save(cityToUpdate);
		return new SuccessResult(Message.GlobalMessages.DATA_UPDATED);

	}

	@Override
	public DataResult<GetCityResponse> getCityById(int id) {
		GetCityResponse cityResponse = this.mapperService.forResponse().map(cityRepository.getReferenceById(id),
				GetCityResponse.class);
		return new SuccessDataResult<GetCityResponse>(cityResponse, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllCityResponse>> getAll() {
		List<GetAllCityResponse> getAll = this.cityRepository.findAll().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllCityResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCityResponse>>(getAll, Message.GlobalMessages.DATA_LISTED);
	}

}
