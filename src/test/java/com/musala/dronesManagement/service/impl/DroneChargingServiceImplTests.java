package com.musala.dronesManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.data.domain.Sort;

import com.musala.dronesManagement.bo.Drone;
import com.musala.dronesManagement.bo.DroneCharging;
import com.musala.dronesManagement.bo.DroneChargingItem;
import com.musala.dronesManagement.bo.Medication;
import com.musala.dronesManagement.dto.CreateChargingItemDTO;
import com.musala.dronesManagement.dto.DroneChargingDTO;
import com.musala.dronesManagement.dto.DroneChargingItemDTO;
import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.dto.MedicationDTO;
import com.musala.dronesManagement.enums.Model;
import com.musala.dronesManagement.enums.State;
import com.musala.dronesManagement.repositories.IDroneChargingItemRepository;
import com.musala.dronesManagement.repositories.IDroneChargingRepository;
import com.musala.dronesManagement.services.IDroneChargingService;
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

    @Mock
    private IDroneChargingService iDroneChargingService;

    @InjectMocks
    private DroneChargingServiceImpl droneChargingService;

    private static DroneChargingDTO droneChargingDTO;
    private static DroneDTO drone1;
    private static Drone drone;
    private static DroneCharging droneCharging;

    private static CreateChargingItemDTO item1;
    private static DroneChargingItem droneChargingItem;
    private static DroneChargingItemDTO droneChargingItemDTO;
    private static List<DroneChargingItem> droneChargingItems;

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

        MedicationDTO medication1 = new MedicationDTO();

        medication1.setCode("HBDG8080000");
        medication1.setId(2);
        medication1.setImage("https://api.v2/images/HBDG8080000.png");
        medication1.setName("Paracetamol");
        medication1.setWeight(23);

        Medication medication = new Medication();

        medication.setCode("PSFG8080001");
        medication.setId(3);
        medication.setImage("https://api.v2/images/PSFG8080001.png");
        medication.setName("CLOMID");
        medication.setWeight(45);

        item1 = new CreateChargingItemDTO();
        item1.setQuantity(1);
        item1.setMedication(medication1);

        droneChargingItem = new DroneChargingItem();
        droneChargingItem.setQuantity(2);
        droneChargingItem.setMedication(medication);

        MedicationDTO medicationDTO = new MedicationDTO();

        medicationDTO.setCode("PSFG8080001");
        medicationDTO.setId(3);
        medicationDTO.setImage("https://api.v2/images/PSFG8080001.png");
        medicationDTO.setName("CLOMID");
        medicationDTO.setWeight(45);

        droneChargingItemDTO = new DroneChargingItemDTO();
        droneChargingItemDTO.setId(1);
        droneChargingItemDTO.setQuantity(2);
        droneChargingItemDTO.setMedication(medicationDTO);
        droneChargingItemDTO.setDroneCharging(droneChargingDTO);

        droneChargingItems = Arrays.asList(droneChargingItem);
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

        verify(droneChargingRepo, times(1)).save(droneCharging);
        verifyNoMoreInteractions(droneChargingRepo);

    }

    @Test
    void testUploadForDroneMedications() throws Exception {

        when(droneService.findDroneById(drone.getId())).thenReturn(Optional.of(drone1));
        when(droneChargingRepo.save(any(DroneCharging.class))).thenReturn(droneCharging);
        when(droneChargingItemRepo.save(any(DroneChargingItem.class))).thenReturn(droneChargingItem);

        Optional<DroneChargingDTO> response = droneChargingService.uploadForDroneMedication(drone.getId(),
                Arrays.asList(item1));

        assertTrue(response.isPresent());

    }

    @Test
    void testGetMedicationsByDroneId() {
        when(droneChargingRepo.findFirstByDroneId(drone.getId(), Sort.by(Sort.Direction.DESC, "id")))
                .thenReturn(Optional.of(droneCharging));

        when(droneChargingItemRepo.findByDroneChargingId(droneCharging.getId()))
                .thenReturn(droneChargingItems);

        List<MedicationDTO> medications = droneChargingService.getMedicationsByDroneId(drone.getId());

        assertEquals(1, medications.size());

    }

}
