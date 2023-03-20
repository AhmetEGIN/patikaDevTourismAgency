package com.example.tourismAgency.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.CustomerService;
import com.example.tourismAgency.business.requests.customerRequests.CreateCustomerRequest;
import com.example.tourismAgency.business.requests.customerRequests.UpdateCustomerRequest;

@RestController
@RequestMapping("/api/customer")
public class CustomersController {

	private CustomerService customerService;

	@Autowired
	public CustomersController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody CreateCustomerRequest customerRequest) {
		return ResponseEntity.ok(this.customerService.add(customerRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.customerService.deleteById(id));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody UpdateCustomerRequest customerRequest) {
		return ResponseEntity.ok(this.customerService.update(customerRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.customerService.getCustomerById(id));
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.customerService.getAll());
	}

}
