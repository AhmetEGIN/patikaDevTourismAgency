package com.example.tourismAgency.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
