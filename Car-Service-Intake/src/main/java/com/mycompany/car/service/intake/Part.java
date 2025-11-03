/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */


import com.google.gson.annotations.SerializedName;

/**
 * Egy konkrét, számlázható tétel: egy beépített alkatrész.
 * * Öröklődik a BaseEntity-től és megvalósítja a Billable interfészt.
 */
public class Part extends BaseEntity implements Billable {

    // --- Mezők ---
    
    /**
     * A BaseEntity 'id' mezőjét fogjuk használni a 'sku' (cikkszám) tárolására.
     * A @SerializedName annotáció jelzi a Gson-nak, hogy a JSON-ban
     * "sku"-ként szereplő mezőt töltse be az örökölt "id" mezőbe.
     */
    @SerializedName("sku")
    protected String id; // Felüldefiniáljuk az 'id'-t, hogy megkapja az annotációt

    private String name;
    private int qty;
    private double unitPrice;

    // --- Getters és Setters ---

    // Az 'id' getter és setter (amit mi SKU-ként használunk) örökölve van
    // public String getId() { ... } // Örökölve
    // public void setId(String id) { ... } // Örökölve

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // --- Interfész metódusok megvalósítása ---

    /**
     * A BaseEntity absztrakt metódusának megvalósítása.
     * Az Alkatrész üzleti kulcsa a 'sku' (cikkszám).
     */
    @Override
    public String businessKey() {
        return this.id; // Visszaadja az 'id' mezőt, ami a 'sku'-t tartalmazza
    }

    /**
     * A Billable interfész metódusának megvalósítása.
     * Kiszámolja az alkatrészek teljes költségét.
     */
    @Override
    public double calculateTotalCost() {
        return this.qty * this.unitPrice;
    }
    
    // --- Felüldefiniált (Override) metódusok ---

    /**
     * A BaseEntity 'toString' metódusának bővítése.
     */
    @Override
    public String toString() {
        return "Part{" +
                "sku='" + id + "', " + // 'id' helyett 'sku'-t írunk a jobb olvashatóságért
                "name='" + name + "', " +
                "cost='" + calculateTotalCost() + "'" +
                '}';
    }
}