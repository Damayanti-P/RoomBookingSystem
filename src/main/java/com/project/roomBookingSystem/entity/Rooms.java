package com.project.roomBookingSystem.entity;





import org.hibernate.annotations.Type;

import com.project.roomBookingSystem.dto.RoomsDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_rooms")
public class Rooms{
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int roomId;

	    private String name;

	    private String type;

	    private int price;

	    private boolean available;
	    
	    @ManyToOne
	    @JoinColumn(name = "status")
	    private Status status;

	    public RoomsDto getRoomsDto() {

	        RoomsDto roomDto = new RoomsDto();
	        roomDto.setRoomId(roomId);
	        roomDto.setName(name);
	        roomDto.setType(type);
	        roomDto.setPrice(price);
	        roomDto.setAvailable(available);
	        return roomDto;
	    }

	
	 
}
