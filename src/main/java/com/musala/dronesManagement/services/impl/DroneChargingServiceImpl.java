package com.musala.dronesManagement.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musala.dronesManagement.bo.DroneCharging;
import com.musala.dronesManagement.bo.DroneChargingItem;
import com.musala.dronesManagement.dto.CreateChargingItemDTO;
import com.musala.dronesManagement.dto.DroneChargingDTO;
import com.musala.dronesManagement.dto.DroneChargingItemDTO;
import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.enums.State;
import com.musala.dronesManagement.repositories.IDroneChargingItemRepository;
import com.musala.dronesManagement.repositories.IDroneChargingRepository;
import com.musala.dronesManagement.services.IDroneChargingService;
import com.musala.dronesManagement.services.IDroneService;

@Service
public class DroneChargingServiceImpl implements IDroneChargingService {

    @Autowired
    private IDroneChargingRepository droneChargingRepo;

    @Autowired
    private IDroneChargingItemRepository droneChargingItemRepo;

    @Autowired
    private IDroneService droneService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<DroneChargingDTO> create(DroneChargingDTO droneChargingDTO) {
        DroneCharging droneChargingSaved = droneChargingRepo
                .save(modelMapper.map(droneChargingDTO, DroneCharging.class));
        return Optional.of(modelMapper.map(droneChargingSaved, DroneChargingDTO.class));
    }

    @Transactional
    @Override
    public Optional<DroneChargingDTO> uploadForDroneMedication(long droneId, List<CreateChargingItemDTO> items) {
        Optional<DroneDTO> existingDrone = droneService.findDroneById(droneId);
        if (existingDrone.isEmpty()) {
            // TODO: Prevent drone doesn't exist
        }
        DroneDTO currentDrone = existingDrone.get();
        if (State.LOADING.equals(currentDrone.getState()) && currentDrone.getBattery() < 25) {
            // TODO: Prevent drone from being in LOADING state if the battery level is < 25%
        }

        Double totalWeight = items.stream().map(i -> i.getQuantity() * i.getMedication().getWeight())
                .mapToDouble(Double::doubleValue).sum();
        if (totalWeight > currentDrone.getWeight().doubleValue()) {
            // TODO: Prevent drone overflow
        }

        DroneChargingDTO droneChargingToSave = new DroneChargingDTO();
        droneChargingToSave.setDrone(currentDrone);

        final DroneChargingDTO droneChargingSaved = create(droneChargingToSave).get();

        items.stream()
                .map(old -> droneChargingItemRepo.save(modelMapper.map(
                        new DroneChargingItemDTO(droneChargingSaved, old.getMedication(), old.getQuantity()),
                        DroneChargingItem.class)))
                .collect(Collectors.toList());

        return Optional.of(droneChargingSaved);
    }

}
