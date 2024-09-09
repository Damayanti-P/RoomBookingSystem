package com.project.roomBookingSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.roomBookingSystem.entity.Rooms;

@Repository
public interface RoomRepo extends JpaRepository<Rooms, Integer>{

	Page<Rooms> findByAvailable(boolean available, Pageable pageable);
	

}
