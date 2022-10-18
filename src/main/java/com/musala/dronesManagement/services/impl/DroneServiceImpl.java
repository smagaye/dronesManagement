package com.musala.dronesManagement.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.dronesManagement.bo.Drone;
import com.musala.dronesManagement.dto.BatteryLevel;
import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.enums.State;
import com.musala.dronesManagement.repositories.IDroneRepository;
import com.musala.dronesManagement.services.IDroneService;

@Service
public class DroneServiceImpl implements IDroneService {

	@Autowired
	private IDroneRepository droneRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Optional<DroneDTO> create(DroneDTO droneDTO) {
		Drone droneToSave = mapper.map(droneDTO, Drone.class);

		Drone droneSaved = droneRepository.save(droneToSave);
		return Optional.of(mapper.map(droneSaved, DroneDTO.class));
	}

	@Override
	public Optional<DroneDTO> findDroneById(long id) {
		return Optional.of(mapper.map(droneRepository.findById(id), DroneDTO.class));
	}

	@Override
	public List<DroneDTO> findByState(State state) {
		List<DroneDTO> list = droneRepository.findByState(state).stream().map(a -> mapper.map(a, DroneDTO.class))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public Optional<Object> checkDroneBatteryById(long id) {
		Optional<Drone> droneOpt = droneRepository.findById(id);
		if (droneOpt.isPresent()) {
			return Optional.of(new BatteryLevel(droneOpt.get().getBattery()));
		}
		return Optional.empty();
	}

}
