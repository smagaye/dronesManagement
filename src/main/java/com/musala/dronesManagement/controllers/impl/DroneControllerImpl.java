package com.musala.dronesManagement.controllers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.musala.dronesManagement.controllers.IDroneController;
import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.dto.MedicationDTO;
import com.musala.dronesManagement.enums.State;
import com.musala.dronesManagement.services.IDroneService;

@RestController
public class DroneControllerImpl implements IDroneController {

    @Autowired
    private IDroneService droneService;

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
    public ResponseEntity<?> updloadMedicationForDrone(long id, List<MedicationDTO> medications) {
        if (id > 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getMedicationByDroneId(long id) {
        Optional<DroneDTO> droneFound = droneService.findDroneById(id);

        if (droneFound.isPresent()) {
            return new ResponseEntity<>(droneFound.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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
