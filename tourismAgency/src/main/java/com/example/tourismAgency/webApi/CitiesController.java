package com.example.tourismAgency.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.CityService;
import com.example.tourismAgency.business.requests.cityRequests.CreateCityRequest;
import com.example.tourismAgency.business.requests.cityRequests.UpdateCityRequest;

@RestController
@RequestMapping("/api/city")
public class CitiesController {

	private CityService cityService;
	
	@Autowired
	public CitiesController(CityService cityService) {

		this.cityService = cityService;
	}

	

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody CreateCityRequest cityRequest) {
		return ResponseEntity.ok(this.cityService.add(cityRequest));
	}

	@GetMapping("/deleteById")
	public ResponseEntity<?> deleteById(@RequestParam int id) {
		return ResponseEntity.ok(this.cityService.deleteById(id));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UpdateCityRequest cityRequest) {
		return ResponseEntity.ok(this.cityService.update(cityRequest));
	}

	@GetMapping("getById")
	public ResponseEntity<?> getCityById(@RequestParam int id) {
		return ResponseEntity.ok(this.cityService.getCityById(id));
	}

	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.cityService.getAll());
	}


}
