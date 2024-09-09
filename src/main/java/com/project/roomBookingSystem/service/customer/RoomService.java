package com.project.roomBookingSystem.service.customer;

import com.project.roomBookingSystem.dto.RoomsResponseDto;

public interface RoomService {

	RoomsResponseDto getAllAvailableRooms(int pageNumber);

}
