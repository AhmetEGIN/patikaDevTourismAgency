package com.example.tourismAgency.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.FacilityService;
import com.example.tourismAgency.business.abstracts.HostelTypeService;
import com.example.tourismAgency.business.abstracts.HotelService;
import com.example.tourismAgency.business.abstracts.RoomService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.requests.hotelRequests.CreateHotelRequest;
import com.example.tourismAgency.business.requests.hotelRequests.UpdateHotelRequest;
import com.example.tourismAgency.business.responses.facilityResponses.GetAllFacilityResponse;
import com.example.tourismAgency.business.responses.hotelResponses.GetAllHotelResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;
import com.example.tourismAgency.business.rules.HotelBusinessRules;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.HotelRepository;
import com.example.tourismAgency.entities.concretes.Facility;
import com.example.tourismAgency.entities.concretes.HostelType;
import com.example.tourismAgency.entities.concretes.Hotel;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HotelManager implements HotelService {

	private HotelRepository hotelRepository;
	private MapperService mapperService;
	private FacilityService facilityService;
	private HostelTypeService hostelTypeService;
	private RoomService roomService;
	private HotelBusinessRules hotelBusinessRules;

	@Override
	public Result add(CreateHotelRequest hotelRequest) {

		hotelBusinessRules.existsHotelByEmail(hotelRequest.getEmail());
		hotelBusinessRules.existsHotelByName(hotelRequest.getName());

		Hotel hotel = this.mapperService.forRequest().map(hotelRequest, Hotel.class);
		hotel.setId(0);
		setFacilities(hotel, hotelRequest.getFacilityIds());
		setHostelTypes(hotel, hotelRequest.getHostelTypeIds());
		this.hotelRepository.save(hotel);

		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result update(UpdateHotelRequest hotelRequest) {
		
		hotelBusinessRules.checkIfIdExists(hotelRequest.getId());
		
		Hotel hotel = this.mapperService.forRequest().map(hotelRequest, Hotel.class);
		hotel.setAddress(hotelRequest.getAddress());
		hotel.setEmail(hotelRequest.getEmail());
		hotel.setPhoneNumber(hotelRequest.getPhoneNumber());
		hotel.setStar(hotelRequest.getStar());
		setHostelTypes(hotel, hotelRequest.getHostelTypeIds());
		setFacilities(hotel, hotelRequest.getFacilityIds());
		this.hotelRepository.save(hotel);
		return new SuccessResult(Message.GlobalMessages.DATA_UPDATED);
	}

	@Override
	public Result deleteById(int id) {
		Hotel hotel = this.hotelRepository.getReferenceById(id);
		this.hotelRepository.delete(hotel);
		return new SuccessResult(Message.GlobalMessages.DATA_DELETED);
	}

	@Override
	public DataResult<GetAllHotelResponse> getHotelById(int id) {
//		GetHotelResponse hotelResponse = this.mapperService.forResponse().map(hotelRepository.getReferenceById(id),
//				GetHotelResponse.class);
		Optional<Hotel> hotel = this.hotelRepository.findById(id);
		GetAllHotelResponse hotelResponse = this.mapperService.forResponse().map(hotel.get(),
				GetAllHotelResponse.class);

		List<GetAllFacilityResponse> allFacilityResponses = this.facilityService.getByHotelId(hotelResponse.getId())
				.getData();
		List<GetRoomResponse> allRoomResponses = this.roomService.getByHotelId(hotelResponse.getId()).getData();
		hotelResponse.setGetAllFacilityResponse(allFacilityResponses);
		hotelResponse.setGetRoomResponses(allRoomResponses);

//		List<GetFacilityResponse> allFacilityResponses = this.facilityService.getByHotelId(id).getData();
//		hotelResponse.setGetFacilityResponse(allFacilityResponses);
		return new SuccessDataResult<GetAllHotelResponse>(hotelResponse, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllHotelResponse>> getAll() {
//		List<GetAllHotelResponse> allHotelResponses = new ArrayList<>();
//		for (Hotel hotel : this.hotelRepository.findAll()) {
//			GetAllHotelResponse hotelResponse = this.mapperService.forResponse().map(hotel, GetAllHotelResponse.class);			
//		}
		List<GetAllHotelResponse> allHotelResponses = this.hotelRepository.findAll().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllHotelResponse.class))
				.collect(Collectors.toList());

		// TODO: Burada bir hata var. Mapper Facility class üzerinde map işlemi
		// yapmıyor. Facility manuel olarak eklenmek zorunda
		for (GetAllHotelResponse hotel : allHotelResponses) {
			List<GetAllFacilityResponse> allFacilityResponses = this.facilityService.getByHotelId(hotel.getId())
					.getData();
			List<GetRoomResponse> allRoomResponses = this.roomService.getByHotelId(hotel.getId()).getData();
			hotel.setGetAllFacilityResponse(allFacilityResponses);
			hotel.setGetRoomResponses(allRoomResponses);
		}

		return new SuccessDataResult<List<GetAllHotelResponse>>(allHotelResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public Result makeActiveOrPassive(int id, boolean isActive) {
		Hotel hotel = this.hotelRepository.getReferenceById(id);
		hotel.setActive(isActive);
		this.hotelRepository.save(hotel);

		return new SuccessResult(Message.HotelMessages.IS_ACTIVE_CHANGED);
	}

	@Override
	public DataResult<List<GetAllHotelResponse>> getAllSortedByName() {
		Sort sort = Sort.by("name");
		List<GetAllHotelResponse> hotelResponses = this.hotelRepository.findAll(sort).stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllHotelResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllHotelResponse>>(hotelResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public DataResult<List<GetAllHotelResponse>> getAllPageable(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<GetAllHotelResponse> hotelResponses = this.hotelRepository.findAll(pageable).stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllHotelResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllHotelResponse>>(hotelResponses, Message.GlobalMessages.DATA_LISTED);

	}

	@Override
	public DataResult<List<GetAllHotelResponse>> getAllFilteredByCityAndHotel(int cityId, int hotelId) {
		Predicate<GetAllHotelResponse> cityCondition = cityId != 0 ? (hotel -> hotel.getCity().getId() == cityId)
				: (hotel -> hotel.getCity().getId() > 0);

		Predicate<GetAllHotelResponse> hotelCondition = hotelId != 0 ? (hotel -> hotel.getId() == hotelId)
				: (hotel -> hotel.getId() > 0);

		Stream<GetAllHotelResponse> stream = this.getAll().getData().stream();
		List<GetAllHotelResponse> hotelResponses = stream.filter(hotelCondition).filter(cityCondition)
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllHotelResponse>>(hotelResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public DataResult<List<GetAllHotelResponse>> getAllPageableFilteredByCityAndHotel(int pageNo, int pageSize,
			int cityId, int hotelId) {
		Predicate<GetAllHotelResponse> cityCondition = cityId != 0 ? (hotel -> hotel.getCity().getId() == cityId)
				: (hotel -> hotel.getCity().getId() > 0);

		Predicate<GetAllHotelResponse> hotelCondition = hotelId != 0 ? (hotel -> hotel.getId() == hotelId)
				: (hotel -> hotel.getId() > 0);

		Stream<GetAllHotelResponse> stream = this.getAllPageable(pageNo, pageSize).getData().stream();
		List<GetAllHotelResponse> hotelResponses = stream.filter(hotelCondition).filter(cityCondition)
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllHotelResponse>>(hotelResponses, Message.GlobalMessages.DATA_LISTED);
	}

	// private codes

	private void setFacilities(Hotel hotel, List<Integer> ids) {
		List<Facility> facilities = new ArrayList<>();
		for (Integer id : ids) {
			Facility facility = new Facility();
			facility = this.mapperService.forResponse().map(this.facilityService.getFacilityById(id).getData(),
					Facility.class);

			facilities.add(facility);
		}
		hotel.setFacilities(facilities);

	}

	private void setHostelTypes(Hotel hotel, List<Integer> ids) {
		List<HostelType> hostelTypes = new ArrayList<>();
		for (Integer id : ids) {
			HostelType hostelType = new HostelType();
			hostelType = this.mapperService.forResponse().map(this.hostelTypeService.getHostelTypeById(id).getData(),
					HostelType.class);
			hostelTypes.add(hostelType);
		}
		hotel.setHostelTypes(hostelTypes);
	}


}
