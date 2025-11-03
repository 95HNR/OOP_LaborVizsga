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
 * Az ügyfelet reprezentáló entitás.
 * * Öröklődik a BaseEntity-től.
 */
public class Client extends BaseEntity {

    // --- Mezők ---

    /**
     * A BaseEntity 'id' mezőjét fogjuk használni a Client 'id'-jének tárolására.
     * A @SerializedName annotáció jelzi a Gson-nak, hogy a JSON-ban
     * "id"-ként szereplő mezőt töltse be az örökölt "id" mezőbe.
     */
    @SerializedName("id")
    protected String id; // Felüldefiniáljuk az 'id'-t, hogy megkapja az annotációt

    private String name;
    private String phone;
    private String email;

    // --- Getters és Setters ---

    // Az 'id' getter és setter metódusokat a BaseEntity-ből örökli.
    // public String getId() { ... } // Örökölve
    // public void setId(String id) { ... } // Örökölve

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // --- Interfész metódusok megvalósítása ---

    /**
     * A BaseEntity absztrakt metódusának megvalósítása.
     * Az Ügyfél üzleti kulcsa az 'id'-ja (pl. "CL-993").
     */
    @Override
    public String businessKey() {
        return this.id;
    }

    // --- Felüldefiniált (Override) metódusok ---
    
    /**
     * A BaseEntity 'toString' metódusának bővítése.
     */
    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + "', " +
                "name='" + name + "', " +
                "email='" + email + "'" +
                '}';
    }
}