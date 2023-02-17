package com.example.tourismAgency.business.responses.bookResponses;

import java.time.LocalDate;

import com.example.tourismAgency.business.responses.customerResponses.GetAllCustomerResponse;
import com.example.tourismAgency.business.responses.roomResponses.GetAllRoomResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBookResponse {

	private int id;

	private int numberOfAdult;

	private int numberOfChild;

	private int price;

	private LocalDate startDate;

	private LocalDate endDate;

	private boolean isActive;

	private GetAllRoomResponse getAllRoomResponse;

	private GetAllCustomerResponse allCustomerResponse;
}
