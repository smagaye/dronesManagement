package com.musala.dronesmanagement.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.dronesmanagement.bo.Drone;
import com.musala.dronesmanagement.dto.BatteryLevel;
import com.musala.dronesmanagement.dto.DroneDTO;
import com.musala.dronesmanagement.enums.State;
import com.musala.dronesmanagement.repositories.IDroneRepository;
import com.musala.dronesmanagement.services.IDroneService;

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
		return Optional.of(mapper.map(droneRepository.findById(id).get(), DroneDTO.class));
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

    @Override
    public List<DroneDTO> getDroneBatteryLessThan(double percent) {
        List<DroneDTO> list = droneRepository.findByBatteryLessThan(percent).stream().map(a -> mapper.map(a, DroneDTO.class))
                .collect(Collectors.toList());
        return list;
    }

}
