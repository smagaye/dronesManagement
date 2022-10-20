package com.musala.dronesManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.dronesManagement.bo.DroneCharging;

@Repository
public interface IDroneChargingRepository extends JpaRepository<DroneCharging, Long>{

	List<DroneCharging> findByDroneId(long id);

}
