package com.conduit.request.Response;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.conduit.request.ApiTest.articleAPITest;
import com.conduit.request.Pojo.Request.LoginUserPayload;
import com.conduit.request.Pojo.Request.User;
import com.conduit.util.CsvReaderUtill;
import com.conduit.util.tokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTestBase_Updates {

    static  PrintStream  logFile;
    //protected static String authToken ;
    protected static ThreadLocal<String> authToken = ThreadLocal.withInitial(() -> "");
    //protected static String bearerToken ;
    protected static ThreadLocal<String> bearerToken = ThreadLocal.withInitial(() -> "");
    private static ThreadLocal<RequestSpecification> noTokenRequestSpecification  = ThreadLocal.withInitial(()->createRequestSpecification(logFile));
    private static ThreadLocal<RequestSpecification> withTokenRequestSpecification  = ThreadLocal.withInitial(()->createRequestSpecification(logFile,bearerToken.get(),authToken.get()));
    public static final Logger logger = LoggerFactory.getLogger(articleAPITest.class);

    @BeforeSuite
    public void setup() throws IOException {
        try {
            // Get working directory and prepare log file
            String userDir = System.getProperty("user.dir");
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String logFileName = userDir + "/src/test/resources/Log/" + "_log_" + timestamp + ".log";
            PrintStream logFile = new PrintStream(logFileName);
            System.out.println("Logging to file: " + logFile.toString());

            // Initialize RequestSpecification (without token)
            noTokenRequestSpecification.set(createRequestSpecification(logFile));
           // RequestSpecification requestSpecification = createRequestSpecification(logFile);

            // Set global RestAssured request specification
           // RestAssured.requestSpecification = requestSpecification;
            RestAssured.requestSpecification=  noTokenRequestSpecification.get();

            // Get the token and set it up for future requests
            logger.info("Starting setup to get the token...");
            //authToken.set(tokenUtil.getToken().get());
            authToken.set(getToken().get());
            //authToken = tokenUtil.getToken();
            logger.info("Auth token received: " + authToken.get());

            // Set the bearer token globally
            bearerToken.set("Bearer " + authToken.get());
            //bearerToken = "Bearer " + authToken;

            // Add the token to the request spec
           // RestAssured.requestSpecification = createRequestSpecification(logFile,bearerToken,authToken);
            withTokenRequestSpecification.set(createRequestSpecification(logFile, bearerToken.get(), authToken.get()));
            RestAssured.requestSpecification = withTokenRequestSpecification.get();


            logger.info("Global RequestSpecification set with Authorization header.");

            // Load test data templates
            logger.info("Loading Fixtures Templates from util packages");
            FixtureFactoryLoader.loadTemplates("com.conduit.util");

        } catch (FileNotFoundException e) {
            System.out.println("Failed to create log file");
            e.printStackTrace();
        }
    }



    private static RequestSpecification createRequestSpecification(PrintStream logFile) {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.realworld.io/api/")
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter(logFile))    // Logs to file
                .addFilter(new ResponseLoggingFilter(logFile))   // Logs to file
                .addFilter(new RequestLoggingFilter())           // Logs to console
                .addFilter(new ResponseLoggingFilter())          // Logs to console
                .build();
    }

    private static RequestSpecification createRequestSpecification(PrintStream logFile, String bearerToken, String authToken) {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.realworld.io/api/")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", String.valueOf(bearerToken)).addHeader("authorization","Token " + authToken)  // Apply bearer token to all requests
                .addFilter(new RequestLoggingFilter(logFile))    // Logs to file
                .addFilter(new ResponseLoggingFilter(logFile))   // Logs to file
                .addFilter(new RequestLoggingFilter())           // Logs to console
                .addFilter(new ResponseLoggingFilter())          // Logs to console
                .build();
    }

    // Method to get RequestSpecification without tokens (for scenarios where token is not needed)
    public static RequestSpecification getNoTokenRequestSpecification() {
        return noTokenRequestSpecification.get();
    }

    // Method to get RequestSpecification with tokens (for authenticated API requests)
    public static RequestSpecification getWithTokenRequestSpecification() {
        return withTokenRequestSpecification.get();
    }
    // Logging setup for individual test classes (optional but not necessary for token-related issues)
    public void setupLoggingForClass(String className) {
        try {
            String userDir = System.getProperty("user.dir");
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String logFileName = userDir + "/src/test/resources/Log/" + className + "_log_" + timestamp + ".log";
            PrintStream logFile = new PrintStream(logFileName);

            // Reapply filters for class-specific logging (optional)
            RestAssured.filters(
                    new RequestLoggingFilter(logFile),   // Logs to file
                    new ResponseLoggingFilter(logFile),  // Logs to file
                    new RequestLoggingFilter(),          // Logs to console
                    new ResponseLoggingFilter()          // Logs to console
            );
        } catch (FileNotFoundException e) {
            System.out.println("Failed to create log file");
            e.printStackTrace();
        }
    }



// Fetch token


    static final ThreadLocal<String> token = ThreadLocal.withInitial(() -> "");
    static Response response;
    public static ThreadLocal<String> getToken() throws IOException {

        {
            String projectRootDir = System.getProperty("user.dir");
            // Append the relative path to the resources folder and the CSV file
            String userCsvFilePath = projectRootDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "loginUserData.csv";

            List<Map<String, String>> userLogin;

            userLogin = CsvReaderUtill.readCsvData(userCsvFilePath);
            //User login details class
            com.conduit.request.Pojo.Request.User user = new User();
            user.setEmail(userLogin.get(0).get("email")).setPassword(userLogin.get(0).get("password"));
            LoginUserPayload loginUserPayload = new LoginUserPayload(user);

            // ObjectMapper for JSON to POJO
            ObjectMapper objectMapper = new ObjectMapper();
            final String loginPayload = objectMapper.writeValueAsString(loginUserPayload);


            // API Login Response captured
            response = given().body(loginPayload).when()
                    .post("/users/login").andReturn();

            // Response captured converted to string
            String responseBody = response.asString();
            System.out.println("Response Body: " + responseBody);

       /* String responseBody = response.asString();
        System.out.println("Response Body: " + responseBody);
       String token= response.jsonPath().getString("user.token");
        String token = response.jsonPath().getString("user.token");
        System.out.println("token = " + token);*/
            String fetchedToken = response.jsonPath().getString("user.token");
            //token = response.jsonPath().getString("user.token");
            System.out.println("Token:" + fetchedToken.trim());
            token.set(fetchedToken);

        }
        return token;
    }


    @Test
    public void getToeken()
    {
        System.out.println("authToken >>" + authToken);


    }
}
