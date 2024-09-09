package com.project.roomBookingSystem.service.customer;

import com.project.roomBookingSystem.dto.ReservationDto;

public interface BookingService {

	boolean saveReservation(ReservationDto reservationDto);

}
