package com.conduit.util;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult result) {
        if (result.getMethod().isBeforeSuiteConfiguration() || result.getMethod().isBeforeClassConfiguration()) {
            if (result.getStatus() == ITestResult.FAILURE) {
                throw new SkipException("Skipping all tests because a configuration method failed.");
            }
        }
    }
}
