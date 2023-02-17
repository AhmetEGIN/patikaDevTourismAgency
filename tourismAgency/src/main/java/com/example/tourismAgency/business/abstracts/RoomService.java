package com.example.tourismAgency.business.abstracts;

import java.util.List;

import com.example.tourismAgency.business.requests.roomRequests.CreateRoomRequest;
import com.example.tourismAgency.business.requests.roomRequests.UpdateRoomRequest;
import com.example.tourismAgency.business.responses.roomResponses.GetAllRoomResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;

public interface RoomService {
	Result add(CreateRoomRequest roomRequest);
	Result update(UpdateRoomRequest roomRequest);
	Result deleteById(int id);
	DataResult<GetRoomResponse> getRoomById(int id);
	DataResult<List<GetAllRoomResponse>> getAll();
	DataResult<List<GetAllRoomResponse>> getAllFilteredByCityAndHotelAndDate(int cityId, int hotelId);
	DataResult<List<GetAllRoomResponse>> getAllPageable(int pageNo, int pageSize);
	DataResult<List<GetAllRoomResponse>> getAllPageableAndSortedByName(int pageNo, int pageSize);
	
	DataResult<List<GetRoomResponse>> getByHotelId(int id);
	Result changeBookStatus(int roomId, boolean status);
//	DataResult<List<GetAllRoomResponse>> getByCityId(int id);
//	DataResult<List<GetAllRoomResponse>> getByHotelId(int id);
//	DataResult<List<GetAllRoomResponse>> getByDate(LocalDate startDate, LocalDate endDate);
}
