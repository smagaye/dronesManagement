package com.musala.dronesmanagement.controllers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.musala.dronesmanagement.controllers.IDroneController;
import com.musala.dronesmanagement.dto.CreateChargingItemDTO;
import com.musala.dronesmanagement.dto.DroneDTO;
import com.musala.dronesmanagement.dto.MedicationDTO;
import com.musala.dronesmanagement.enums.State;
import com.musala.dronesmanagement.services.IDroneChargingService;
import com.musala.dronesmanagement.services.IDroneService;

@RestController
public class DroneControllerImpl implements IDroneController {

    @Autowired
    private IDroneService droneService;

    @Autowired
    private IDroneChargingService droneChargingService;

    @Override
    public ResponseEntity<?> register(DroneDTO droneDTO) {
        if (droneDTO.getId() > 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<DroneDTO> droneSaved = droneService.create(droneDTO);

        if (droneSaved.isPresent()) {
            return new ResponseEntity<>(droneSaved, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<?> updloadMedicationForDrone(long id, List<CreateChargingItemDTO> droneChargingItems) {
        return new ResponseEntity<>(droneChargingService.uploadForDroneMedication(id, droneChargingItems),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getMedicationsByDroneId(long id) {
        List<MedicationDTO> medications = droneChargingService.getMedicationsByDroneId(id);
     return new ResponseEntity<>(medications, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getDroneByState(State state) {
        List<DroneDTO> list = droneService.findByState(state);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> checkDroneBatteryById(long id) {
        Optional<Object> response = droneService.checkDroneBatteryById(id);

        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
