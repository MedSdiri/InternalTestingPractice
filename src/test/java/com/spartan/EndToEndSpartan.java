package com.spartan;

import com.pojo.Spartan;
import com.pojo.SpartanGet;
import com.utilities.ConfigurationReader;
import com.utilities.DB_Utility;
import com.utilities.SpartanBaseTest;
import com.utilities.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.OrderWith;
import org.junit.runners.MethodSorters;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;

@SerenityTest @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EndToEndSpartan extends SpartanBaseTest {
    private static String login = ConfigurationReader.getProperty("spartanApiLogin");
    private static String password = ConfigurationReader.getProperty("spartanApiPassword");
    private static int newSpartanId;
    private static SpartanGet addedSpartan;
    private static String updatedName = "Ramiz";
    private static String updatedGender = "Male";




    @DisplayName("Test add One Spartan POST/spartans endpoint")
    @Test()
    public void test_1_AddOneSpartan() {
        Spartan payload = SpartanUtil.getRandomSpartanPOJO();

        System.out.println("payload = " + payload);

                SerenityRest.given()
                        .log().uri()
                        .auth().basic(login, password)
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post("/spartans")

        ;
        addedSpartan = lastResponse().jsonPath().getObject("data", SpartanGet.class);
        newSpartanId = addedSpartan.getId();
        Ensure.that("Status Code is 201", p -> p.statusCode(201));
        Ensure.that("New spartan id is " + newSpartanId, p -> p.body("data.id", is(newSpartanId)));
        Ensure.that("Name of the new Spartan is " + payload.getName(), p -> p.body("data.name", is(payload.getName())));
        Ensure.that("the New spartan gender is " + payload.getGender(), p -> p.body("data.gender", is(payload.getGender())));
        Ensure.that("New spartans phone number is " + payload.getPhone(), p -> p.body("data.phone", is(payload.getPhone())));

        System.out.println("newSpartanId from Post= " + newSpartanId);
        System.out.println("added Post= " + addedSpartan);


    }

    @DisplayName("Test Verify the New Spartan was added GET /spartans/")
    @Test
    public void test_2_VerifyAddedSpartan() {

        System.out.println("newSpartanId = " + newSpartanId);
        System.out.println("addedSpartan = " + addedSpartan);

        SerenityRest.given()
                .auth().basic(login, password)
                .pathParam("spartan_id", newSpartanId)
                .when()
                .get("/spartans/{spartan_id}")
        .prettyPrint()
                ;
        Ensure.that("Status code is 200 ", p-> p.statusCode(200));
        Ensure.that("Name is "+addedSpartan.getName(), p-> p.body("name", is(addedSpartan.getName())));
        Ensure.that("Gender is "+addedSpartan.getGender(), p-> p.body("gender", is(addedSpartan.getGender())));
        Ensure.that("Phone number is "+addedSpartan.getPhone(), p-> p.body("phone", is(addedSpartan.getPhone())));

    }

    @DisplayName("Test Update the New Spartan was added Put /spartans/")
    @Test
    public void test_3_UpdateSpartan() {

        Spartan payload = SpartanUtil.getRandomSpartanPOJO();

        System.out.println("payload = " + payload);

        SerenityRest.given()
                .log().uri()
                .auth().basic(login, password)
                .pathParam("spartan_id", newSpartanId)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/spartans/{spartan_id}")

        ;
//        Ensure.that("Status code is 200 ", p-> p.statusCode(204));
//        Ensure.that("Name is "+payload.getName(), p-> p.body("name", is(payload.getName())));
//        Ensure.that("Gender is "+payload.getGender(), p-> p.body("gender", is(payload.getGender())));
//        Ensure.that("Phone number is "+payload.getPhone(), p-> p.body("phone", is(payload.getPhone())));

    }

    @DisplayName("Test Verify Spartan is Updated GET /spartans/{id}")
    @Test
    public void test_4_VerifySpartanUpdated() {


        SerenityRest.given()
                .auth().basic(login, password)
                .pathParam("spartan_id", newSpartanId)
                .when()
                .get("/spartans/{spartan_id}")
                .prettyPrint()
        ;
//        Ensure.that("Status code is 200 ", p-> p.statusCode(200));
//        Ensure.that("Name is "+addedSpartan.getName(), p-> p.body("name", is(addedSpartan.getName())));
//        Ensure.that("Gender is "+addedSpartan.getGender(), p-> p.body("gender", is(addedSpartan.getGender())));
//        Ensure.that("Phone number is "+addedSpartan.getPhone(), p-> p.body("phone", is(addedSpartan.getPhone())));

    }


}
