package com.example.tourismAgency.business.rules;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.RoomService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;
import com.example.tourismAgency.core.utilities.exceptions.BusinessException;
import com.example.tourismAgency.dataAccess.abstracts.BookRepository;
import com.example.tourismAgency.entities.concretes.Book;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookBusinessRules {

	private BookRepository bookRepository;
	private RoomService roomService;

	public void checkIfTheNumberOfBedsSufficient(int roomId, int numberOfGuest) {

		GetRoomResponse room = this.roomService.getRoomById(roomId).getData();
		if (numberOfGuest > room.getNumberOfBeds()) {
			throw new BusinessException(Message.BookMessages.NUMBER_OF_BEDS_NOT_SUFFICIENT);

		}

	}

	public void checkDateBeforeNow(LocalDate startDate, LocalDate endDate) {
		if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
			throw new BusinessException(Message.BookMessages.CHECK_DATE);
		}

	}

	public void checkRoomAvailable(int roomId, LocalDate startDate, LocalDate endDate) {

		Book book = this.bookRepository.getByRoomIdAndIsActive(roomId, true);
		if (book == null) {
			return;
		}
		if ((startDate.isAfter(book.getEndDate()) && endDate.isAfter(book.getEndDate()))
				|| (startDate.isBefore(book.getStartDate()) && endDate.isBefore(book.getStartDate()))) {
			return;
		}
		throw new BusinessException(Message.BookMessages.ROOM_IS_ALREADY_BOOKED_IN_SELECTED_DATE_RANGE);
	}

}
