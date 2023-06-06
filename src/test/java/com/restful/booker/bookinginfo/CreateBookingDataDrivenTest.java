package com.restful.booker.bookinginfo;

import com.restful.booker.testbase.TestBaseBooking;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import java.util.HashMap;
//@Concurrent(threads = "4x")
//@UseTestDataFrom("src/test/java/resources/testdata/bookinginfo.csv")
//@RunWith(SerenityParameterizedRunner.class)
public class CreateBookingDataDrivenTest  extends TestBaseBooking {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String checkin;
    private String checkout;
    private String additionalneeds;

    @Steps
    BookingSteps bookingSteps;
    @Title("Data driven test for adding multiple bookings to the application")
    @Test

    public void createMultipleBookings(){
        HashMap<String, Object> booking = new HashMap<>();
        booking.put("checkin", "2023-01-01");
        booking.put("checkout", "2023-02-01");
        bookingSteps.createBooking(firstname, lastname, totalprice,depositpaid,booking, additionalneeds).statusCode(200);


    }



}
