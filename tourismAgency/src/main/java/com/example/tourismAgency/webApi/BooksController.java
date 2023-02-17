package com.example.tourismAgency.webApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tourismAgency.business.abstracts.BookService;
import com.example.tourismAgency.business.requests.bookRequests.CreateBookRequest;

@RestController
@RequestMapping("/api/book")
public class BooksController {

	private BookService bookService;

	@Autowired
	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody CreateBookRequest bookRequest) {
		return ResponseEntity.ok(this.bookService.add(bookRequest));
	}

	@GetMapping("/deleteById")
	public ResponseEntity<?> deleteById(@RequestParam int id) {
		return ResponseEntity.ok(this.bookService.deleteById(id));

	}


	@GetMapping("/getById")
	public ResponseEntity<?> getBookById(@RequestParam int id) {
		return ResponseEntity.ok(this.bookService.getBookById(id));

	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.bookService.getAll());

	}

	@PostMapping("/getPrice")
	public ResponseEntity<?> getPrice(@RequestBody CreateBookRequest bookRequest) {
		return ResponseEntity.ok(this.bookService.getPrice(bookRequest));

	}

	@GetMapping("/checkOut")
	public ResponseEntity<?> checkOut(@RequestParam int roomId) {
		return ResponseEntity.ok(this.bookService.checkOut(roomId));

	}
}
