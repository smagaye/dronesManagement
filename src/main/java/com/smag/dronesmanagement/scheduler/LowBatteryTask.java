package com.smag.dronesmanagement.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.smag.dronesmanagement.dto.DroneDTO;
import com.smag.dronesmanagement.services.IDroneService;

@Configuration
@EnableScheduling
public class LowBatteryTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(LowBatteryTask.class);

    private IDroneService droneService;
    
    @Autowired
    public void setDroneService(IDroneService droneService) {
        this.droneService = droneService;
    }



    @Scheduled(fixedDelay = 45000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
        List<DroneDTO> list = droneService.getDroneBatteryLessThan(25);

        list.forEach(e -> LOGGER.warn("Drone {} - serial {} - Battery Low {}%", e.getId(), e.getSerialNumber(),
                e.getBattery().toString()));

    }

}
