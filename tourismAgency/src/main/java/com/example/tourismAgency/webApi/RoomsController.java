package com.example.tourismAgency.webApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.RoomService;
import com.example.tourismAgency.business.requests.roomRequests.CreateRoomRequest;
import com.example.tourismAgency.business.requests.roomRequests.UpdateRoomRequest;

import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/api/room")
@AllArgsConstructor
public class RoomsController {

	private RoomService roomService;

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody CreateRoomRequest roomRequest) {
		return ResponseEntity.ok(this.roomService.add(roomRequest));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody UpdateRoomRequest roomRequest) {
		return ResponseEntity.ok(this.roomService.update(roomRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.roomService.deleteById(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRoomById(@RequestParam int id) {
		return ResponseEntity.ok(this.roomService.getRoomById(id));
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.roomService.getAll());
	}

	@GetMapping("/getAllFilteredByCityAndHotelAndDate/{cityid}/{hotelid}")
	public ResponseEntity<?> getAllFilteredByCityAndHotelAndDate(@PathVariable("cityid") int cityId, @PathVariable("hotelid") int hotelId) {
		return ResponseEntity.ok(this.roomService.getAllFilteredByCityAndHotelAndDate(cityId, hotelId));
	}

	@GetMapping("/getAllPageable/{pageno}/{pagesize}")
	public ResponseEntity<?> getAllPageable(@PathVariable("pageno") int pageNo, @PathVariable("pagesize") int pageSize) {
		return ResponseEntity.ok(this.roomService.getAllPageable(pageNo, pageSize));
	}

	@GetMapping("/getAllPageableAndSortedByName/{pageno}/{pagesize}")
	public ResponseEntity<?> getAllPageableAndSortedByName(@PathVariable("pageno") int pageNo, @PathVariable("pagesize") int pageSize) {
		return ResponseEntity.ok(this.roomService.getAllPageableAndSortedByName(pageNo, pageSize));
	}
}
