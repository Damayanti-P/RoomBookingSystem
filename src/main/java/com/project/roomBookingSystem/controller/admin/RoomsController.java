package com.project.roomBookingSystem.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.roomBookingSystem.dto.RoomsDto;
import com.project.roomBookingSystem.dto.RoomsResponseDto;
import com.project.roomBookingSystem.service.admin.RoomsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class RoomsController {
	
	private final RoomsService service;
	
	 @PostMapping("/room/add-room")
	    public ResponseEntity addRoom(@RequestBody RoomsDto roomDto) {
	        boolean isAdded = service.addRoom(roomDto);
	        if(isAdded) {
	            return ResponseEntity.status(HttpStatus.CREATED).build();
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }

	    }


	    @GetMapping("/rooms/{pageNumber}")
	    public ResponseEntity getAllRooms(@PathVariable int pageNumber) {
	        RoomsResponseDto roomsList = service.getAllRooms(pageNumber);
	        return new ResponseEntity(roomsList, HttpStatus.OK);
	    }

	    @GetMapping("/room/{roomId}")
	    public ResponseEntity getRoomById(@PathVariable int roomId) {
	        RoomsDto room = service.getRoomByID(roomId);
	        return new ResponseEntity(room, HttpStatus.OK);
	    }

	    @PutMapping("/room/{roomId}")
	    public ResponseEntity updateRoom(@PathVariable int roomId, @RequestBody RoomsDto roomDto) {
	        boolean response = service.updateRoom(roomId, roomDto);
	        if(response) {
	            return ResponseEntity.status(HttpStatus.OK).build();
	        } else  {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }

	    @DeleteMapping("/room/{roomId}")
	    public ResponseEntity deleteRoom(@PathVariable int roomId) {
	        boolean response = service.deleteRoom(roomId);
	        if(response) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } else  {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }

}
