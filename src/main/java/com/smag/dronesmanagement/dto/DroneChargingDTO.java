package com.smag.dronesmanagement.dto;

import java.util.Date;

import com.smag.dronesmanagement.enums.ChargingState;

public class DroneChargingDTO {

    private long id;

    private DroneDTO drone;
    
    private ChargingState state;

    private Date createdAt;

    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DroneDTO getDrone() {
        return drone;
    }

    public void setDrone(DroneDTO drone) {
        this.drone = drone;
    }

    public ChargingState getState() {
        return state;
    }

    public void setState(ChargingState state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
