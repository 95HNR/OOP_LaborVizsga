/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */

package com.mycompany.car.service.intake; // EZ VOLT A HIÁNYZÓ SOR

import java.util.ArrayList;
// Nincs szükség importra a többi modell osztályhoz,
// mivel azok is ebben a package-ben vannak.

public class Diagnostics {

    // --- Mezők (List -> ArrayList) ---
    private ArrayList<OBDEntry> obd;
    private ArrayList<TestEntry> tests;

    // --- Getters és Setters (frissített típusokkal) ---

    public ArrayList<OBDEntry> getObd() {
        return obd;
    }

    public void setObd(ArrayList<OBDEntry> obd) {
        this.obd = obd;
    }

    public ArrayList<TestEntry> getTests() {
        return tests;
    }

    public void setTests(ArrayList<TestEntry> tests) {
        this.tests = tests;
    }
}

