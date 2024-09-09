package com.project.roomBookingSystem.service.admin;

import java.util.*;

import com.project.roomBookingSystem.dto.RoomsDto;
import com.project.roomBookingSystem.dto.RoomsResponseDto;
import com.project.roomBookingSystem.entity.Rooms;
import com.project.roomBookingSystem.entity.Status;
import com.project.roomBookingSystem.repository.RoomRepo;
import com.project.roomBookingSystem.repository.StatusRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService{
	
		private final RoomRepo roomRepo;
		private final StatusRepo statusrepo;
		
		@Override
	    public  boolean addRoom(RoomsDto roomDto) {

	        try {
	            Rooms room = new Rooms();
	            Status status=statusrepo.findByStatusId(1);
	            room.setAvailable(true);
	            room.setType(roomDto.getType());
	            room.setPrice(roomDto.getPrice());
	            room.setName(roomDto.getName());
	            room.setStatus(status);
	            roomRepo.save(room);
	            return true;
	        } catch (Exception ex) {
	            return false;
	        }

	    }

	    @Override
	    public RoomsResponseDto getAllRooms(int pageNumber) {

	        List<RoomsDto> roomsList = new ArrayList<>();
	        Pageable pageable = PageRequest.of(pageNumber, 6);
	        List<Rooms> rooms = roomRepo.findAll();
	        Page<Rooms> roomsPage =  roomRepo.findAll(pageable);
	        RoomsResponseDto responseDto = new RoomsResponseDto();
	        responseDto.setPageNumber(roomsPage.getPageable().getPageNumber());
	        responseDto.setTotalPages(roomsPage.getTotalPages());

	        for (Rooms room: roomsPage) {
	        	Status status=room.getStatus();
	        	if(status.getStatusId()==1)
	            roomsList.add(room.getRoomsDto());
	        }
	        responseDto.setRoomList(roomsList);
	        return  responseDto;
	    }

	    @Override
	    public RoomsDto getRoomByID(int roomId) {
	        Optional<Rooms> room = roomRepo.findById(roomId);
	        if(room.isPresent()) {
	            return room.get().getRoomsDto();
	        } else {
	          throw new EntityNotFoundException("Room doesn't exist");
	        }

	    }

	    @Override
	    public boolean updateRoom(int id, RoomsDto roomDto) {
	        Optional<Rooms> room = roomRepo.findById(id);
	        if(room.isPresent()) {
	            Rooms existingRoom = room.get();

	            existingRoom.setName(roomDto.getName());
	            existingRoom.setType(roomDto.getType());
	            existingRoom.setPrice(roomDto.getPrice());
	            existingRoom.setAvailable(roomDto.isAvailable());
	            roomRepo.save(existingRoom);
	            return true;
	        } else {
	            throw new EntityNotFoundException("Room doesn't exist");
	        }
	    }

	    @Override
	    public boolean deleteRoom(int roomId) {
	    	Status status=statusrepo.findById(2).get();
	    	Optional<Rooms> rooms = roomRepo.findById(roomId);
	        Rooms room = roomRepo.findById(roomId).get();
	        if(rooms.isPresent()) {
	        	room.setStatus(status);
	    		roomRepo.save(room);
	            return true;
	        } else {
	            throw new EntityNotFoundException("Room doesn't exist");
	        }
	    }

}
