package com.project.roomBookingSystem.dto;

import lombok.Data;

import java.time.LocalDate;

import com.project.roomBookingSystem.enums.ReservationStatus;

@Data
public class ReservationDto {

    private int id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int price;

    private ReservationStatus reservationStatus;

    private int roomId;

    private String roomType;

    private String roomName;

    private int userId;

    private String userName;
}
