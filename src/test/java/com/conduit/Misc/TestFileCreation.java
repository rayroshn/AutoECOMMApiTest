package com.conduit.Misc;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestFileCreation {
    public static void main(String[] args) {
        try {
            PrintStream logFile = new PrintStream("api_testss.log");
            logFile.println("This is a test log");
            logFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
