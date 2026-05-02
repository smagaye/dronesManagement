package com.smag.dronesmanagement.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smag.dronesmanagement.bo.DroneCharging;
import com.smag.dronesmanagement.bo.DroneChargingItem;
import com.smag.dronesmanagement.dto.CreateChargingItemDTO;
import com.smag.dronesmanagement.dto.DroneChargingDTO;
import com.smag.dronesmanagement.dto.DroneChargingItemDTO;
import com.smag.dronesmanagement.dto.DroneDTO;
import com.smag.dronesmanagement.dto.MedicationDTO;
import com.smag.dronesmanagement.enums.ChargingState;
import com.smag.dronesmanagement.enums.State;
import com.smag.dronesmanagement.repositories.IDroneChargingItemRepository;
import com.smag.dronesmanagement.repositories.IDroneChargingRepository;
import com.smag.dronesmanagement.services.IDroneChargingService;
import com.smag.dronesmanagement.services.IDroneService;

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
        droneChargingToSave.setState(ChargingState.LOADING);

        final DroneChargingDTO droneChargingSaved = create(droneChargingToSave).get();

        items.stream()
                .map(old -> droneChargingItemRepo.save(modelMapper.map(
                        new DroneChargingItemDTO(droneChargingSaved, old.getMedication(), old.getQuantity()),
                        DroneChargingItem.class)))
                .collect(Collectors.toList());

        return Optional.of(droneChargingSaved);
    }

    @Override
    public List<MedicationDTO> getMedicationsByDroneId(long droneId) {
        // Retrieve last droneCharging by id
        Optional<DroneCharging> lastCharging = droneChargingRepo.findFirstByDroneId(droneId,
                Sort.by(Sort.Direction.DESC, "id"));
        if (lastCharging.isPresent()) {
            // Get medication for last droneCharging
            List<MedicationDTO> list = droneChargingItemRepo.findByDroneChargingId(lastCharging.get().getId()).stream()
                    .map(item -> modelMapper.map(item.getMedication(), MedicationDTO.class))
                    .collect(Collectors.toList());

            return list;
        }
        return null;
    }

}
