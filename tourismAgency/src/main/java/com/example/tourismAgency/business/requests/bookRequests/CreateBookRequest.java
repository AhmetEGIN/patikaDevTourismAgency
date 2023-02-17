package com.example.tourismAgency.business.requests.bookRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

	private int numberOfAdult;

	private int numberOfChild;


	private LocalDate startDate;

	private LocalDate endDate;


	private int roomId;

	private int customerId;
}
