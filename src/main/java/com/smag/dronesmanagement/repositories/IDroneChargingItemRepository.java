package com.smag.dronesmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smag.dronesmanagement.bo.DroneChargingItem;

@Repository
public interface IDroneChargingItemRepository extends JpaRepository<DroneChargingItem, Long>{

	List<DroneChargingItem> findByDroneChargingId(long id);

}
