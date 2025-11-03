/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hunor
 */

package com.mycompany.car.service.intake; // Ez a helyes package

// Importok a JSON feldolgozáshoz
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


// -----------------------------------------------------------

// Importok a fájlkezeléshez és listákhoz
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;
// 'List' importra már nincs szükség, ha 'ArrayList'-t használunk
// import java.util.List; 

/**
 *
 * @author Hunor
 */
public class CarServiceIntake {

    public static void main(String[] args) {
        
        // 1. A JSON fájl elérési útvonala
        String jsonFilePath = "C:\\Users\\Hunor\\Downloads\\OOP-Exam-2025-Info2-main\\exam2_car_service\\data.json";

        System.out.println("JSON feldolgozás indítása: " + jsonFilePath);

        // 2. Gson és a "tiszta" lista inicializálása
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        // A WorkOrderDeserializer-t is a model csomagból kell elérni
        gsonBuilder.registerTypeAdapter(WorkOrder.class, new WorkOrder.WorkOrderDeserializer());
        Gson gson = gsonBuilder.create(); 
        
        ArrayList<Intake> validIntakes = new ArrayList<Intake>();
        
        int totalCount = 0;
        int errorCount = 0;

        // 3. Fájlbeolvasás
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(jsonFilePath);
            bufferedReader = new BufferedReader(fileReader);

            // 4. A JSON "kézi" feldolgozása
            JsonParser parser = new JsonParser();
            JsonObject rootObject = parser.parse(bufferedReader).getAsJsonObject();
            JsonArray intakesArray = rootObject.getAsJsonArray("intakes");

            totalCount = intakesArray.size();
            System.out.println("Feldolgozás megkezdve, összesen " + totalCount + " bejegyzés...");

            // 5. Iterálás a bejegyzéseken és hibakezelés
            for (JsonElement intakeElement : intakesArray) {
                try {
                    Intake intake = gson.fromJson(intakeElement, Intake.class);
                    validIntakes.add(intake);
                    
                } catch (JsonSyntaxException e) {
                    errorCount++;
                    String idInfo = getErrorElementId(intakeElement);
                    System.err.println("Figyelem: " + idInfo + " kihagyva. Hiba: " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("KRITIKUS HIBA: A 'data.json' fájl nem található!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Általános hiba a feldolgozás során:");
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException e) { e.printStackTrace(); }
        }

        // 6. Eredmény kiírása
        System.out.println("\n--- FELDOLGOZÁS BEFEJEZVE ---");
        System.out.println("Összes bejegyzés a fájlban: " + totalCount);
        System.out.println("Sikeresen beolvasott (érvényes): " + validIntakes.size());
        System.out.println("Hibás (kihagyott): " + errorCount);
        System.out.println("Létrehozott Task (feladat) objektumok száma: " + Task.getTotalTasksCreated());
        

        // --- 7. Rendezés Comparator használatával ---
        
        System.out.println("\n--- Rendezés IntakeId alapján (Comparator) ---");
        
        Comparator<Intake> intakeIdComparator = new Comparator<Intake>() {
            @Override
            public int compare(Intake o1, Intake o2) {
                return o1.getIntakeId().compareTo(o2.getIntakeId());
            }
        };

        Collections.sort(validIntakes, intakeIdComparator);
        
        // --- 8. Rendezett lista kiírása ---
        
        System.out.println("\nÉrvényes munkalapok listája (rendezve):");
        for (Intake intake : validIntakes) {
            System.out.println(intake); 
            
            for (WorkOrder wo : intake.getWorkOrders()) {
                System.out.println("  " + wo);
                if (wo.getBillableItems() != null && !wo.getBillableItems().isEmpty()) {
                    System.out.println("    -> Első tétel: " + wo.getBillableItems().get(0));
                }
            }
        }
    }

    /**
     * Segédfüggvény a hibás elem azonosításához
     */
    private static String getErrorElementId(JsonElement element) {
        try {
            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.has("intakeId") && obj.get("intakeId").isJsonPrimitive()) {
                    return "Hibás elem (ID: " + obj.get("intakeId").getAsString() + ")";
                }
            }
        } catch (Exception e) { /* Hiba azonosítás közben, nem baj */ }
        return "Ismeretlen hibás elem";
    }
}
