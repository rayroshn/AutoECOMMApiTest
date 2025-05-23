package com.config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigReaderTest {

    @Test
    public void testGetMockBaseURI() {
        String expectedUri = "https://de0a4e67-8fb6-421b-883c-caaa920b6525.mock.pstmn.io";
        String actualUri = ConfigReader.getMockBaseURI();
        Assert.assertEquals(actualUri, expectedUri, "Mock Base URI should match the value in config.properties");
    }

    @Test
    public void testGetConduitBaseURI() {
        String expectedUri = "https://api.realworld.io/api/";
        String actualUri = ConfigReader.getConduitBaseURI();
        Assert.assertEquals(actualUri, expectedUri, "Conduit Base URI should match the value in config.properties");
    }

    @Test
    public void testGetHVBaseURI() {
        String expectedUri = "http://localhost:3004";
        String actualUri = ConfigReader.getHVBaseURI();
        Assert.assertEquals(actualUri, expectedUri, "HV Base URI should match the value in config.properties");
    }

    @Test
    public void testGetProperty_existingProperty() {
        String expectedUri = "https://api.realworld.io/api/"; // conduit.baseURI
        String actualUri = ConfigReader.getProperty("conduit.baseURI");
        Assert.assertEquals(actualUri, expectedUri, "getProperty should return the correct value for an existing property.");
    }

    @Test
    public void testGetProperty_nonExistingProperty() {
        String actualUri = ConfigReader.getProperty("non.existent.property");
        Assert.assertNull(actualUri, "getProperty should return null for a non-existing property.");
    }
}
