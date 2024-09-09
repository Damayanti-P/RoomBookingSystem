package com.project.roomBookingSystem.controller.admin;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.roomBookingSystem.entity.Status;
import com.project.roomBookingSystem.service.admin.StatusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/status")
public class StatusController 
{
	@Autowired
	StatusService service;
	
	@PostMapping("/status")
	public ResponseEntity saveStatus(@RequestBody Status statusData)
	{
		Status status=service.saveStatus(statusData);
		return new ResponseEntity<>(status.getStatusId(),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getById")
	public  ResponseEntity<String> getStatusById(@Nullable @RequestParam String id)
	{
		Status status=service.getStatusById(Integer.valueOf(id));
		return new ResponseEntity<>(status.toString(),HttpStatus.OK);
		
	}
	
	@GetMapping("/statusList")
	public ResponseEntity<String> getAll()
	{
		List<Status> statusAll=service.getAll();
		return new ResponseEntity<>(statusAll.toString(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteStatus/{id}")
	public ResponseEntity<String> deleteStatus(@Nullable @PathVariable String id )
	{
		service.deleteStatus(Integer.valueOf(id));
		return new ResponseEntity<>("Done",HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("updateStatus/{id}")
	public ResponseEntity<String> updateStatus(@RequestBody Status statusNew, @PathVariable String id )
	{
		service.updateStatus(statusNew, Integer.valueOf(id));
		return new ResponseEntity<>("Status Updated",HttpStatus.OK);
	}

}
