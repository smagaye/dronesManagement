package com.musala.dronesManagement.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
