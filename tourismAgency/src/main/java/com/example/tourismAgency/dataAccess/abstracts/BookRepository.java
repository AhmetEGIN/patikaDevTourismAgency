package com.example.tourismAgency.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	Book getByRoomIdAndIsActive(int id, boolean status);
	List<Book> getBookByCustomer_Id(int id);
	
}
