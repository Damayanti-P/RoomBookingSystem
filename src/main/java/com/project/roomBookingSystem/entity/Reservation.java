package com.project.roomBookingSystem.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.project.roomBookingSystem.dto.ReservationDto;
import com.project.roomBookingSystem.enums.ReservationStatus;

import java.time.LocalDate;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int price;

    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rooms rooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public ReservationDto getReservationDto() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setPrice(price);
        reservationDto.setCheckInDate(checkInDate);
        reservationDto.setCheckOutDate(checkOutDate);
        reservationDto.setReservationStatus(reservationStatus);
        reservationDto.setUserId(user.getId());
        reservationDto.setRoomId(rooms.getRoomId());

        reservationDto.setRoomType(rooms.getType());
        reservationDto.setRoomName(rooms.getName());
        reservationDto.setUserName(user.getUsername());

        return reservationDto;
    }

}
