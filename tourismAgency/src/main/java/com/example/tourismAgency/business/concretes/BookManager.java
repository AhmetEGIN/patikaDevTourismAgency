package com.example.tourismAgency.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.BookService;
import com.example.tourismAgency.business.abstracts.RoomService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.constants.seasons.Season;
import com.example.tourismAgency.business.requests.bookRequests.CreateBookRequest;
import com.example.tourismAgency.business.responses.bookResponses.GetAllBookResponse;
import com.example.tourismAgency.business.responses.bookResponses.GetBookResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.business.BusinessRules;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.ErrorDataResult;
import com.example.tourismAgency.core.utilities.results.ErrorResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.BookRepository;
import com.example.tourismAgency.entities.concretes.Book;

@Service
public class BookManager implements BookService {

	private BookRepository bookRepository;
	private MapperService mapperService;
	private RoomService roomService;

	@Autowired
	public BookManager(BookRepository bookRepository, MapperService mapperService, RoomService roomService) {
		this.bookRepository = bookRepository;
		this.mapperService = mapperService;
		this.roomService = roomService;
	}

	@Override
	public Result add(CreateBookRequest bookRequest) {
		Result response = BusinessRules.run(
				checkRoomAvailable(bookRequest.getRoomId(), bookRequest.getStartDate(), bookRequest.getEndDate()),
				checkDateBeforeNow(bookRequest.getStartDate(), bookRequest.getEndDate()),
				checkIfTheNumberOfBedsSufficient(bookRequest.getRoomId(),
						(bookRequest.getNumberOfAdult() + bookRequest.getNumberOfChild())));

		if (response != null) {
			return new ErrorResult(response.getMessage());
		}
		Book book = this.mapperService.forRequest().map(bookRequest, Book.class);
		book.setPrice(this.getPrice(bookRequest).getData());
		this.bookRepository.save(book);
		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result deleteById(int id) {
		Book book = this.bookRepository.getReferenceById(id);
		this.bookRepository.delete(book);
		return new SuccessResult(Message.GlobalMessages.DATA_DELETED);
	}

	@Override
	public DataResult<GetBookResponse> getBookById(int id) {
		GetBookResponse bookResponse = this.mapperService.forResponse().map(bookRepository.getReferenceById(id),
				GetBookResponse.class);
		return new SuccessDataResult<GetBookResponse>(bookResponse, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllBookResponse>> getAll() {
		List<GetAllBookResponse> bookResponses = this.bookRepository.findAll().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllBookResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBookResponse>>(bookResponses, Message.GlobalMessages.DATA_LISTED);
	}

	@Override
	public DataResult<Integer> getPrice(CreateBookRequest bookRequest) {
		Period between = Period.between(bookRequest.getStartDate(), bookRequest.getEndDate());
		int dayRange = between.getDays() + 1;
		GetRoomResponse room = this.getRoomById(bookRequest.getRoomId()).getData();
		if (bookRequest.getStartDate().isAfter(Season.beginnigOfFirstPeriod)
				&& bookRequest.getStartDate().isBefore(Season.endOfFirstPeriod)
				&& bookRequest.getEndDate().isBefore(Season.endOfFirstPeriod)) {
			int price = dayRange * bookRequest.getNumberOfAdult() * room.getPriceFirstPeriodForAdult()
					+ dayRange * bookRequest.getNumberOfChild() * room.getPriceFirstPeriodForChild();
			return new SuccessDataResult<Integer>(price);
		} else if (bookRequest.getStartDate().isAfter(Season.beginnigOfSecondPeriod)
				&& bookRequest.getStartDate().isBefore(Season.endOfSecondPeriod)
				&& bookRequest.getEndDate().isBefore(Season.endOfSecondPeriod)) {
			int price = dayRange * bookRequest.getNumberOfAdult() * room.getPriceSecondPeriodForAdult()
					+ dayRange * bookRequest.getNumberOfChild() * room.getPriceSecondPeriodForChild();
			return new SuccessDataResult<Integer>(price);
		} else if (bookRequest.getStartDate().isAfter(Season.beginnigOfFirstPeriod)
				&& bookRequest.getStartDate().isBefore(Season.endOfFirstPeriod)
				&& bookRequest.getEndDate().isAfter(Season.beginnigOfSecondPeriod)) {
			int dayRangeInFirtsSeason = Period.between(bookRequest.getStartDate(), Season.endOfFirstPeriod).getDays()
					+ 1;
			int dayRangeInSecondSeaseon = Period.between(Season.beginnigOfSecondPeriod, bookRequest.getEndDate())
					.getDays() + 1;
			int price = dayRangeInFirtsSeason * bookRequest.getNumberOfAdult() * room.getPriceFirstPeriodForAdult()
					+ dayRangeInFirtsSeason * bookRequest.getNumberOfChild() * room.getPriceFirstPeriodForChild()
					+ dayRangeInSecondSeaseon * bookRequest.getNumberOfAdult() * room.getPriceSecondPeriodForAdult()
					+ dayRangeInSecondSeaseon * bookRequest.getNumberOfChild() * room.getPriceSecondPeriodForChild();
			return new SuccessDataResult<Integer>(price);

		}else if(bookRequest.getStartDate().isAfter(Season.beginnigOfSecondPeriod)
				&& bookRequest.getStartDate().isBefore(Season.endOfSecondPeriod)
				&& bookRequest.getEndDate().isAfter(Season.beginnigOfFirstPeriod)) {
			int dayRangeInFirstSeason = Period.between(Season.beginnigOfFirstPeriod, bookRequest.getEndDate()).getDays() + 1;
			int dayRangeInSecondSeason = Period.between(bookRequest.getStartDate(), Season.endOfSecondPeriod).getDays() + 1;
			int price = dayRangeInFirstSeason * bookRequest.getNumberOfAdult() * room.getPriceFirstPeriodForAdult()
					+ dayRangeInFirstSeason * bookRequest.getNumberOfChild() * room.getPriceFirstPeriodForChild()
					+ dayRangeInSecondSeason * bookRequest.getNumberOfAdult() * room.getPriceSecondPeriodForAdult()
					+ dayRangeInSecondSeason * bookRequest.getNumberOfChild() * room.getPriceSecondPeriodForChild();
			return new SuccessDataResult<Integer>(price);
		}
			

		return new ErrorDataResult<Integer>("Etwas schiefgeht");
	}

	@Override
	public Result checkOut(int bookId) {
		Book book = this.bookRepository.getReferenceById(bookId);
		book.setActive(false);
		return new SuccessResult(Message.BookMessages.CHECK_OUT_SUCCESS);
	}

	@Override
	public DataResult<List<GetAllBookResponse>> getBookByCustomerId(int id) {
		List<GetAllBookResponse> bookResponses = this.bookRepository.getBookByCustomer_Id(id).stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllBookResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBookResponse>>(bookResponses, Message.GlobalMessages.DATA_LISTED);
	}

	// private codes

	private Result checkDateBeforeNow(LocalDate startDate, LocalDate endDate) {
		if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
			return new ErrorResult(Message.BookMessages.CHECK_DATE);
		}
		return new SuccessResult();

	}

	private Result checkRoomAvailable(int roomId, LocalDate startDate, LocalDate endDate) {
		Book book = this.getActiveBookByRoomId(roomId).getData();
		if (book == null) {
			return new SuccessResult();
		}
		if ((startDate.isAfter(book.getEndDate()) && endDate.isAfter(book.getEndDate()))
				|| (startDate.isBefore(book.getStartDate()) && endDate.isBefore(book.getStartDate()))) {
			return new SuccessResult();
		}

		return new ErrorResult(Message.BookMessages.ROOM_IS_ALREADY_BOOKED_IN_SELECTED_DATE_RANGE);
	}

	private DataResult<GetRoomResponse> getRoomById(int id) {
		return this.roomService.getRoomById(id);
	}

	private DataResult<Book> getActiveBookByRoomId(int roomId) {

		return new SuccessDataResult<Book>(this.bookRepository.getByRoomIdAndIsActive(roomId, true));
	}

	private Result checkIfTheNumberOfBedsSufficient(int roomId, int numberOfGuest) {
		GetRoomResponse room = this.roomService.getRoomById(roomId).getData();
		if (numberOfGuest > room.getNumberOfBeds()) {
			return new ErrorResult(Message.BookMessages.NUMBER_OF_BEDS_NOT_SUFFICIENT);
		}
		return new SuccessResult();
	}

}
