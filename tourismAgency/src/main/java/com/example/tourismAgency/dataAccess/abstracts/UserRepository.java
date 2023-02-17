package com.example.tourismAgency.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
