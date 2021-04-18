package com.step_definitions;

import com.pojo.Spartan;
import com.pojo.SpartanGet;
import com.utilities.ConfigurationReader;
import com.utilities.SpartanBaseTest;
import com.utilities.SpartanUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.is;

@SerenityTest
public class EndToEnd_Step_DSefinition {

    private static String login = ConfigurationReader.getProperty("spartanApiLogin");
    private static String password = ConfigurationReader.getProperty("spartanApiPassword");
    private static int newSpartanId;
    private static SpartanGet addedSpartan;
    private static String updatedName = "Ramiz";
    private static String updatedGender = "Male";

    @Given("user add new spartan status code {int}")
    public void user_add_new_spartan_status_code(Integer statusCode) {
        Spartan payload = SpartanUtil.getRandomSpartanPOJO();

        System.out.println("New payload = " + payload);

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
        Ensure.that("Status Code is 201", p -> p.statusCode(statusCode));
        Ensure.that("New spartan id is " + newSpartanId, p -> p.body("data.id", is(newSpartanId)));
        Ensure.that("Name of the new Spartan is " + payload.getName(), p -> p.body("data.name", is(payload.getName())));
        Ensure.that("the New spartan gender is " + payload.getGender(), p -> p.body("data.gender", is(payload.getGender())));
        Ensure.that("New spartans phone number is " + payload.getPhone(), p -> p.body("data.phone", is(payload.getPhone())));

        System.out.println("newSpartanId from Post= " + newSpartanId);
        System.out.println("added Post= " + addedSpartan);


    }



    @Then("user get the added spartan and status code is {int}")
    public void user_get_the_added_spartan_and_status_code_is(Integer statusCode) {
        System.out.println("newSpartanId = " + newSpartanId);
        System.out.println("addedSpartan = " + addedSpartan);

        SerenityRest.given()
                .auth().basic(login, password)
                .pathParam("spartan_id", newSpartanId)
                .when()
                .get("/spartans/{spartan_id}")
                .prettyPrint()
        ;
        Ensure.that("Status code is 200 ", p-> p.statusCode(statusCode));
        Ensure.that("Name is "+addedSpartan.getName(), p-> p.body("name", is(addedSpartan.getName())));
        Ensure.that("Gender is "+addedSpartan.getGender(), p-> p.body("gender", is(addedSpartan.getGender())));
        Ensure.that("Phone number is "+addedSpartan.getPhone(), p-> p.body("phone", is(addedSpartan.getPhone())));

    }


    @Then("user update the added spartan {int}")
    public void user_update_the_added_spartan(Integer statusCode) {
        Spartan payload = SpartanUtil.getRandomSpartanPOJO();

        System.out.println("Update payload = " + payload);

        SerenityRest.given()
                .log().uri()
                .auth().basic(login, password)
                .pathParam("spartan_id", newSpartanId)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/spartans/{spartan_id}")

        ;
    }


    @And("user get and verify the update was done status code {int}")
    public void user_get_and_verify_the_update_was_done_status_code(Integer statusCode) {

        SerenityRest.given()
                .auth().basic(login, password)
                .pathParam("spartan_id", newSpartanId)
                .when()
                .get("/spartans/{spartan_id}")
                .prettyPrint()
        ;
        Ensure.that("status code is "+statusCode, p-> p.statusCode(statusCode));
    }

}
