package com.musala.dronesmanagement.services;

import java.util.List;
import java.util.Optional;

import com.musala.dronesmanagement.dto.DroneDTO;
import com.musala.dronesmanagement.enums.State;

public interface IDroneService {

	public Optional<DroneDTO> create(DroneDTO droneDTO);

	public Optional<DroneDTO> findDroneById(long id);
	
	public List<DroneDTO> findByState(State state);

	public Optional<Object> checkDroneBatteryById(long id);
	
	public List<DroneDTO> getDroneBatteryLessThan(double percent);

}
