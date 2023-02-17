package com.example.tourismAgency.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.HostelTypeService;
import com.example.tourismAgency.business.requests.hostelTypeRequests.CreateHostelTypeRequest;
import com.example.tourismAgency.business.requests.hostelTypeRequests.UpdateHostelTypeRequest;

@RestController
@RequestMapping("/api/hostelType")
public class HostelTypesController {
	private HostelTypeService hostelTypeService;
	
	@Autowired
	public HostelTypesController(HostelTypeService hostelTypeService) {
		super();
		this.hostelTypeService = hostelTypeService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody CreateHostelTypeRequest HostelTypeRequest) {
		return ResponseEntity.ok(this.hostelTypeService.add(HostelTypeRequest));
	}
	
	@GetMapping("/deleteById")
	public ResponseEntity<?> deleteById(@RequestParam int id) {
		return ResponseEntity.ok(this.hostelTypeService.deleteById(id));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UpdateHostelTypeRequest HostelTypeRequest) {
		return ResponseEntity.ok(this.hostelTypeService.update(HostelTypeRequest));
	}

	@GetMapping("getById")
	public ResponseEntity<?> getHostelTypeById(@RequestParam int id) {
		return ResponseEntity.ok(this.hostelTypeService.getHostelTypeById(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.hostelTypeService.getAll());
	}

}
