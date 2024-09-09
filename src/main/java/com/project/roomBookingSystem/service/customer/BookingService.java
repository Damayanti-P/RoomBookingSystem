package com.project.roomBookingSystem.service.customer;

import com.project.roomBookingSystem.dto.ReservationDto;
import com.project.roomBookingSystem.dto.ReservationResponseDto;

public interface BookingService {

	boolean saveReservation(ReservationDto reservationDto);

	ReservationResponseDto getAllReservationsByUserId(int userId, int pageNumber);

}
