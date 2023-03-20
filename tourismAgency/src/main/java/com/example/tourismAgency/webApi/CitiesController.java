package com.example.tourismAgency.webApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.CityService;
import com.example.tourismAgency.business.requests.cityRequests.CreateCityRequest;
import com.example.tourismAgency.business.requests.cityRequests.UpdateCityRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/city")
@AllArgsConstructor
public class CitiesController {

	private CityService cityService;

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody CreateCityRequest cityRequest) {
		
		return ResponseEntity.ok(this.cityService.add(cityRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		
		return ResponseEntity.ok(this.cityService.deleteById(id));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody UpdateCityRequest cityRequest) {
		
		return ResponseEntity.ok(this.cityService.update(cityRequest));
	
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCityById(@PathVariable("id") int id) {
		
		return ResponseEntity.ok(this.cityService.getCityById(id));
	
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
	
		return ResponseEntity.ok(this.cityService.getAll());
	}

}
