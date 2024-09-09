package com.project.roomBookingSystem.controller.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.roomBookingSystem.service.customer.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/room/{pageNumber}")
    public ResponseEntity<?> getAllAvailableRooms(@PathVariable int pageNumber) {
        return ResponseEntity.ok(roomService.getAllAvailableRooms(pageNumber));
    }
}
