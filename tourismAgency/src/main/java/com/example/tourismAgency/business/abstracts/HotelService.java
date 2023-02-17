package com.example.tourismAgency.business.abstracts;

import java.util.List;

import com.example.tourismAgency.business.requests.hotelRequests.CreateHotelRequest;
import com.example.tourismAgency.business.requests.hotelRequests.UpdateHotelRequest;
import com.example.tourismAgency.business.responses.hotelResponses.GetAllHotelResponse;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;

public interface HotelService {

	Result add(CreateHotelRequest hotelRequest);

	Result update(UpdateHotelRequest hotelRequest);

	Result deleteById(int id);

	DataResult<GetAllHotelResponse> getHotelById(int id);

	DataResult<List<GetAllHotelResponse>> getAll();

	Result makeActiveOrPassive(int id, boolean isActive);

	DataResult<List<GetAllHotelResponse>> getAllSortedByName();

	DataResult<List<GetAllHotelResponse>> getAllPageable(int pageNo, int pageSize);

	DataResult<List<GetAllHotelResponse>> getAllFilteredByCityAndHotel(int cityId, int hotelId);

	DataResult<List<GetAllHotelResponse>> getAllPageableFilteredByCityAndHotel(int pageNo, int pageSize, int cityId,
			int hotelId);

}
