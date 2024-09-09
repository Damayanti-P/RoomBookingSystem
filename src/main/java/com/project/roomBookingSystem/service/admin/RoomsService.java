package com.project.roomBookingSystem.service.admin;

import com.project.roomBookingSystem.dto.RoomsDto;
import com.project.roomBookingSystem.dto.RoomsResponseDto;

public interface RoomsService {

	boolean addRoom(RoomsDto roomDto);

    RoomsResponseDto getAllRooms(int pageNumber);

   RoomsDto getRoomByID(int roomId);

   boolean updateRoom(int id, RoomsDto roomDto);

   boolean deleteRoom(int roomId);

}
