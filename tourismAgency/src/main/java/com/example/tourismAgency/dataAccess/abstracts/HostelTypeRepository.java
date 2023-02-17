package com.example.tourismAgency.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.HostelType;

public interface HostelTypeRepository extends JpaRepository<HostelType, Integer> {
//	boolean exitstHostelTypeByIdEquals(int id);
	boolean existsHostelTypeByNameContainingIgnoreCase(String name);
}
