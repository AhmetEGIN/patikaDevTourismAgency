package com.example.tourismAgency.core.utilities.business;

import com.example.tourismAgency.core.utilities.results.ErrorResult;
import com.example.tourismAgency.core.utilities.results.Result;

public class BusinessRules {
	
	public static Result run(Result... results) {
		for (Result result : results) {
			if (!result.isSuccess()) {
				return new ErrorResult(result.getMessage());
			}
			
		}
		return null;
	}
	
}
