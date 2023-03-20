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

import com.example.tourismAgency.business.abstracts.HostelTypeService;
import com.example.tourismAgency.business.requests.hostelTypeRequests.CreateHostelTypeRequest;
import com.example.tourismAgency.business.requests.hostelTypeRequests.UpdateHostelTypeRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/hostelType")
@AllArgsConstructor
public class HostelTypesController {
	private HostelTypeService hostelTypeService;

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody CreateHostelTypeRequest HostelTypeRequest) {
		return ResponseEntity.ok(this.hostelTypeService.add(HostelTypeRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		return ResponseEntity.ok(this.hostelTypeService.deleteById(id));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody UpdateHostelTypeRequest HostelTypeRequest) {
		return ResponseEntity.ok(this.hostelTypeService.update(HostelTypeRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getHostelTypeById(@PathVariable int id) {
		return ResponseEntity.ok(this.hostelTypeService.getHostelTypeById(id));
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.hostelTypeService.getAll());
	}

}
