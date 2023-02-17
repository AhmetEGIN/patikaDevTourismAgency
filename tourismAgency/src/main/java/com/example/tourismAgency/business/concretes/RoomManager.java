package com.example.tourismAgency.business.concretes;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.RoomService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.requests.roomRequests.CreateRoomRequest;
import com.example.tourismAgency.business.requests.roomRequests.UpdateRoomRequest;
import com.example.tourismAgency.business.responses.roomResponses.GetAllRoomResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.RoomRepository;
import com.example.tourismAgency.entities.concretes.Room;

@Service
public class RoomManager implements RoomService {

	private RoomRepository roomRepository;
	private MapperService mapperService;

	public RoomManager(RoomRepository roomRepository, MapperService mapperService) {
		this.roomRepository = roomRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateRoomRequest roomRequest) {
		Room room = this.mapperService.forRequest().map(roomRequest, Room.class);
		room.setId(0);
		for (int i = 0; i < roomRequest.getNumberOfRooms(); i++) {
			this.roomRepository.save(room);
			
		}
		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result update(UpdateRoomRequest roomRequest) {
		Room room = this.roomRepository.getReferenceById(roomRequest.getId());
		updateRoom(room, roomRequest);
		this.roomRepository.save(room);
		return new SuccessResult(Message.GlobalMessages.DATA_UPDATED);
	}

	@Override
	public Result deleteById(int id) {
		Room room = this.roomRepository.getReferenceById(id);
		this.roomRepository.delete(room);
		return new SuccessResult(Message.GlobalMessages.DATA_DELETED);
	}

	@Override
	public DataResult<GetRoomResponse> getRoomById(int id) {
		GetRoomResponse roomResponse = this.mapperService.forResponse().map(this.roomRepository.getReferenceById(id),
				GetRoomResponse.class);

		return new SuccessDataResult<GetRoomResponse>(roomResponse, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllRoomResponse>> getAll() {
		List<GetAllRoomResponse> roomResponses = this.roomRepository.findAll().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllRoomResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllRoomResponse>>(roomResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public DataResult<List<GetRoomResponse>> getByHotelId(int id) {
		List<GetRoomResponse> roomResponses = this.roomRepository.getByHotel_id(id).stream()
				.map(item -> this.mapperService.forResponse().map(item, GetRoomResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetRoomResponse>>(roomResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public DataResult<List<GetAllRoomResponse>> getAllFilteredByCityAndHotelAndDate(int cityId , int hotelId ) {
		Predicate<GetAllRoomResponse> cityCondition = cityId != 0
				? (room -> room.getHotelResponse().getCity().getId() == cityId)
				: (room -> room.getHotelResponse().getCity().getId() > 0);
		Predicate<GetAllRoomResponse> hotelCondition = hotelId != 0
				? (room -> room.getHotelResponse().getId() == hotelId)
				: (room -> room.getHotelResponse().getId() > 0);
//		Predicate<Room> dateCondition = (startDate != null && endDate != null)

		Stream<GetAllRoomResponse> stream = this.getAll().getData().stream();
		List<GetAllRoomResponse> roomResponses = stream.filter(cityCondition).filter(hotelCondition)
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRoomResponse>>(roomResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public DataResult<List<GetAllRoomResponse>> getAllPageable(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<GetAllRoomResponse> roomResponses = this.roomRepository.findAll(pageable).getContent().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllRoomResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllRoomResponse>>(roomResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public DataResult<List<GetAllRoomResponse>> getAllPageableAndSortedByName(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("hotel_name").descending());
		List<GetAllRoomResponse> roomResponses = this.roomRepository.findAll(pageable).getContent().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllRoomResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRoomResponse>>(roomResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public Result changeBookStatus(int roomId, boolean status) {
		Room room = this.roomRepository.getReferenceById(roomId);
		this.roomRepository.save(room);
		return new SuccessResult(Message.RoomMessages.BOOKED_STATUS_CHANGED);
	}
	
	// private codes

	private void updateRoom(Room room, UpdateRoomRequest roomRequest) {
		room.setHasGameConsole(roomRequest.isHasGameConsole());
		room.setHasMinibar(roomRequest.isHasMinibar());
		room.setHasTv(roomRequest.isHasTv());
		room.setNumberOfBeds(roomRequest.getNumberOfBeds());
		room.setPriceFirstPeriodForAdult(roomRequest.getPriceFirstPeriodForAdult());
		room.setPriceFirstPeriodForChild(roomRequest.getPriceFirstPeriodForChild());
		room.setPriceSecondPeriodForAdult(roomRequest.getPriceSecondPeriodForAdult());
		room.setPriceSecondPeriodForChild(roomRequest.getPriceSecondPeriodForChild());

	}



}
