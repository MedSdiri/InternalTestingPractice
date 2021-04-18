package com.utilities;

import com.github.javafaker.Faker;
import com.pojo.Spartan;
import com.pojo.SpartanGet;
import net.serenitybdd.rest.SerenityRest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SpartanUtil {

    private static Faker faker = new Faker();
    /**
     * Used to get valid map object to represent post body fro POST/ spartans request
     * @return Map object with random name, gender, phone number(5000000000 - 100000000000)
     */
    public static Map<String, Object> getRandomSpartanMap(){
        Map<String,Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName()   );
        bodyMap.put("gender", faker.demographic().sex()  );
        bodyMap.put("phone", faker.number().numberBetween(5_000_000_000L, 10_000_000_000L) ) ;
        return bodyMap;
    }
    /***
     * Create random spartan Object with random field values
     * @return spartan object with random name, gender, phone number(5000000000 - 100000000000)
     */
    public static Spartan getRandomSpartanPOJO(){
        Spartan sp = new Spartan();
        sp.setName(faker.name().firstName());
        sp.setGender(faker.demographic().sex());
        sp.setPhone(faker.number().numberBetween(5_000_000_000L, 10_000_000_000L));
        return sp;
    }

    public static int getLastSpartansId(){

        List<Integer> listOfIds =
                SerenityRest.given()
                        .auth().basic(ConfigurationReader.getProperty("spartanApiLogin"),
                        ConfigurationReader.getProperty("spartanApiPassword"))
                        //.pathParam("spartan_id", newSpartanId)
                        .log().uri()
                        .when()
                        .get("/spartans")
                        .jsonPath().getList("id", Integer.class)
                ;
        return listOfIds.get(listOfIds.size()-1);
    }

}
