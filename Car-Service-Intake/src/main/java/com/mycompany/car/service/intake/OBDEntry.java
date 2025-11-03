/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */

public class OBDEntry {

    // --- Mezők ---
    private String dtc;
    private String status;

    // --- Getters és Setters ---

    public String getDtc() {
        return dtc;
    }

    public void setDtc(String dtc) {
        this.dtc = dtc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}