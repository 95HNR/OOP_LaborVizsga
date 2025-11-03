/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.car.service.intake;

/**
 *
 * @author Hunor
 */


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Egy munkamegrendelést reprezentál, ami számlázható tételeket (Task, Part) tartalmaz.
 *
 * FIGYELEM: A JSON struktúra ('tasks' és 'parts' külön tömbökben) eltér
 * a mi új OOP modellünktől (egy közös 'billableItems' lista).
 * Emiatt egy egyedi Gson Deserializer-re van szükségünk, hogy
 * a beolvasáskor a két külön tömböt egy listába fésüljük.
 */
public class WorkOrder {

    // --- Mezők ---
    private String type;

    /**
     * ArrayList használata a feladat kérésének megfelelően.
     * Ez a lista tárolja az összes számlázható tételt (munkadíjak ÉS alkatrészek).
     */
    private ArrayList<Billable> billableItems;

    // --- Konstruktor ---
    public WorkOrder() {
        this.billableItems = new ArrayList<Billable>();
    }

    // --- Getters és Setters ---

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Billable> getBillableItems() {
        return billableItems;
    }

    // Nincs setter az egész listára, helyette add/remove metódusokat használunk
    // public void setBillableItems(ArrayList<Billable> billableItems) { ... }

    // --- Metódus Túlterhelés (Overloading) ---

    /**
     * Túlterhelt metódus #1: Hozzáad egy Task-ot a számlázható tételekhez.
     * @param task A hozzáadandó feladat.
     */
    public void addBillableItem(Task task) {
        if (task != null) {
            this.billableItems.add(task);
        }
    }

    /**
     * Túlterhelt metódus #2: Hozzáad egy Part-ot a számlázható tételekhez.
     * @param part A hozzáadandó alkatrész.
     */
    public void addBillableItem(Part part) {
        if (part != null) {
            this.billableItems.add(part);
        }
    }

    // --- Domain-specifikus metódusok ---

    /**
     * Kiszámolja a munkamegrendelés teljes költségét
     * az összes hozzá tartozó tétel (Task és Part) költségének összegzésével.
     * @return A WorkOrder teljes költsége.
     */
    public double calculateTotalCost() {
        double total = 0.0;
        for (Billable item : billableItems) {
            total += item.calculateTotalCost();
        }
        return total;
    }
    
    @Override
    public String toString() {
        return "WorkOrder{" +
                "type='" + type + "', " +
                "totalCost=" + calculateTotalCost() + ", " +
                "itemCount=" + billableItems.size() +
                '}';
    }

    /**
     * Egyedi Deserializer a WorkOrder számára.
     * Ez szükséges, mert a JSON-ban 'tasks' és 'parts' van, de mi
     * egy közös 'billableItems' listába akarjuk olvasni őket.
     */
    public static class WorkOrderDeserializer implements JsonDeserializer<WorkOrder> {
        @Override
        public WorkOrder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            
            JsonObject jsonObject = json.getAsJsonObject();
            WorkOrder workOrder = new WorkOrder();
            
            // 1. Egyszerű mező beolvasása
            workOrder.setType(jsonObject.get("type").getAsString());

            // 2. 'tasks' tömb beolvasása és hozzáadása a billableItems listához
            if (jsonObject.has("tasks")) {
                JsonArray tasksArray = jsonObject.getAsJsonArray("tasks");
                for (JsonElement taskElement : tasksArray) {
                    // A 'context' segítségével deserializáljuk az elemet Task-ká
                    Task task = context.deserialize(taskElement, Task.class);
                    workOrder.addBillableItem(task);
                }
            }
            
            // 3. 'parts' tömb beolvasása és hozzáadása ugyanahhoz a listához
            if (jsonObject.has("parts")) {
                JsonArray partsArray = jsonObject.getAsJsonArray("parts");
                for (JsonElement partElement : partsArray) {
                    // A 'context' segítségével deserializáljuk az elemet Part-tá
                    Part part = context.deserialize(partElement, Part.class);
                    workOrder.addBillableItem(part);
                }
            }
            
            return workOrder;
        }
    }
}