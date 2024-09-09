package com.project.roomBookingSystem.service.admin;

import java.util.List;

import com.project.roomBookingSystem.entity.Status;

public interface StatusService {

	Status saveStatus(Status statusData);

	void updateStatus(Status statusNew, int id);

	void deleteStatus(int id);

	List<Status> getAll();

	Status getStatusById(int id);

}
