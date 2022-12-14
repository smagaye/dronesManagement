package com.musala.dronesManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.dronesManagement.bo.DroneChargingItem;

@Repository
public interface IDroneChargingItemRepository extends JpaRepository<DroneChargingItem, Long>{

	List<DroneChargingItem> findByDroneChargingId(long id);

}
