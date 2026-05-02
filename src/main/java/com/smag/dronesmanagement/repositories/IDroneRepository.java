package com.smag.dronesmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smag.dronesmanagement.bo.Drone;
import com.smag.dronesmanagement.enums.State;

@Repository
public interface IDroneRepository extends JpaRepository<Drone, Long>{

	List<Drone> findByState(State state);
	
	List<Drone> findByBatteryLessThan(double level);

}
