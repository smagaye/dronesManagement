package com.musala.dronesManagement.controllers.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.musala.dronesManagement.dto.BatteryLevel;
import com.musala.dronesManagement.dto.CreateChargingItemDTO;
import com.musala.dronesManagement.dto.DroneChargingDTO;
import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.dto.MedicationDTO;
import com.musala.dronesManagement.enums.Model;
import com.musala.dronesManagement.enums.State;
import com.musala.dronesManagement.helper.Formatter;
import com.musala.dronesManagement.services.IDroneChargingService;
import com.musala.dronesManagement.services.IDroneService;

@DisplayName("Test Class DroneController")
@ExtendWith(MockitoExtension.class)
class DroneControllerImplTests {

    private MockMvc mockMvc;

    @Mock
    private IDroneService droneService;

    @Mock
    private IDroneChargingService droneChargingService;

    @InjectMocks
    private DroneControllerImpl droneController;

    private static DroneDTO drone1;
    private static DroneDTO drone2;
    private static DroneChargingDTO droneChargingDTO;

    private static CreateChargingItemDTO item1;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        drone1 = new DroneDTO();
        drone1.setId(1);
        drone1.setBattery(50.0);
        drone1.setModel(Model.Lightweight);
        drone1.setSerialNumber("RFG782u9053");
        drone1.setWeight(new BigDecimal(200));

        drone2 = new DroneDTO();
        drone2.setId(10);
        drone2.setBattery(90.0);
        drone2.setModel(Model.Cruiserweight);
        drone2.setSerialNumber("AQFG782u9053");
        drone2.setWeight(new BigDecimal(150));
        drone2.setState(State.IDLE);

        droneChargingDTO = new DroneChargingDTO();
        droneChargingDTO.setId(1);
        droneChargingDTO.setDrone(drone1);
        droneChargingDTO.setCreatedAt(new Date());
        droneChargingDTO.setUpdatedAt(new Date());

        MedicationDTO medicationDTO = new MedicationDTO();

        medicationDTO.setCode("PSFG8080001");
        medicationDTO.setId(3);
        medicationDTO.setImage("https://api.v2/images/PSFG8080001.png");
        medicationDTO.setName("CLOMID");
        medicationDTO.setWeight(45);

        item1 = new CreateChargingItemDTO();
        item1.setQuantity(1);
        item1.setMedication(medicationDTO);

    }

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(droneController)
                .build();
    }

    @Test
    void testDroneRegister() throws Exception {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setId(0);
        droneDTO.setBattery(90.0);
        droneDTO.setModel(Model.Cruiserweight);
        droneDTO.setSerialNumber("AQFG782U9053");
        droneDTO.setWeight(new BigDecimal(150));
        droneDTO.setState(State.LOADING);

        when(droneService.create(any(DroneDTO.class))).thenReturn(Optional.of(droneDTO));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/drones/register")
                .content(Formatter.asJsonString(droneDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.serialNumber").value(droneDTO.getSerialNumber()));
        verify(droneService, times(1)).create(any(DroneDTO.class));
        verifyNoMoreInteractions(droneService);
    }

    @Test
    void testDroneRegisterWithInvalidId() throws Exception {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setId(10);
        droneDTO.setBattery(90.0);
        droneDTO.setModel(Model.Cruiserweight);
        droneDTO.setSerialNumber("AQFG782U9053");
        droneDTO.setWeight(new BigDecimal(150));
        droneDTO.setState(State.LOADING);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/drones/register")
                .content(Formatter.asJsonString(droneDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());
        verify(droneService, times(0)).create(any(DroneDTO.class));
        verifyNoMoreInteractions(droneService);
    }
    
    @Test
    void testDroneRegisterWithInvalidBatteryValue() throws Exception {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setId(0);
        droneDTO.setBattery(900.0);
        droneDTO.setModel(Model.Cruiserweight);
        droneDTO.setSerialNumber("AQFG782U9053");
        droneDTO.setWeight(new BigDecimal(150));
        droneDTO.setState(State.LOADING);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/drones/register")
                .content(Formatter.asJsonString(droneDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());

    }

    @Test
    void testDroneRegisterWithUnSavedDrone() throws Exception {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setId(0);
        droneDTO.setBattery(90.0);
        droneDTO.setModel(Model.Cruiserweight);
        droneDTO.setSerialNumber("AQFG782U9053");
        droneDTO.setWeight(new BigDecimal(150));
        droneDTO.setState(State.LOADING);

        when(droneService.create(any(DroneDTO.class))).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/drones/register")
                .content(Formatter.asJsonString(droneDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest());
        verify(droneService, times(1)).create(any(DroneDTO.class));
        verifyNoMoreInteractions(droneService);
    }

    @Test
    void testGetDroneByState() throws Exception {
        List<DroneDTO> drones = Arrays.asList(drone1, drone2);
        when(droneService.findByState(State.LOADING)).thenReturn(drones);
        mockMvc.perform(get("/drones?state=LOADING"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serialNumber", is(drone1.getSerialNumber())))
                .andExpect(jsonPath("$[0].model", is(drone1.getModel().toString())))
                .andExpect(jsonPath("$[0].battery", is(drone1.getBattery())))
                .andExpect(jsonPath("$[0].weight", is(drone1.getWeight().intValue())))
                .andExpect(jsonPath("$[0].state", is(drone1.getState())));

        verify(droneService, times(1)).findByState(State.LOADING);
        verifyNoMoreInteractions(droneService);
    }

    @Test
    void testGetBatteryLevelforDroneByUsingInvalidId() throws Exception {

        when(droneService.checkDroneBatteryById(drone1.getId()))
                .thenReturn(Optional.empty());
        mockMvc.perform(get("/drones/{id}/battery", drone1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(droneService, times(1)).checkDroneBatteryById(drone1.getId());
        verifyNoMoreInteractions(droneService);
    }

    @Test
    void testGetBatteryLevelforDroneById() throws Exception {

        when(droneService.checkDroneBatteryById(drone1.getId()))
                .thenReturn(Optional.of(new BatteryLevel(drone1.getBattery().longValue())));
        mockMvc.perform(get("/drones/{id}/battery", drone1.getId()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.level").value(drone1.getBattery().longValue() + "%"));

        verify(droneService, times(1)).checkDroneBatteryById(drone1.getId());
        verifyNoMoreInteractions(droneService);
    }

    @Test
    void testUpdloadMedicationForDrone() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/drones/medications/updload/{id}", drone1.getId())
                .content(Formatter.asJsonString(Arrays.asList(item1)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

}
