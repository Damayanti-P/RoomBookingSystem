package com.project.roomBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.roomBookingSystem.entity.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer>{

	@Query(value="Select * from tbl_status",nativeQuery = true)
	List<Status> getAll();

	Status findByStatusId(int i);

}
