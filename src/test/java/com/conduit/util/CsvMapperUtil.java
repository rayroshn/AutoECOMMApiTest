package com.conduit.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class CsvMapperUtil {

    // Generic method to map CSV data to any class type
    public static <T> T mapCsvToClass(Map<String, String> csvData, Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, String> entry : csvData.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();

            // Get the class field by the CSV column name
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);  // Make the field accessible if it's private

            // Convert the string value to the appropriate type and set it
            if (field.getType() == String.class) {
                field.set(instance, fieldValue);
            } else if (field.getType() == int.class || field.getType() == Integer.class) {
                field.set(instance, Integer.parseInt(fieldValue));
            } else if (field.getType() == double.class || field.getType() == Double.class) {
                field.set(instance, Double.parseDouble(fieldValue));
            }
            // You can add more type conversions as needed (e.g., boolean, float, etc.)
        }

        return instance;
    }
}
