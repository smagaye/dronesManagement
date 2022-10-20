package com.musala.dronesManagement.services;

import java.util.List;
import java.util.Optional;

import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.enums.State;

public interface IDroneService {

	public Optional<DroneDTO> create(DroneDTO droneDTO);

	public Optional<DroneDTO> findDroneById(long id);
	
	public List<DroneDTO> findByState(State state);

	public Optional<Object> checkDroneBatteryById(long id);

}
