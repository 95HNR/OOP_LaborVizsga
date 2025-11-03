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
 * Absztrakt ősosztály minden entitáshoz (pl. Task, Part, Client).
 * * Megvalósítja az Identifiable interfészt és egy közös alapot biztosít
 * az equals(), hashCode() és toString() metódusokhoz.
 */
public abstract class BaseEntity implements Identifiable {

    /**
     * Az entitás azonosítója. A JSON-ból ez a 'id' vagy 'sku' stb. lesz.
     * 'protected' láthatóságú, hogy az alosztályok közvetlenül elérjék.
     */
    protected String id;

    // --- Absztrakt metódus ---
    
    /**
     * Visszaad egy "üzleti kulcsot" (business key), ami egyedileg azonosítja
     * az entitást az üzleti logika szempontjából. 
     * Ennek egyedinek kell lennie az adott típuson belül (pl. 'T-1' a Task-ok között).
     *
     * @return Az egyedi üzleti kulcs Stringként.
     */
    public abstract String businessKey();

    // --- Az Identifiable interfész megvalósítása ---

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    // --- Közös metódusok felüldefiniálása (Overriding) ---

    /**
     * Az 'equals' felüldefiniálása.
     * * Két entitás akkor egyenlő, ha:
     * 1. Nem nullák.
     * 2. Pontosan azonos osztályba tartoznak.
     * 3. Az üzleti kulcsaik (businessKey) megegyeznek.
     * * Ez biztosítja, hogy pl. két 'Task' objektum, aminek ugyanaz a 'T-1' 
     * azonosítója, egyenlőnek minősül.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Ugyanaz az objektum a memóriában
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Nem azonos osztály
        }
        
        // Kasztolás BaseEntity-re (biztonságos a fenti ellenőrzés miatt)
        BaseEntity that = (BaseEntity) obj;
        
        // Összehasonlítás a businessKey() alapján (Java 6 kompatibilis null-check)
        String thisKey = this.businessKey();
        String thatKey = that.businessKey();

        if (thisKey == null) {
            return (thatKey == null);
        } else {
            return thisKey.equals(thatKey);
        }
    }

    /**
     * A 'hashCode' felüldefiniálása.
     * * Ha felüldefiniáljuk az 'equals'-t, a 'hashCode'-ot is kötelező,
     * hogy a Hashing alapú kollekciók (pl. HashMap, HashSet) megfelelően működjenek.
     * A hashCode-ot az 'equals'-ben is használt 'businessKey' alapján generáljuk.
     */
    @Override
    public int hashCode() {
        // Java 6-kompatibilis hashCode generálás
        String key = this.businessKey();
        return (key != null ? key.hashCode() : 0);
    }

    /**
     * A 'toString' felüldefiniálása egy informatívabb kiíratásért.
     * Pl: "Task{id='T-1', businessKey='T-1'}"
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + id + "', " +
                "businessKey='" + businessKey() + "'" +
                '}';
    }
}
