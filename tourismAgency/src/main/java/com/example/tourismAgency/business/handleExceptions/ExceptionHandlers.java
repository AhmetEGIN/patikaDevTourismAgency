package com.example.tourismAgency.business.handleExceptions;

import java.util.HashMap;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.tourismAgency.core.utilities.exceptions.BusinessException;
import com.example.tourismAgency.core.utilities.exceptions.ProblemDetails;
import com.example.tourismAgency.core.utilities.exceptions.ValidationProblemDetails;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;
	}
	
	
	public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException argumentNotValidException) {
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
		validationProblemDetails.setMessage("Validation Exceptions");
		validationProblemDetails.setValidationErrors(new HashMap<>());

		Consumer<FieldError> consumer = fieldError -> validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		argumentNotValidException.getBindingResult().getFieldErrors().stream()
		.forEach(consumer);
	
		return validationProblemDetails;
	}
	
	
}
