package com.project.roomBookingSystem.service.admin;

import com.project.roomBookingSystem.dto.ReservationResponseDto;

public interface ReservationService {

	  ReservationResponseDto getAllReservations(int pageNumber);

	boolean updateReservationStatus(int reservationId, String status);
}
