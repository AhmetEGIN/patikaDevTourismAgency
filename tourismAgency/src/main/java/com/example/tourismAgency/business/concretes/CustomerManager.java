package com.example.tourismAgency.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.tourismAgency.business.abstracts.CustomerService;
import com.example.tourismAgency.business.constants.messages.Message;
import com.example.tourismAgency.business.requests.customerRequests.CreateCustomerRequest;
import com.example.tourismAgency.business.requests.customerRequests.UpdateCustomerRequest;
import com.example.tourismAgency.business.responses.customerResponses.GetAllCustomerResponse;
import com.example.tourismAgency.business.responses.customerResponses.GetCustomerResponse;
import com.example.tourismAgency.config.mapper.MapperService;
import com.example.tourismAgency.core.utilities.results.DataResult;
import com.example.tourismAgency.core.utilities.results.Result;
import com.example.tourismAgency.core.utilities.results.SuccessDataResult;
import com.example.tourismAgency.core.utilities.results.SuccessResult;
import com.example.tourismAgency.dataAccess.abstracts.CustomerRepository;
import com.example.tourismAgency.entities.concretes.Customer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

	private CustomerRepository customerRepository;
	private MapperService mapperService;

	@Override
	public Result add(CreateCustomerRequest customerRequest) {
		Customer customer = this.mapperService.forResponse().map(customerRequest, Customer.class);
		this.customerRepository.save(customer);
		return new SuccessResult(Message.GlobalMessages.DATA_ADDED);
	}

	@Override
	public Result deleteById(int id) {
		Customer customer = this.customerRepository.getReferenceById(id);
		this.customerRepository.delete(customer);
		return new SuccessResult(Message.GlobalMessages.DATA_DELETED);
	}

	@Override
	public Result update(UpdateCustomerRequest customerRequest) {
		Customer customer = this.customerRepository.getReferenceById(customerRequest.getId());
		updateCustomer(customer, customerRequest);
		this.customerRepository.save(customer);

		return new SuccessResult(Message.GlobalMessages.DATA_UPDATED);
	}

	@Override
	public DataResult<GetCustomerResponse> getCustomerById(int id) {
		GetCustomerResponse customerResponse = this.mapperService.forResponse()
				.map(customerRepository.getReferenceById(id), GetCustomerResponse.class);
		return new SuccessDataResult<GetCustomerResponse>(customerResponse, Message.GlobalMessages.DATA_BROUGHT);
	}

	@Override
	public DataResult<List<GetAllCustomerResponse>> getAll() {
		List<GetAllCustomerResponse> customerResponses = this.customerRepository.findAll().stream()
				.map(item -> this.mapperService.forResponse().map(item, GetAllCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCustomerResponse>>(customerResponses,
				Message.GlobalMessages.DATA_LISTED);
	}

	private void updateCustomer(Customer customer, UpdateCustomerRequest customerRequest) {
		customer.setFirstName(customerRequest.getFirstName());
		customer.setLastName(customerRequest.getLastName());

	}

}
