package com.musala.dronesManagement.services;

import java.util.List;
import java.util.Optional;

import com.musala.dronesManagement.dto.CreateChargingItemDTO;
import com.musala.dronesManagement.dto.DroneChargingDTO;

public interface IDroneChargingService {

	public Optional<DroneChargingDTO> create(DroneChargingDTO droneChargingDTO);

    public Optional<DroneChargingDTO>  uploadForDroneMedication(long id, List<CreateChargingItemDTO> items);



}
