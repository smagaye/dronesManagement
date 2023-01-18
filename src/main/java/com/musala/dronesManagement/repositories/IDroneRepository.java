package com.musala.dronesmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.dronesmanagement.bo.Drone;
import com.musala.dronesmanagement.enums.State;

@Repository
public interface IDroneRepository extends JpaRepository<Drone, Long>{

	List<Drone> findByState(State state);
	
	List<Drone> findByBatteryLessThan(double level);

}
