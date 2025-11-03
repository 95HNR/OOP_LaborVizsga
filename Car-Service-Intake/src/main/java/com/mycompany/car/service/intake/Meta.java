/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */

public class Meta {

    // --- Mezők ---
    private String priority;
    private boolean courtesyCar;

    // --- Getters és Setters ---

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCourtesyCar() {
        return courtesyCar;
    }

    public void setCourtesyCar(boolean courtesyCar) {
        this.courtesyCar = courtesyCar;
    }
}