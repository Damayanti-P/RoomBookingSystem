package com.project.roomBookingSystem.service.admin;

import aj.org.objectweb.asm.commons.InstructionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.project.roomBookingSystem.dto.ReservationDto;
import com.project.roomBookingSystem.dto.ReservationResponseDto;
import com.project.roomBookingSystem.entity.Reservation;
import com.project.roomBookingSystem.repository.ReservationRepo;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

	private final ReservationRepo reservationRepository ;
	
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

}
