package com.example.tourismAgency.business.requests.bookRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {

	private int id;

	private int numberOfAdult;

	private int numberOfChild;

	private int price;

	private LocalDate startDate;

	private LocalDate endDate;

	private boolean isActive = true;

	private int roomId;

	private int customerId;
}
