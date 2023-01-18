package com.musala.dronesmanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.dronesmanagement.bo.DroneCharging;

@Repository
public interface IDroneChargingRepository extends JpaRepository<DroneCharging, Long> {

    List<DroneCharging> findByDroneId(long id);

    Optional<DroneCharging> findFirstByDroneId(long droneId, Sort sort);

}
