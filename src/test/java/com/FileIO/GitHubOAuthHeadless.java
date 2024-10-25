package com.FileIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class GitHubOAuthHeadless {

    String code;

    public static final Logger logger = LoggerFactory.getLogger(GitHubOAuthHeadless.class);

    @Test
    public void testAuthCode() throws InterruptedException {

        String AuthCode=getAuthCode();
        System.out.println("AuthCode = " + AuthCode);

    }

    public  String getAuthCode() throws InterruptedException {

      //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        //options.addArguments("--headless");  // Use the original --headless flag
       /* options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");  // Disables GPU acceleration, helpful for CI environments
        options.addArguments("--disable-dev-shm-usage");  // Reduces resource usage for shared memory
        options.addArguments("--window-size=1920,1080"); */ // Set window size to avoid viewport issues
        options.addArguments("--disable-software-rasterizer");  // Forces Chrome to use only software rendering
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        // Start Chrome Driver
        WebDriver driver = new ChromeDriver(options);


        // Navigate to the authorization URL
        String authorizationUrl = "https://github.com/login/oauth/authorize?client_id=Ov23lie7ZhQOcstacB8G&redirect_uri=https://github.com/callback&scope=user";
        https://github.com/login?client_id=Ov23lie7ZhQOcstacB8G&return_to=%2Flogin%2Foauth%2Fauthorize%3Fclient_id%3DOv23lie7ZhQOcstacB8G%26redirect_uri%3Dhttps%253A%252F%252Fgithub.com%252Fcallback%26scope%3Duser
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

        Thread.sleep(2);
        String textIntegrator = driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/div/p/strong[2]")).getText().trim();
        System.out.println("textIntegrator = " + textIntegrator);
        driver.findElement(By.id("login_field")).sendKeys("rayroshn@gmail.com");
        String login_field = driver.findElement(By.id("login_field")).getAttribute("value");
        System.out.println("login_field = " + login_field);

        driver.findElement(By.id("password")).sendKeys("Pardegand#!!4u");
        String password = driver.findElement(By.id("password")).getAttribute("value");
        System.out.println("password = " + password);

        String passKey = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/webauthn-subtle/p/button/span/span")).getText().trim();
        System.out.println("passKey = " + passKey);
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/form/div/input[13]")).click();
        logger.info("SIGN IN BUTTON CLICKED ");
       Thread.sleep(2);
        String redirectUrls = driver.getCurrentUrl();
        logger.info("Redirect URL new: " + redirectUrls);



        String[] parts = redirectUrls.split("=");
        System.out.println("parts = " + parts[1]);

        // The code part is in the second element (index 1)
        if (parts.length > 1) {
             code = parts[1];
            System.out.println("Authorization Code: " + code);
        } else {
            System.out.println("Code not found in URL");
        }

        // Close the browser
        driver.quit();

        System.out.println("Returning Authorization Code ....... : " + code);
        return code;
    }
}
