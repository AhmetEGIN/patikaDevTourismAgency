package com.example.tourismAgency.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	Book getByRoomIdAndIsActive(int id, boolean status);
	List<Book> getBookByCustomer_Id(int id);
	List<Book> getByRoomIdAndIsActiveAndStartDateAndEndDate(int id, boolean status, LocalDate startDate, LocalDate endDate);
	
}
