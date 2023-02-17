package com.example.tourismAgency.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.RoomService;
import com.example.tourismAgency.business.requests.roomRequests.CreateRoomRequest;
import com.example.tourismAgency.business.requests.roomRequests.UpdateRoomRequest;

@RestController()
@RequestMapping("/api/room")
public class RoomsController {

	private RoomService roomService;

	@Autowired
	public RoomsController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody CreateRoomRequest roomRequest) {
		return ResponseEntity.ok(this.roomService.add(roomRequest));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UpdateRoomRequest roomRequest) {
		return ResponseEntity.ok(this.roomService.update(roomRequest));
	}

	@GetMapping("/deleteById")
	public ResponseEntity<?> deleteById(@RequestParam int id) {
		return ResponseEntity.ok(this.roomService.deleteById(id));
	}

	@GetMapping("/getById")
	public ResponseEntity<?> getRoomById(@RequestParam int id) {
		return ResponseEntity.ok(this.roomService.getRoomById(id));
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.roomService.getAll());
	}

	@GetMapping("/getAllFilteredByCityAndHotelAndDate")
	public ResponseEntity<?> getAllFilteredByCityAndHotelAndDate(@RequestParam int cityId, @RequestParam int hotelId) {
		return ResponseEntity.ok(this.roomService.getAllFilteredByCityAndHotelAndDate(cityId, hotelId));
	}

	@GetMapping("/getAllPageable")
	public ResponseEntity<?> getAllPageable(@RequestParam int pageNo, @RequestParam int pageSize) {
		return ResponseEntity.ok(this.roomService.getAllPageable(pageNo, pageSize));
	}

	@GetMapping("/getAllPageableAndSortedByName")
	public ResponseEntity<?> getAllPageableAndSortedByName(@RequestParam int pageNo, @RequestParam int pageSize) {
		return ResponseEntity.ok(this.roomService.getAllPageableAndSortedByName(pageNo, pageSize));
	}
}
