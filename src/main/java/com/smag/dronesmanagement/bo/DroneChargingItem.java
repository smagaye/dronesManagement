package com.smag.dronesmanagement.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DroneChargingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "drone_charging_id")
	DroneCharging droneCharging;

	@ManyToOne
	@JoinColumn(name = "medication_id")
	private Medication medication;

	private int quantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DroneCharging getDroneCharging() {
		return droneCharging;
	}

	public void setDroneCharging(DroneCharging droneCharging) {
		this.droneCharging = droneCharging;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
