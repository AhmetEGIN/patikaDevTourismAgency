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

import com.example.tourismAgency.business.abstracts.HotelService;
import com.example.tourismAgency.business.requests.hotelRequests.CreateHotelRequest;
import com.example.tourismAgency.business.requests.hotelRequests.UpdateHotelRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/hotel")
@AllArgsConstructor
public class HotelsController {
	private HotelService hotelService;

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody CreateHotelRequest HotelRequest) {
		return ResponseEntity.ok(this.hotelService.add(HotelRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.hotelService.deleteById(id));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody UpdateHotelRequest HotelRequest) {
		return ResponseEntity.ok(this.hotelService.update(HotelRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getHotelById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.hotelService.getHotelById(id));
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.hotelService.getAll());
	}

}
