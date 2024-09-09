package com.project.roomBookingSystem.entity;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_status")
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long statusId;
	
	private String statusName;
	
	private String statusDescription;
	

}
