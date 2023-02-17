package com.example.tourismAgency.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tourismAgency.entities.concretes.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	List<Room> getByHotel_id(int id);
}
