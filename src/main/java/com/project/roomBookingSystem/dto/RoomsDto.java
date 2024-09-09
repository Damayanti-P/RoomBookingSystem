package com.project.roomBookingSystem.dto;

import lombok.Data;

@Data
public class RoomsDto {


    private int roomId;

    private String name;

    private String type;

    private int price;

    private boolean available;
}

