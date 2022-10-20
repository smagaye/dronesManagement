package com.musala.dronesManagement.dto;

public class DroneChargingItemDTO {

	private long id;

	private DroneChargingDTO droneCharging;

	private MedicationDTO medication;

	private int quantity;
	
	

    public DroneChargingItemDTO(DroneChargingDTO droneCharging, MedicationDTO medication, int quantity) {
        super();
        this.droneCharging = droneCharging;
        this.medication = medication;
        this.quantity = quantity;
    }
    
    

    public DroneChargingItemDTO() {
        super();
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DroneChargingDTO getDroneCharging() {
        return droneCharging;
    }

    public void setDroneCharging(DroneChargingDTO droneCharging) {
        this.droneCharging = droneCharging;
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication) {
        this.medication = medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	

}
