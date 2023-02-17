package com.example.tourismAgency.business.responses.roomResponses;

import com.example.tourismAgency.entities.enums.RoomType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomResponse {
//	private GetHotelResponse hotelResponse;

	private int numberOfRooms;
	
	private boolean isBooked;

	private RoomType roomType;

	private int numberOfBeds;

	private int squareMeter;

	private boolean hasTv;

	private boolean hasMinibar;

	private boolean hasGameConsole;

	private int priceFirstPeriodForAdult;

	private int priceFirstPeriodForChild;

	private int priceSecondPeriodForAdult;

	private int priceSecondPeriodForChild;
}
