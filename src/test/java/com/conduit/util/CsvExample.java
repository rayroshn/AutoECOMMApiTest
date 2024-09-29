package com.conduit.util;

import com.conduit.request.Pojo.Request.User;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CsvExample {

    public static void main(String[] args) throws Exception {
        // Path to the CSV files
        String userCsvFilePath = "path_to_user_csv.csv";
        String orderCsvFilePath = "path_to_order_csv.csv";
        String projectRootDir = System.getProperty("user.dir");
        // Append the relative path to the resources folder and the CSV file
         userCsvFilePath = projectRootDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "loginUserData.csv";

        // Read and map User data
        List<Map<String, String>> userCsvData = CsvReaderUtill.readCsvData(userCsvFilePath);
        String email = userCsvData.get(0).get("email");
        System.out.println("email = " + email);
        for(Map<String,String> row : userCsvData)
         {
             String password = row.get("password");
             System.out.println("password = " + password);
             String username = row.get("username");
             System.out.println("username = " + username);
         }

       /* // Read and map Order data
        List<Map<String, String>> orderCsvData = CsvReaderUtils.readCsvData(orderCsvFilePath);
        for (Map<String, String> row : orderCsvData) {
            Order order = CsvMapperUtil.mapCsvToClass(row, Order.class);
            System.out.println(order);  // Print or use the Order object
        }*/
    }
}
