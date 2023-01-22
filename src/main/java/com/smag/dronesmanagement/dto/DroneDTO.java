package com.smag.dronesmanagement.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.smag.dronesmanagement.enums.Model;
import com.smag.dronesmanagement.enums.State;

public class DroneDTO implements Serializable {

    private static final long serialVersionUID = 8424997795042252781L;

    private long id;

    @NotEmpty
    @Size(max = 100, message = "max characters 100")
    private String serialNumber;

    private Model model;

    @Max(500)
    private BigDecimal weight;

    @Max(100)
    private Double battery;

    private State state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

   

}
