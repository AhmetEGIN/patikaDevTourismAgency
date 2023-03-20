package com.example.tourismAgency.business.abstracts;

import java.util.List;

import com.example.tourismAgency.business.requests.bookRequests.CreateBookRequest;
import com.example.tourismAgency.business.responses.bookResponses.GetAllBookResponse;
import com.example.tourismAgency.business.responses.bookResponses.GetBookResponse;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;

public interface BookService {
	Result add(CreateBookRequest bookRequest);
	Result deleteById(int id);
	DataResult<GetBookResponse> getBookById(int id);
	DataResult<List<GetAllBookResponse>> getAll();
	
	DataResult<Integer> getPrice(CreateBookRequest bookRequest);
	Result checkOut(int bookId);
	
	DataResult<List<GetAllBookResponse>> getBookByCustomerId(int id);

}
