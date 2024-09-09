package com.project.roomBookingSystem.controller.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.roomBookingSystem.dto.ReservationDto;
import com.project.roomBookingSystem.service.customer.BookingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class BookingController {


    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> saveBooking(@RequestBody ReservationDto reservationDto) {
    	boolean isReserved = bookingService.saveReservation(reservationDto);

        if (isReserved) {
          return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/book/{userId}/{pageNumber}")
    public ResponseEntity<?> getAllReservationsByUserId(@PathVariable int userId, @PathVariable int pageNumber) {
        try {
            return ResponseEntity.ok(bookingService.getAllReservationsByUserId(userId, pageNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }	

}
