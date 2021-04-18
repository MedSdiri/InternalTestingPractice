package com.step_definitions;

import com.utilities.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class Hooks {

    @Before
    public static void init(){

        RestAssured.baseURI= ConfigurationReader.getProperty("spartanBaseURI");
        RestAssured.basePath=ConfigurationReader.getProperty("spartanBasePath");
    }

    @After
    public static void cleanup(){
        RestAssured.reset()
        ;
    }
}
