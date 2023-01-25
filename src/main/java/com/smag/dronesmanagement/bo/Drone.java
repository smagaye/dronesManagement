package com.smag.dronesmanagement.bo;

import com.smag.dronesmanagement.enums.Model;
import com.smag.dronesmanagement.enums.State;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Model model;

    @Column
    private double weight;

    @Column
    private double battery;

    @Column
    @Enumerated(EnumType.STRING)
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
