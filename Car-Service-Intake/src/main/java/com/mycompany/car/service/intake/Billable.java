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
 * Interfész, amely előírja, hogy egy tétel számlázható,
 * azaz rendelkezik egy kalkulálható teljes költséggel.
 */
public interface Billable {

    /**
     * Kiszámolja és visszaadja a tétel teljes költségét.
     * Pl. (darabszám * egységár) vagy (munkaóra * óradíj).
     *
     * @return A tétel teljes költsége.
     */
    double calculateTotalCost();
}
