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

import com.example.tourismAgency.business.abstracts.FacilityService;
import com.example.tourismAgency.business.requests.facilityRequests.CreateFacilityRequest;
import com.example.tourismAgency.business.requests.facilityRequests.UpdateFacilityRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/facility")
@AllArgsConstructor
public class FacilitiesController {
	private FacilityService facilityService;

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody CreateFacilityRequest FacilityRequest) {
		return ResponseEntity.ok(this.facilityService.add(FacilityRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.facilityService.deleteById(id));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody UpdateFacilityRequest FacilityRequest) {
		return ResponseEntity.ok(this.facilityService.update(FacilityRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getFacilityById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.facilityService.getFacilityById(id));
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.facilityService.getAll());
	}
}
