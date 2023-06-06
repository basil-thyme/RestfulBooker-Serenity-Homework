package com.restful.booker.bookinginfo;

import com.restful.booker.testbase.TestBaseBooking;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class BookingCRUDTest extends TestBaseBooking {
    static String firstname = TestUtils.getRandomText();
    static String lastname = TestUtils.getRandomText();
    static int totalprice = 300;
    static boolean depositpaid = true;
    static String additionalneeds = "Custom service";
    public static int bookingid;

    @Steps
    BookingSteps bookingSteps;

    @Title("Creating a new booking using Post method")
    @Test
    public void test001() {
        HashMap<String, Object> booking = new HashMap<>();
        booking.put("checkin", "2023-01-01");
        booking.put("checkout", "2023-02-01");

        // create new booking
        ValidatableResponse response = bookingSteps.createBooking(firstname,
                lastname, totalprice, depositpaid, booking, additionalneeds);
        // validating the new record with the status code 200
        response.log().all().statusCode(200);
        bookingid = response.log().all().extract().path("bookingid");
    }

    @Title("Verify if the booking was added to the application")
    @Test
    public void test002() {
        System.out.println("booking id is: " + bookingid);
        ValidatableResponse response = bookingSteps.getBookingInfoById(bookingid);
        response.log().all().statusCode(200);

    }

    @Title("Update the booking information")
    @Test
    public void test003() {
        firstname = firstname + "_updated";
        lastname = lastname + "_updated";
        HashMap<String, Object> booking = new HashMap<>();
        booking.put("checkin", "2023-01-01");
        booking.put("checkout", "2023-02-01");
        ValidatableResponse response = bookingSteps.updateBookingRecordById
                (firstname, lastname, totalprice, depositpaid, booking, additionalneeds, bookingid);
        //validating the response for put method
        response.statusCode(200)
                .log().all();
    }

    @Title("Partially Update the booking information")
    @Test
    public void test004() {
        HashMap<String, Object> booking = new HashMap<>();
        booking.put("checkin", "2023-01-01");
        booking.put("checkout", "2023-02-01");
        ValidatableResponse response = bookingSteps.updateBookingPartiallyById
                (firstname, lastname, totalprice, depositpaid, booking, additionalneeds, bookingid);
        //validating the response for patch method
        response.statusCode(200)
                .log().all();
    }

    @Title("Delete the booking and verify if the booking is deleted")
    @Test
    public void test005() {
        bookingSteps.deleteBooking(bookingid).statusCode(201);
        bookingSteps.getBookingInfoById(bookingid).statusCode(404);
    }

    @Title("Getting all booking info")
    @Test
    public void test006() {
        bookingSteps.getAllBookingInfo().statusCode(200);
    }
}