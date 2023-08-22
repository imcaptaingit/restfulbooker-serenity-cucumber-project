package com.herokuapp.booker.restful.testbase;


import com.herokuapp.booker.restful.constants.Path;
import com.herokuapp.booker.restful.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Author - Sarvat Shaikh
 * Project Name: restfulbooker-serenity-cucumber-project
 */

public class TestBase {

    public static PropertyReader propertyReader;

    @BeforeClass
    public static void inIt() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.basePath = Path.BOOKING;
    }

}