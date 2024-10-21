package com.FileIO;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.conduit.util.FileDownloadUtil.downloadFile;

public class FileDownloadAutomation {

    @Test
    public void DownloadFileAPITest()  {
        // API URL of the image to download
        String fileUrl = "http://openweathermap.org/img/w/11d.png"; // Image URL from OpenWeatherMap
        String outputFilePath = "downloadedFiles/weather_icons_"+ new Faker().idNumber().ssnValid() +"_11d.png"; // Path where file will be saved

        // Call the method to download the file
        try {
            downloadFile(fileUrl, outputFilePath);
            System.out.println("File downloaded successfully at: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error occurred while downloading the file: " + e.getMessage());
        }
    }


}
