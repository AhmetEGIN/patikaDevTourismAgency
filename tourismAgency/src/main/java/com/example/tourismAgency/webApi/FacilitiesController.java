package com.example.tourismAgency.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.FacilityService;
import com.example.tourismAgency.business.requests.facilityRequests.CreateFacilityRequest;
import com.example.tourismAgency.business.requests.facilityRequests.UpdateFacilityRequest;

@RestController
@RequestMapping("/api/facility")
public class FacilitiesController {
	private FacilityService facilityService;
	
	
	@Autowired
	public FacilitiesController(FacilityService facilityService) {
		this.facilityService = facilityService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody CreateFacilityRequest FacilityRequest) {
		return ResponseEntity.ok(this.facilityService.add(FacilityRequest));
	}

	@GetMapping("/deleteById")
	public ResponseEntity<?> deleteById(@RequestParam int id) {
		return ResponseEntity.ok(this.facilityService.deleteById(id));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UpdateFacilityRequest FacilityRequest) {
		return ResponseEntity.ok(this.facilityService.update(FacilityRequest));
	}

	@GetMapping("/getById")
	public ResponseEntity<?> getFacilityById(@RequestParam int id) {
		return ResponseEntity.ok(this.facilityService.getFacilityById(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.facilityService.getAll());
	}
}
