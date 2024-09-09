package com.project.roomBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.roomBookingSystem.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer>{

}
