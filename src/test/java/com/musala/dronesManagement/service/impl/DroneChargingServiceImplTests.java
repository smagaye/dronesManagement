package com.musala.dronesManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.musala.dronesManagement.bo.Drone;
import com.musala.dronesManagement.bo.DroneCharging;
import com.musala.dronesManagement.dto.DroneChargingDTO;
import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.enums.Model;
import com.musala.dronesManagement.enums.State;
import com.musala.dronesManagement.repositories.IDroneChargingItemRepository;
import com.musala.dronesManagement.repositories.IDroneChargingRepository;
import com.musala.dronesManagement.services.IDroneService;
import com.musala.dronesManagement.services.impl.DroneChargingServiceImpl;

@DisplayName("Test Class DroneService")
@ExtendWith(MockitoExtension.class)
class DroneChargingServiceImplTests {

    @Mock
    private IDroneChargingRepository droneChargingRepo;

    @Mock
    private IDroneChargingItemRepository droneChargingItemRepo;

    @Mock
    private IDroneService droneService;

    @Spy
    private ModelMapper modelMapper;
    
    @InjectMocks
    private DroneChargingServiceImpl droneChargingService;

    private static DroneChargingDTO droneChargingDTO;

    private static DroneDTO drone1;
    
    private static Drone drone;
    
    private static DroneCharging droneCharging;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        drone = new Drone();
        drone.setId(10);
        drone.setBattery(90);
        drone.setModel(Model.Cruiserweight);
        drone.setSerialNumber("AQFG782u9053");
        drone.setWeight(150);
        drone.setState(State.LOADING);
        
        droneCharging = new DroneCharging();
        droneCharging.setId(1);
        droneCharging.setDrone(drone);
        droneCharging.setCreatedAt(new Date());
        droneCharging.setUpdatedAt(new Date());
        
        
        drone1 = new DroneDTO();
        drone1.setId(10);
        drone1.setBattery(90.0);
        drone1.setModel(Model.Cruiserweight);
        drone1.setSerialNumber("AQFG782u9053");
        drone1.setWeight(new BigDecimal(150));
        drone1.setState(State.LOADING);


        droneChargingDTO = new DroneChargingDTO();
        droneChargingDTO.setId(1);
        droneChargingDTO.setDrone(drone1);
        droneChargingDTO.setCreatedAt(new Date());
        droneChargingDTO.setUpdatedAt(new Date());

    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testCreateDroneCharging() throws Exception {

        when(droneChargingRepo.save(droneCharging)).thenReturn(droneCharging);
        when(modelMapper.map(droneChargingDTO, DroneCharging.class)).thenReturn(droneCharging);
        when(modelMapper.map(droneCharging, DroneChargingDTO.class)).thenReturn(droneChargingDTO);
        
        Optional<DroneChargingDTO> droneChargingDTOsaved = droneChargingService.create(droneChargingDTO);
        
        assertTrue(droneChargingDTOsaved.isPresent());
        assertNotNull(droneChargingDTOsaved.get());

    }

    @Test
    void testGetDroneByState() throws Exception {

    }

}
