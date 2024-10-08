package com.project.roomBookingSystem.controller.admin;


import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.roomBookingSystem.service.admin.ReservationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber) {
        try {
            return ResponseEntity.ok(reservationService.getAllReservations(pageNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/reservation/{reservationId}/{status}")
    public ResponseEntity<?> updateReservationStatus(@PathVariable int reservationId, @PathVariable String status) {
        boolean success = reservationService.updateReservationStatus(reservationId, status);

        if(success) {
            return ResponseEntity.ok().build();
        } else  {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

