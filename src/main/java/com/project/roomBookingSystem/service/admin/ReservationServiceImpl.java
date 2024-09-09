package com.project.roomBookingSystem.service.admin;

import aj.org.objectweb.asm.commons.InstructionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.roomBookingSystem.dto.ReservationDto;
import com.project.roomBookingSystem.dto.ReservationResponseDto;
import com.project.roomBookingSystem.entity.Reservation;
import com.project.roomBookingSystem.entity.Rooms;
import com.project.roomBookingSystem.enums.ReservationStatus;
import com.project.roomBookingSystem.repository.ReservationRepo;
import com.project.roomBookingSystem.repository.RoomRepo;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

	private final ReservationRepo reservationRepository ;
	
	private final RoomRepo roomRepository ;
	
    @Override
    public ReservationResponseDto getAllReservations(int pageNumber) {

        List<ReservationDto> reservationDtoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, 4);

        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);

        ReservationResponseDto responseDto = new ReservationResponseDto();

        responseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        responseDto.setTotalPages(reservationPage.getTotalPages());

        for (Reservation reservation: reservationPage) {
            reservationDtoList.add(reservation.getReservationDto());
        }
        responseDto.setReservationDtoList(reservationDtoList);
        return  responseDto;

    }

	@Override
	 public boolean updateReservationStatus(int id, String status) {

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if("APPROVED".equals(status)) {
                reservation.setReservationStatus(ReservationStatus.APPROVED);
                Rooms room = reservation.getRooms();
                room.setAvailable(false);
                roomRepository.save(room);
            } else {
                reservation.setReservationStatus(ReservationStatus.REJECTED);
            }
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

}
