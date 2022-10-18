package com.musala.dronesManagement.dto;

import java.io.Serializable;

import com.musala.dronesManagement.enums.Model;
import com.musala.dronesManagement.enums.State;

public class DroneDTO implements Serializable {

	private static final long serialVersionUID = 8424997795042252781L;

	private long id;

	private String serialNumber;

	private Model model;

	private double weight;

	private int battery;

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

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
