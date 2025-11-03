/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */


/**
 * Egy konkrét, számlázható tétel: egy elvégzett feladat (munka).
 * * Öröklődik a BaseEntity-től és megvalósítja a Billable interfészt.
 */
public class Task extends BaseEntity implements Billable {

    // --- Statikus tag (Registry/Counter) ---
    
    /**
     * Statikus számláló, ami nyomon követi, hány Task objektum jött létre.
     */
    private static int taskCounter = 0;

    // --- Mezők ---
    
    // Az 'id' mezőt a BaseEntity ősosztályból örökli.
    // private String id; // Erre már nincs szükség, örökölve van.
    
    private String desc;
    private double laborH;
    private double hourly;

    /**
     * Alapértelmezett konstruktor.
     * Növeli a statikus számlálót minden új példány létrehozásakor.
     */
    public Task() {
        super(); // Ősosztály konstruktorának hívása (nem kötelező kiírni)
        taskCounter++; // Statikus számláló növelése
    }

    // --- Getters és Setters ---
    
    // Az 'id' getter és setter metódusokat a BaseEntity-ből örökli.
    // public String getId() { ... } // Örökölve
    // public void setId(String id) { ... } // Örökölve

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getLaborH() {
        return laborH;
    }

    public void setLaborH(double laborH) {
        this.laborH = laborH;
    }

    public double getHourly() {
        return hourly;
    }

    public void setHourly(double hourly) {
        this.hourly = hourly;
    }

    // --- Statikus metódus ---
    
    /**
     * Visszaadja a létrehozott Task példányok teljes számát.
     * @return A taskCounter statikus változó értéke.
     */
    public static int getTotalTasksCreated() {
        return taskCounter;
    }

    // --- Interfész metódusok megvalósítása ---

    /**
     * A BaseEntity absztrakt metódusának megvalósítása.
     * A Task üzleti kulcsa maga a 'T-x' azonosítója.
     */
    @Override
    public String businessKey() {
        return this.id; // Visszaadja az örökölt 'id' mezőt
    }

    /**
     * A Billable interfész metódusának megvalósítása.
     * Kiszámolja a feladat teljes munkadíját.
     */
    @Override
    public double calculateTotalCost() {
        // Egyszerűsített számítás, a valóságban kerekíteni kellene
        return this.laborH * this.hourly;
    }

    // --- Felüldefiniált (Override) metódusok ---

    /**
     * A BaseEntity 'toString' metódusának bővítése.
     * Egy informatívabb kiíratást ad, ami tartalmazza a leírást és a költséget is.
     */
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + "', " +
                "desc='" + desc + "', " +
                "cost='" + calculateTotalCost() + "'" +
                '}';
    }
}
