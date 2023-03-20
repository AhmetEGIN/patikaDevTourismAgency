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

import com.example.tourismAgency.business.abstracts.BookService;
import com.example.tourismAgency.business.requests.bookRequests.CreateBookRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BooksController {

	private BookService bookService;

	@PostMapping()
	public ResponseEntity<?> add(@RequestBody CreateBookRequest bookRequest) {
		
		return ResponseEntity.ok(this.bookService.add(bookRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {

		return ResponseEntity.ok(this.bookService.deleteById(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable("id") int id) {

		return ResponseEntity.ok(this.bookService.getBookById(id));
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {

		return ResponseEntity.ok(this.bookService.getAll());
	}

	@PutMapping()
	public ResponseEntity<?> getPrice(@RequestBody CreateBookRequest bookRequest) {

		return ResponseEntity.ok(this.bookService.getPrice(bookRequest));
	}

	@PutMapping("/{roomId}")
	public ResponseEntity<?> checkOut(@PathVariable("roomId") int roomId) {

		return ResponseEntity.ok(this.bookService.checkOut(roomId));
	}
}
