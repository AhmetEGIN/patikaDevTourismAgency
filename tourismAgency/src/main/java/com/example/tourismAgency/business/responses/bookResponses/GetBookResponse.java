package com.example.tourismAgency.business.responses.bookResponses;

import java.time.LocalDate;

import com.example.tourismAgency.business.responses.customerResponses.GetCustomerResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetRoomResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBookResponse {
	private int numberOfAdult;

	private int numberOfChild;

	private int price;

	private LocalDate startDate;

	private LocalDate endDate;

	private boolean isActive;

	private GetRoomResponse getAllRoomResponse;

	private GetCustomerResponse allCustomerResponse;
}
