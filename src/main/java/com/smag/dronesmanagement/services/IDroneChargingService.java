package com.smag.dronesmanagement.services;

import java.util.List;
import java.util.Optional;

import com.smag.dronesmanagement.dto.CreateChargingItemDTO;
import com.smag.dronesmanagement.dto.DroneChargingDTO;
import com.smag.dronesmanagement.dto.MedicationDTO;

public interface IDroneChargingService {

	public Optional<DroneChargingDTO> create(DroneChargingDTO droneChargingDTO);

    public Optional<DroneChargingDTO>  uploadForDroneMedication(long id, List<CreateChargingItemDTO> items);

    public List<MedicationDTO> getMedicationsByDroneId(long droneId);



}
