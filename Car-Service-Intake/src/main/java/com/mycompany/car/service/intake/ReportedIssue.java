/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */


package com.mycompany.car.service.intake;

// List helyett ArrayList-et importálunk
import java.util.ArrayList;

public class ReportedIssue {

    // --- Mezők ---
    private String severity;
    private String code;
    
    // List -> ArrayList
    private ArrayList<String> symptoms;
    
    private int sinceKm;

    // --- Getters és Setters (frissített típusokkal) ---

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public int getSinceKm() {
        return sinceKm;
    }

    public void setSinceKm(int sinceKm) {
        this.sinceKm = sinceKm;
    }
}
