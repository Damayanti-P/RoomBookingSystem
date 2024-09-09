package com.project.roomBookingSystem.service.customer;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.roomBookingSystem.dto.RoomsDto;
import com.project.roomBookingSystem.dto.RoomsResponseDto;
import com.project.roomBookingSystem.entity.Rooms;
import com.project.roomBookingSystem.repository.RoomRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	    private final RoomRepo roomRepo;

	    @Override
	    public RoomsResponseDto getAllAvailableRooms(int pageNumber) {

	        List<RoomsDto> roomsList = new ArrayList<>();
	        Pageable pageable = PageRequest.of(pageNumber, 6);
	        Page<Rooms> roomsPage =  roomRepo.findByAvailable(true, pageable);
	        RoomsResponseDto responseDto = new RoomsResponseDto();
	        responseDto.setPageNumber(roomsPage.getPageable().getPageNumber());
	        responseDto.setTotalPages(roomsPage.getTotalPages());

	        for (Rooms room: roomsPage) {
	            roomsList.add(room.getRoomsDto());
	        }
	        responseDto.setRoomList(roomsList);
	        return  responseDto;
	    }

}
