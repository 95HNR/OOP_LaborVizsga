/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */

package com.mycompany.car.service.intake; // EZ A HELYES package sor

import java.util.ArrayList;
// NINCS SZÜKSÉG import-ra a Client, Car, stb. osztályokhoz,
// mivel azok is mind a 'model' package-ben vannak.

/**
 * A fő entitás, ami egy teljes munkalapot reprezentál.
 */
public class Intake {
    
    // --- Mezők ---
    // A fordító most már tudni fogja, hogy ezeket az osztályokat
    // (Client, Car, stb.) ugyanabban a 'model' csomagban kell keresni.
    private String intakeId;
    private String receivedAt;
    private Client client;
    private Car car;
    private ArrayList<ReportedIssue> reportedIssues;
    private Diagnostics diagnostics;
    private ArrayList<WorkOrder> workOrders;
    private Invoice invoice;
    private Meta meta;

    // --- Getters és Setters ---

    public String getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(String intakeId) {
        this.intakeId = intakeId;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ArrayList<ReportedIssue> getReportedIssues() {
        return reportedIssues;
    }

    public void setReportedIssues(ArrayList<ReportedIssue> reportedIssues) {
        this.reportedIssues = reportedIssues;
    }

    public Diagnostics getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(Diagnostics diagnostics) {
        this.diagnostics = diagnostics;
    }

    public ArrayList<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(ArrayList<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    // --- Felüldefiniált toString() ---
    
    @Override
    public String toString() {
        // A 'client' és 'car' már a saját, felüldefiniált toString()-jét fogja használni
        return "Intake{" +
                "intakeId='" + intakeId + "', " +
                "client=" + (client != null ? client.getName() : "N/A") + ", " +
                "car=" + (car != null ? car.getMake() + " " + car.getModel() : "N/A") +
                '}';
    }
}