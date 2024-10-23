package com.FileIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class GitHubOAuthHeadless {

    String code;

    @Test
    public void testAuthCode()
    {

        String AuthCode=getAuthCode();
        System.out.println("AuthCode = " + AuthCode);

    }

    public  String getAuthCode() {

      //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Optional: Run in headless mode
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Start Chrome Driver
        WebDriver driver = new ChromeDriver(options);


        // Navigate to the authorization URL
        String authorizationUrl = "https://github.com/login/oauth/authorize?client_id=Ov23lie7ZhQOcstacB8G&redirect_uri=https://github.com/callback&scope=user";
        driver.get(authorizationUrl);

        // Add logic to fill in credentials and click the authorization button
        // This part will depend on the HTML structure of the GitHub login page
        // For example:
        // WebElement usernameField = driver.findElement(By.id("login_field"));
        // usernameField.sendKeys("your-username");
        // WebElement passwordField = driver.findElement(By.id("password"));
        // passwordField.sendKeys("your-password");
        // passwordField.submit(); // or click the login button

        // Capture the redirect URL once authorized
        String redirectUrl = driver.getCurrentUrl();
        System.out.println("Redirect URL: " + redirectUrl);


        // Extract authorization code and exchange it for an access token...

        driver.findElement(By.id("login_field")).sendKeys("rayroshn@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Pardegand#!!4u");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/form/div/input[13]")).click();

        String redirectUrls = driver.getCurrentUrl();
        System.out.println("Redirect URL new: " + redirectUrls);

        String[] parts = redirectUrls.split("=");

        // The code part is in the second element (index 1)
        if (parts.length > 1) {
             code = parts[1];
            System.out.println("Authorization Code: " + code);
        } else {
            System.out.println("Code not found in URL");
        }

        // Close the browser
        driver.quit();

        return code;
    }
}
