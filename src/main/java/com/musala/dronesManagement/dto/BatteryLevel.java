package com.musala.dronesmanagement.dto;

public class BatteryLevel {

	public BatteryLevel(String level) {
		super();
		this.level = level;
	}

	public BatteryLevel(double level) {
		super();
		this.level = Double.toString(level).concat("%");
	}

	private String level;

	public String getLevel() {
		return level;
	}

}
