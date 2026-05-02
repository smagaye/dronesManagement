package com.smag.dronesmanagement.controllers.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.smag.core.AnnotationTest.IntegrationTest;
import com.smag.dronesmanagement.dto.CreateChargingItemDTO;
import com.smag.dronesmanagement.dto.DroneChargingDTO;
import com.smag.dronesmanagement.dto.DroneDTO;
import com.smag.dronesmanagement.dto.MedicationDTO;
import com.smag.dronesmanagement.enums.ChargingState;
import com.smag.dronesmanagement.enums.Model;
import com.smag.dronesmanagement.enums.State;

@DisplayName("IT Test Class DroneController")
@IntegrationTest
class DroneControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static DroneDTO drone1;
    private static DroneDTO drone2;
    private static DroneChargingDTO droneChargingDTO;

    private static CreateChargingItemDTO item1;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        drone1 = new DroneDTO();
        drone1.setId(1);
        drone1.setBattery(100.0);
        drone1.setModel(Model.Lightweight);
        drone1.setSerialNumber("GR89285i39000");
        drone1.setWeight(new BigDecimal(500.0));
        drone1.setState(State.LOADING);

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
        droneChargingDTO.setState(ChargingState.LOADING);

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
    
    @Test
    void testGetDroneByState() throws Exception {
        mockMvc.perform(get("/drones?state=LOADING"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serialNumber", is(drone1.getSerialNumber())))
                .andExpect(jsonPath("$[0].model", is(drone1.getModel().toString())))
                .andExpect(jsonPath("$[0].battery", is(drone1.getBattery())))
                .andExpect(jsonPath("$[0].weight", is(drone1.getWeight().doubleValue())))
                .andExpect(jsonPath("$[0].state", is(drone1.getState().toString())));
    }

}
