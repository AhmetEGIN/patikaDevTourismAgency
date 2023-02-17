package com.example.tourismAgency.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
	boolean existsHotelByNameContainingIgnoreCase(String name);
	boolean existsHotelByEmailEquals(String email);
	
}
