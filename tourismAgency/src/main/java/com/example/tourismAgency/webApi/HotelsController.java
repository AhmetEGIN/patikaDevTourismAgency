package com.example.tourismAgency.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.HotelService;
import com.example.tourismAgency.business.requests.hotelRequests.CreateHotelRequest;
import com.example.tourismAgency.business.requests.hotelRequests.UpdateHotelRequest;

@RestController
@RequestMapping("/api/hotel")
public class HotelsController {
	private HotelService hotelService;
	
	@Autowired
	public HotelsController(HotelService hotelService) {
		super();
		this.hotelService = hotelService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody CreateHotelRequest HotelRequest) {
		return ResponseEntity.ok(this.hotelService.add(HotelRequest));
	}

	@GetMapping("/deleteById")
	public ResponseEntity<?> deleteById(@RequestParam int id) {
		return ResponseEntity.ok(this.hotelService.deleteById(id));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UpdateHotelRequest HotelRequest) {
		return ResponseEntity.ok(this.hotelService.update(HotelRequest));
	}

	@GetMapping("/getById")
	public ResponseEntity<?> getHotelById(@RequestParam int id) {
		return ResponseEntity.ok(this.hotelService.getHotelById(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.hotelService.getAll());
	}
	

}
