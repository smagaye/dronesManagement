package com.musala.dronesManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.dronesManagement.bo.Drone;
import com.musala.dronesManagement.enums.State;

@Repository
public interface IDroneRepository extends JpaRepository<Drone, Long>{

	List<Drone> findByState(State state);
	
	List<Drone> findByBatteryLessThan(double level);

}
