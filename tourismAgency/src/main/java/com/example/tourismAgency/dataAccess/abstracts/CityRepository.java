package com.example.tourismAgency.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	boolean existsCityByNameEquals(String name);
	
}
