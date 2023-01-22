package com.smag.dronesmanagement.dto;

public class CreateChargingItemDTO {

    private MedicationDTO medication;

    private int quantity;

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
