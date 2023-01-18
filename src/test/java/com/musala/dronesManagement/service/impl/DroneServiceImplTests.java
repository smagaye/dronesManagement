package com.musala.dronesmanagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
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

import com.musala.dronesmanagement.bo.Drone;
import com.musala.dronesmanagement.dto.DroneDTO;
import com.musala.dronesmanagement.enums.Model;
import com.musala.dronesmanagement.enums.State;
import com.musala.dronesmanagement.repositories.IDroneRepository;
import com.musala.dronesmanagement.services.impl.DroneServiceImpl;

@DisplayName("Test Class DroneService")
@ExtendWith(MockitoExtension.class)
class DroneServiceImplTests {

    @Mock
    private IDroneRepository droneRepository;

    @InjectMocks
    private DroneServiceImpl droneService;

    @Spy
    private ModelMapper modelMapper;

    private static Drone drone1;

    private static DroneDTO drone2;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        drone1 = new Drone();
        drone1.setId(1);
        drone1.setBattery(50);
        drone1.setModel(Model.Lightweight);
        drone1.setSerialNumber("RFG782u9053");
        drone1.setWeight(200);

        drone2 = new DroneDTO();
        drone2.setId(10);
        drone2.setBattery(90.0);
        drone2.setModel(Model.Cruiserweight);
        drone2.setSerialNumber("AQFG782u9053");
        drone2.setWeight(new BigDecimal(150));
        drone2.setState(State.IDLE);

    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testDroneRegister() throws Exception {

        DroneDTO droneToSave = new DroneDTO();
        droneToSave.setId(0);
        droneToSave.setBattery(50.0);
        droneToSave.setModel(Model.Lightweight);
        droneToSave.setSerialNumber("PJG782u9053");
        droneToSave.setWeight(new BigDecimal(500));
        droneToSave.setState(State.LOADING);

        lenient().when(modelMapper.map(droneToSave, Drone.class)).thenReturn(drone1);
        lenient().when(droneRepository.save(drone1)).thenReturn(drone1);
        lenient().when(modelMapper.map(drone1, DroneDTO.class)).thenReturn(droneToSave);

        Optional<DroneDTO> droneSaved = droneService.create(droneToSave);

        assertFalse(droneSaved.isEmpty());
        assertNotNull(droneSaved.get());

        verify(droneRepository, times(1)).save(drone1);
        verifyNoMoreInteractions(droneRepository);
    }

    @Test
    void testGetDroneByState() throws Exception {

        when(droneRepository.findByState(State.LOADING)).thenReturn(Arrays.asList(drone1));

        List<DroneDTO> dronesbyState = droneService.findByState(State.LOADING);

        assertEquals(1, dronesbyState.size());

        verify(droneRepository, times(1)).findByState(State.LOADING);
        verifyNoMoreInteractions(droneRepository);
    }

    @Test
    void testGetBatteryLevelforDroneById() throws Exception {

        when(droneRepository.findById(drone1.getId()))
                .thenReturn(Optional.of(drone1));

        Optional<Object> resp = droneService.checkDroneBatteryById(drone1.getId());

        assertTrue(resp.isPresent());

        verify(droneRepository, times(1)).findById(drone1.getId());
        verifyNoMoreInteractions(droneRepository);
    }

    @Test
    void testGetBatteryLevelforDroneWithInvalidId() throws Exception {

        when(droneRepository.findById(drone1.getId()))
                .thenReturn(Optional.empty());

        Optional<Object> resp = droneService.checkDroneBatteryById(drone1.getId());

        assertTrue(resp.isEmpty());


        verify(droneRepository, times(1)).findById(drone1.getId());
        verifyNoMoreInteractions(droneRepository);
    }

    @Test
    void testDroneById() throws Exception {

        when(droneRepository.findById(drone1.getId()))
                .thenReturn(Optional.of(drone1));

        Optional<DroneDTO> droneFound = droneService.findDroneById(drone1.getId());

        assertTrue(droneFound.isPresent());
        assertNotNull(droneFound.get());

        verify(droneRepository, times(1)).findById(drone1.getId());
        verifyNoMoreInteractions(droneRepository);
    }

}
