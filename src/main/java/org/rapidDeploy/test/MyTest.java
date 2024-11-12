package org.rapidDeploy.test;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MyTest {

Response response;

    @Test
    @Description("As an API user, I would like to get all currencies")
    public void getCurrency() {
        response = given().
                when().
                get("https://v6.exchangerate-api.com/v6/1fc80820c72b0163bc9c7536/latest/USD").
                then().
                log().all().
                assertThat().statusCode(200).
                extract().response();

        System.out.println("The response code is " + response.statusCode());
        JSONObject conversionRates = new JSONObject(response.asString()).getJSONObject("conversion_rates");

        int totalCurrencies = conversionRates.length();
        System.out.println("Total number of currencies: " + totalCurrencies);
        assertThat(conversionRates.has("GBP"), is(true));
    }
}
