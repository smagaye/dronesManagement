package com.musala.dronesManagement.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.musala.dronesManagement.dto.DroneDTO;
import com.musala.dronesManagement.services.IDroneService;

@Configuration
@EnableScheduling
public class LowBatteryTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(LowBatteryTask.class);

    @Autowired
    private IDroneService droneService;

    @Scheduled(fixedDelay = 45000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
        List<DroneDTO> list = droneService.getDroneBatteryLessThan(25);

        list.forEach(e -> LOGGER.warn("Drone {} - serial {} - Battery Low statement {}%", e.getId(), e.getSerialNumber(),
                e.getBattery().toString()));

    }

}
