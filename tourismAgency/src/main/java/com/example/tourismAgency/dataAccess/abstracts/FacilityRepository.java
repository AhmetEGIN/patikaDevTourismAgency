package com.example.tourismAgency.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {
	
	boolean existsFacilityByNameEquals(String name);
	
	List<Facility> getByHotel_id(int id);
}
