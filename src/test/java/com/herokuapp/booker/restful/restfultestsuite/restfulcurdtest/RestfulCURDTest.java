package com.herokuapp.booker.restful.restfultestsuite.restfulcurdtest;


import com.herokuapp.booker.restful.model.BookingPojo;
import com.herokuapp.booker.restful.restfulinfo.RestfulSteps;
import com.herokuapp.booker.restful.testbase.TestBase;
import com.herokuapp.booker.restful.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

/**
 * Author - Sarvat Shaikh
 * Project Name: restfulbooker-serenity-cucumber-project
 */


@RunWith(SerenityRunner.class)
public class RestfulCURDTest extends TestBase {
    static String firstname = "Macbook" + TestUtils.getRandomValue();
    static String lastname = "Pro" + TestUtils.getRandomValue();
    static int totalprice = Integer.parseInt(1 + TestUtils.getRandomValue());
    static boolean depositpaid = true;
    static String additionalneeds = "pillow";

    static String token;
    static int id;

    @Steps
    RestfulSteps restfulSteps;

    @Title("This method will create a Token")
    @Test
    public void test001() {
        ValidatableResponse response = restfulSteps.getTokken().statusCode(200);
        token = response.extract().path("token");
    }

    @Title("This method will create and verify a booking")
    @Test
    public void test002() {
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-12-01");
        ValidatableResponse response = restfulSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds).statusCode(200);
        id = response.extract().path("bookingid");

        ValidatableResponse validate = restfulSteps.getAllBookingIDs();
        ArrayList<?> booking = validate.extract().path("bookingid");
        Assert.assertTrue(booking.contains(id));
    }

    @Title("This method will get booking with Id")
    @Test
    public void test003() {
        restfulSteps.getSingleBookingIDs(id).statusCode(200);
    }

    @Title("This method will updated a booking with ID")
    @Test
    public void test004() {
        additionalneeds = "lunch";
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-12-01");
        restfulSteps.updateBookingWithID(id, token, firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
        ValidatableResponse response = restfulSteps.getSingleBookingIDs(id);
        HashMap<String, ?> update = response.extract().path("");
        Assert.assertThat(update, hasValue("lunch"));
    }

    @Title("This method will delete a booking with ID")
    @Test
    public void test005() {
        restfulSteps.deleteABookingID(id, token).statusCode(201);
        restfulSteps.getSingleBookingIDs(id).statusCode(404);
    }

}
