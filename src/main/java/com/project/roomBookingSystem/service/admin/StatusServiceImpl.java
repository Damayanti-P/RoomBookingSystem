package com.project.roomBookingSystem.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.roomBookingSystem.entity.Status;
import com.project.roomBookingSystem.repository.RoomRepo;
import com.project.roomBookingSystem.repository.StatusRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService{

		@Autowired
		StatusRepo sRepo;
		
		@Override
		public Status saveStatus(Status statusData) 
		{
			Status  status=sRepo.save(statusData);
			return status;
		}
		
		@Override
		public Status getStatusById(int id) {
			Status status=sRepo.findById(id).get();
			return status;
		}
		
		@Override
		public List<Status> getAll()
		{
			List<Status> allStatus=sRepo.getAll();
			return allStatus;
		}

		@Override
		public void deleteStatus(int id)
		{
			sRepo.deleteById(id);
		}
		
		@Override
		public void updateStatus(Status statusNew,int id)
		{
			Status status=getStatusById(id);
			statusNew.setStatusId(status.getStatusId());
			saveStatus(statusNew);
		}
		
		

}
