package com.example.tourismAgency.business.rules;

import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.core.utilities.exceptions.BusinessException;
import com.example.tourismAgency.dataAccess.abstracts.HotelRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HotelBusinessRules {

	private HotelRepository hotelRepository;

	public void existsHotelByName(String name) {
		if (this.hotelRepository.existsHotelByNameContainingIgnoreCase(name)) {
			throw new BusinessException(Message.HotelMessages.HOTEL_ALREADY_EXISTS);
		}
	}

	public void existsHotelByEmail(String email) {

		if (this.hotelRepository.existsHotelByEmailEquals(email)) {
			throw new BusinessException(Message.HotelMessages.EMAIL_ALREADY_EXISTS);
		}
	}

	public void checkIfIdExists(int id) {
		if (!this.hotelRepository.existsById(id)) {
			throw new BusinessException(Message.HotelMessages.HOTEL_NOT_FOUND);
		}

	}

}
