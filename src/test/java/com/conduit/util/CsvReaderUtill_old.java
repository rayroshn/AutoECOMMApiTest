package com.conduit.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvReaderUtill_old {

    // Generic CSV reader that returns a list of maps, where each map represents a row of data.
    public static List<Map<String, String>> readCsvData(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        List<Map<String, String>> result = new ArrayList<>();
        String[] headers = null;

        // Read the header row
        if ((row = csvReader.readLine()) != null) {
            headers = row.split(",");  // Assume headers are present in the first row
        }

        // Read each data row and store it in a map (with column names as keys)
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Map<String, String> rowData = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                rowData.put(headers[i], data[i]);  // Map column name (header) to its value
            }
            result.add(rowData);
        }

        csvReader.close();
        return result;
    }
}
