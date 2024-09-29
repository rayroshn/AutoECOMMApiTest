package com.conduit.util;

import com.conduit.request.Pojo.Request.User;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReaderUtil {

    // Method to read data from CSV and print it
    public static List<User> readAndPrintCsv(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        List<User> userList = new ArrayList<>();
        // Read the header first and skip
        String header = csvReader.readLine();

        // Reading each row after the header and printing the values
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String email = data[0];
            String username = data[1];
            String password = data[2];

            // Print values to the console
            System.out.println("Email: " + email);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("-------------");
            userList.add(new User(email,password));
        }

        csvReader.close();
        return userList;
    }

    public static List<String[]> readCsvData(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        List<String[]> userList = new ArrayList<>();
        // Read the header first and skip
        String header = csvReader.readLine();

        // Reading each row after the header and printing the values
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String email = data[0];
            String username = data[1];
            String password = data[2];
            userList.add(data);
        }

        csvReader.close();
        return userList;
    }

    public static void main(String[] args) throws IOException {
        // Call the method and pass the path to your CSV file
        String projectRootDir = System.getProperty("user.dir");

        // Append the relative path to the resources folder and the CSV file
        String csvFilePath = projectRootDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "loginUserData.csv";
        System.out.println("csvFilePath = " + csvFilePath);
        // Print the path
       /* List<User> userList=readAndPrintCsv(csvFilePath);
        User firstUser = userList.get(0);

        // Extract values
        String email = firstUser.getEmail();
        String username = firstUser.getPassword();


        System.out.println("userList = " + email + username);*/

        List<String[]> str =readCsvData(csvFilePath);

       for(int i=0;i< str.size();i++)
       {
           System.out.println(Arrays.toString(str.get(i)).trim());

       }

    }
}
