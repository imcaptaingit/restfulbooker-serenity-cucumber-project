package com.herokuapp.booker.restful.restfultestsuite.testsuite;

import com.herokuapp.booker.restful.model.BookingPojo;
import com.herokuapp.booker.restful.restfulinfo.RestfulSteps;
import com.herokuapp.booker.restful.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Author - Sarvat Shaikh
 * Project Name: restfulbooker-serenity-cucumber-project
 */
@UseTestDataFrom(("src/test/java/resources/testdata/restfulbooker.csv"))
@RunWith(SerenityParameterizedRunner.class)
public class RestfulBookerDataDrivenTest extends TestBase {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String checkin;
    private String checkout;
    private String additionalneeds;

    @Steps
    RestfulSteps restfulSteps;

    @Title("Data driven test for creating multiple bookings")
    @Test
    public void createMultipleBookings() {
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        restfulSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds).statusCode(200);
    }
}
