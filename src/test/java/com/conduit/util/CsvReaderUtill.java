package com.conduit.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvReaderUtill {

    // Method to read CSV into a list of maps, each map representing a row
    public static List<Map<String, String>> readCsvData(String filePath) throws IOException {
        List<Map<String, String>> dataList = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        // Read header and split into columns
        String headerLine = csvReader.readLine();
        String[] headers = headerLine.split(",");

        String row;
        // Read each row and map values to the respective column name
        while ((row = csvReader.readLine()) != null) {
            String[] rowData = row.split(",");
            Map<String, String> dataMap = new HashMap<>();

            for (int i = 0; i < headers.length; i++) {
                dataMap.put(headers[i], rowData[i]);
            }
            dataList.add(dataMap);
        }

        csvReader.close();
        return dataList;
    }

    // Method to get all CSVs
    public static void getCsvDatas(String[] csvFiles)
    {

        for (String csvFile : csvFiles) {
            try {
                List<Map<String, String>> csvData = CsvReaderUtill.readCsvData(csvFile);

                // Process or print the data
                System.out.println("Data from " + csvFile + ":");
                for (Map<String, String> row : csvData) {
                    System.out.println(row.get(0)); // Print the entire row
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + csvFile);
                e.printStackTrace();
            }
        }
    }
}

