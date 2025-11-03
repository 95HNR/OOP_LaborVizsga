/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hunor
 */

package com.mycompany.car.service.intake;

// Importok a JSON feldolgozáshoz
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


// Importok a fájlkezeléshez és listákhoz
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// *** ÚJ IMPORTOK A 3. FELADATHOZ (I/O) ***
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat; // Szebb számformázáshoz

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
        gsonBuilder.registerTypeAdapter(WorkOrder.class, new WorkOrder.WorkOrderDeserializer());
        Gson gson = gsonBuilder.create();
        
        ArrayList<Intake> validIntakes = new ArrayList<Intake>();
        
        int totalCount = 0;
        int errorCount = 0;

        // 3. Fájlbeolvasás (try-with-resources Java 8-tól, de a kód Java 8-on fut)
        // Mivel a pom.xml-t 1.8-ra állítottuk, használhatunk try-with-resources-t
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFilePath))) {

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
        } catch (IOException e) {
            System.err.println("Általános I/O hiba a data.json olvasása közben:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Általános hiba a feldolgozás során:");
            e.printStackTrace();
        }

        // 6. Eredmény kiírása konzolra
        System.out.println("\n--- FELDOLGOZÁS BEFEJEZVE ---");
        System.out.println("Összes bejegyzés a fájlban: " + totalCount);
        System.out.println("Sikeresen beolvasott (érvényes): " + validIntakes.size());
        System.out.println("Hibás (kihagyott): " + errorCount);
        System.out.println("Létrehozott Task (feladat) objektumok száma: " + Task.getTotalTasksCreated());
        

        // --- 7. Rendezés Comparator használatával ---
        System.out.println("\n--- Rendezés IntakeId alapján (Comparator) ---");
        Collections.sort(validIntakes, new Comparator<Intake>() {
            @Override
            public int compare(Intake o1, Intake o2) {
                return o1.getIntakeId().compareTo(o2.getIntakeId());
            }
        });
        
        // --- 8. Rendezett lista kiírása konzolra ---
        System.out.println("\nÉrvényes munkalapok listája (rendezve):");
        for (Intake intake : validIntakes) {
            System.out.println(intake); 
            for (WorkOrder wo : intake.getWorkOrders()) {
                System.out.println("  " + wo);
            }
        }

        // --- 9. RIPORT ÍRÁSA (3. FELADAT) ---
        System.out.println("\n--- 9. Riport írása (Task 3) ---");
        String reportPath = "report.txt"; // A projekt gyökerébe menti
        try {
            writeReport(reportPath, validIntakes, totalCount, errorCount);
            System.out.println("A '" + reportPath + "' sikeresen legenerálva.");
        } catch (IOException e) {
            System.err.println("HIBA: A '" + reportPath + "' írása sikertelen!");
            e.printStackTrace();
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

    /**
     * **ÚJ METÓDUS A 3. FELADATHOZ**
     * Legenerálja a report.txt fájlt a feldolgozott adatok alapján.
     *
     * @param path A kimeneti fájl neve (pl. "report.txt")
     * @param intakes A beolvasott érvényes munkalapok listája
     * @param totalCount Az összes bejegyzés száma
     * @param errorCount A hibás bejegyzések száma
     * @throws IOException Ha a fájl írása sikertelen
     */
    private static void writeReport(String path, ArrayList<Intake> intakes, int totalCount, int errorCount) throws IOException {
        
        // Java 8-tól a DecimalFormat a legjobb a pénznemek formázására
        DecimalFormat df = new DecimalFormat("#,##0.00 EUR");
        
        // try-with-resources (Java 7+), automatikusan lezárja a writert
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            
            writer.write("========================================"); writer.newLine();
            writer.write(" AUTÓSZERVIZ FELDOLGOZÁSI RIPORT"); writer.newLine();
            writer.write("========================================"); writer.newLine();
            writer.newLine();
            
            writer.write("--- ÖSSZEGZÉS ---"); writer.newLine();
            writer.write("Feldolgozott bejegyzések (összesen): " + totalCount); writer.newLine();
            writer.write("Érvényes munkalapok: " + intakes.size()); writer.newLine();
            writer.write("Hibás / Kihagyott bejegyzések: " + errorCount); writer.newLine();
            writer.newLine();
            
            writer.write("--- RÉSZLETES KÖLTSÉGEK (Munkalaponként) ---"); writer.newLine();
            
            double osszesBevetel = 0.0;
            
            for (Intake intake : intakes) {
                writer.newLine();
                writer.write("Munkalap ID: " + intake.getIntakeId()); writer.newLine();
                writer.write("  Ügyfél: " + intake.getClient().getName()); writer.newLine();
                writer.write("  Autó: " + intake.getCar().getMake() + " " + intake.getCar().getModel()); writer.newLine();
                
                double munkalapOsszesen = 0.0;
                for (WorkOrder wo : intake.getWorkOrders()) {
                    munkalapOsszesen += wo.calculateTotalCost();
                }
                
                writer.write("  Teljes költség: " + df.format(munkalapOsszesen)); writer.newLine();
                osszesBevetel += munkalapOsszesen;
            }
            
            writer.newLine();
            writer.write("========================================"); writer.newLine();
            writer.write(" VÁRHATÓ ÖSSZBEVÉTEL: " + df.format(osszesBevetel)); writer.newLine();
            writer.write("========================================"); writer.newLine();
        }
        // A try-with-resources miatt a .close() hívás automatikus
    }
}

