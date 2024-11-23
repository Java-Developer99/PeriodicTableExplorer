package com.periodicTable.services;

import com.periodicTable.models.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class DataLoader extends Thread{

    private final List<Elements> elements;
    private String fileName;

    public DataLoader(List<Elements> elements, String fileName) {
        this.elements = elements;
        this.fileName=fileName;
    }


    public void run() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(DataLoader.class.getClassLoader().getResourceAsStream("resource/element.csv"))
                )
        )) {
            String line;
            boolean isFirstLine = true; // To skip the header row

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header row
                    continue;
                }

                // Split the CSV line into fields
                String[] fields = line.split(",");
                if (fields.length == 6) { // Ensure the correct number of fields
                    // Parse fields into the respective data types
                    int atomicNumber = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim();
                    String symbol = fields[2].trim();
                    double atomicWeight = Double.parseDouble(fields[3].trim());
                    int group = Integer.parseInt(fields[4].trim());
                    int period = Integer.parseInt(fields[5].trim());


                    synchronized (elements) {
                        elements.add(new Elements(atomicNumber, name, symbol, atomicWeight, group, period));
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Error loading file: " + fileName);
            e.printStackTrace();
        }
    }
}
