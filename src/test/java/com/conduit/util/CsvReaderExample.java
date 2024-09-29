package com.conduit.util;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CsvReaderExample {
    public static void main(String[] args) {
        String projectRootDir = System.getProperty("user.dir");
        String userCsvFilePathloginUserData = projectRootDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "loginUserData.csv";
        String userCsvFilePathloginCommentData = projectRootDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "commentData.csv";

        String[] csvFiles = {
                userCsvFilePathloginUserData,
                userCsvFilePathloginCommentData
        };

        getCsvDatas(csvFiles);

    }


    public static void getCsvDatas(String[] csvFiles)
    {

        for (String csvFile : csvFiles) {
            try {
                //List<Map<String, String>> csvData = CsvReaderUtill.readCsvData(csvFile);
                List<Map<String, String>> csvData=CsvReaderUtill.readCsvData(csvFile);
                // Process or print the data
                System.out.println("Data from " + csvFile + ":");
                for (Map<String, String> row : csvData) {
                    System.out.println(row.get("email")); // Print the entire row
                }

            } catch (IOException e) {
                System.err.println("Error reading file: " + csvFile);
                e.printStackTrace();
            }
        }
    }
}
