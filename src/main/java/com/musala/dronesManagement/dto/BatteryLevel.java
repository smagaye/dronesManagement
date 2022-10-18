package com.musala.dronesManagement.dto;

public class BatteryLevel {

	public BatteryLevel(String level) {
		super();
		this.level = level;
	}

	public BatteryLevel(long level) {
		super();
		this.level = Long.toString(level).concat("%");
	}

	private String level;

	public String getLevel() {
		return level;
	}

}
