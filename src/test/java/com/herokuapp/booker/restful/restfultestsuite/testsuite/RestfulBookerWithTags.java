package com.herokuapp.booker.restful.restfultestsuite.testsuite;

import com.herokuapp.booker.restful.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Author - Sarvat Shaikh
 * Project Name: restfulbooker-serenity-cucumber-project
 */

@RunWith(SerenityRunner.class)
public class RestfulBookerWithTags extends TestBase {

    @WithTag("restfulbooker:NEGATIVE")
    @Title("This method will get error code 404 when a method is use without providing resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given().log().all()
                .when()
                .patch()
                .then()
                .statusCode(404).log().all();
    }

    @WithTags({@WithTag("restfulbooker:NEGATIVE"),
            @WithTag("restfulbooker:REGRESSION")})
    @Title("This test will provide an error code 403 when user doesn't provide authentication")
    @Test
    public void authenticationMissing() {
        SerenityRest.rest().
                given().log().all()
                .when().delete("/id")
                .then().log().all().statusCode(403);
    }

    @WithTags({@WithTag("restfulbooker:POSITIVE"),
            @WithTag("restfulbooker:REGRESSION")})
    @Title("This test will return a correct response code 200 when GET request is used to get booking ids")
    @Test
    public void verifyingStatusCodeIsCorrect() {
        SerenityRest.given()
                .when()
                .get()
                .then().log().all().statusCode(200);
    }
}
