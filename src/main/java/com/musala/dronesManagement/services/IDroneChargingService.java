package com.musala.dronesmanagement.services;

import java.util.List;
import java.util.Optional;

import com.musala.dronesmanagement.dto.CreateChargingItemDTO;
import com.musala.dronesmanagement.dto.DroneChargingDTO;
import com.musala.dronesmanagement.dto.MedicationDTO;

public interface IDroneChargingService {

	public Optional<DroneChargingDTO> create(DroneChargingDTO droneChargingDTO);

    public Optional<DroneChargingDTO>  uploadForDroneMedication(long id, List<CreateChargingItemDTO> items);

    public List<MedicationDTO> getMedicationsByDroneId(long droneId);



}
