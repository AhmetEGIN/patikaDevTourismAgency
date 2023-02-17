package com.example.tourismAgency.business.requests.roomRequests;

import com.example.tourismAgency.entities.enums.RoomType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequest {

	private int id;

	private int hotelId;
	
//	private boolean isBooked;

	private int numberOfRooms;
	
	private int numberOfVacantRooms;

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
