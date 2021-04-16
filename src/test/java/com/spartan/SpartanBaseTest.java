package com.spartan;

import com.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanBaseTest {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI= ConfigurationReader.getProperty("spartanBaseURI");
        RestAssured.basePath=ConfigurationReader.getProperty("spartanBasePath");
    }

    @AfterAll
    public static void cleanup(){
        RestAssured.reset()
        ;
    }
}
