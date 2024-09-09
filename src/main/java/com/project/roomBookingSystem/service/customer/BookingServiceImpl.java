package com.project.roomBookingSystem.service.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.project.roomBookingSystem.dto.ReservationDto;
import com.project.roomBookingSystem.entity.Reservation;
import com.project.roomBookingSystem.entity.Rooms;
import com.project.roomBookingSystem.entity.User;
import com.project.roomBookingSystem.enums.ReservationStatus;
import com.project.roomBookingSystem.repository.ReservationRepo;
import com.project.roomBookingSystem.repository.RoomRepo;
import com.project.roomBookingSystem.repository.UserRepo;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final UserRepo userRepository;

    private final RoomRepo roomRepository;

    private final ReservationRepo reservationRepository;

    public boolean saveReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Rooms> optionalRooms = roomRepository.findById(reservationDto.getRoomId());

        if(optionalUser.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setRooms(optionalRooms.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            int days = (int) ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRooms.get().getPrice() * days);
            reservationRepository.save(reservation);
            return true;
        }
        return false;

    }
}
