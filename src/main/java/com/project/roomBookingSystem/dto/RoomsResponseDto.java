package com.project.roomBookingSystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class RoomsResponseDto {
	
	  private List<RoomsDto> roomList;

	    private Integer totalPages;

	    private Integer pageNumber;


}
