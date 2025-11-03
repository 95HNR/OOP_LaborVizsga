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
 * Interfész, amely előírja, hogy az objektumnak van egy 
 * egyedi azonosítója (ID).
 */
public interface Identifiable {
    
    /**
     * Visszaadja az entitás elsődleges azonosítóját.
     * @return Az azonosító String formátumban.
     */
    String getId();

    /**
     * Beállítja az entitás elsődleges azonosítóját.
     * @param id Az új azonosító.
     */
    void setId(String id);
}
