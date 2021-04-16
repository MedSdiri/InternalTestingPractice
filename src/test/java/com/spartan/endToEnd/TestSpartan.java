package com.spartan.endToEnd;

import com.pojo.Spartan;
import com.pojo.SpartanGet;
import com.utilities.SpartanBaseTest;
import com.utilities.SpartanUtil;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;

@SerenityTest
public class TestSpartan extends SpartanBaseTest {
    private String login = "admin";
    private String password = "admin";
    public static int newSpartanId;
    public static SpartanGet addedSpartan;


    @DisplayName("Test add One Spartan POST/spartans endpoint")
    @Test
    public void addOneSpartanTest(){
        Spartan payload = SpartanUtil.getRandomSpartanPOJO();

        System.out.println("payload = " + payload);

        SerenityRest.given()
                .auth().basic(login, password)
                .contentType(ContentType.JSON)
                .body(payload)
                .log().uri()
                .when()
                .post("/spartans")
                ;
        addedSpartan = lastResponse().jsonPath().getObject("data", SpartanGet.class);
        newSpartanId = addedSpartan.getId();
        Ensure.that("Status Code is 201", p-> p.statusCode(201));
        Ensure.that("New spartan id is "+newSpartanId, p-> p.body("data.id", is(newSpartanId)));
        Ensure.that("Name of the new Spartan is "+payload.getName() , p-> p.body("data.name", is(payload.getName())));
        Ensure.that("the New spartan gender is "+payload.getGender(), p-> p.body("data.gender", is(payload.getGender())));
        Ensure.that("New spartans phone number is "+payload.getPhone(), p-> p.body("data.phone", is(payload.getPhone())));

        System.out.println("newSpartanId = " + newSpartanId);
        System.out.println("added = " + addedSpartan);

    }

    @DisplayName("Test Verify the New Spartan was added GET /spartans/")
//    @ParameterizedTest
//    @MethodSource("getNewSpartanId")
    @Test
    public void oneSpartanAddedTest(){
        System.out.println("newSpartanId = " + newSpartanId);
        System.out.println("addedSpartan = " + addedSpartan);
//        SerenityRest.given()
//                .auth().basic(login, password)
//                .pathParam("spartan_id", newSpartanId)
//                .log().uri()
//                .when()
//                .get("/spartans/{spartan_id}")
//                ;
//        System.out.println("newSpartanId = " + newSpartanId);
//        Ensure.that("Status code is 200", p-> p.statusCode(200));

    }


}
