package com.restful.booker.bookinginfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class BookingSteps {
    static String token;

    @Step("Creating new booking with firstname: {0}, lastname: {1}, totalprice: {2}" +
            " depositpaid: {3}, bookingdates: {4}, additionalneeds: {5}")

    public ValidatableResponse createBooking(String firstname,
                                             String lastname,
                                             int totalprice,
                                             boolean depositpaid,
                                             HashMap<String, Object> bookingdates,
                                             String additionalneeds) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname,
                lastname, totalprice,
                depositpaid, bookingdates, additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(bookingPojo)
                .post()
                .then();
    }

//    @Step("Getting the Student information with firstName : {0}")
//    public HashMap<String, Object> getStudentInfoByFirstName(String firstname) {
//        String s1 = "booking.findAll{it.firstname == '";
//        String s2 = "'}.get(0)";
//        return SerenityRest.given()
//                .when()
//                .get()
//                .then().statusCode(200)
//                .extract()
//                .path(s1 + firstname + s2);
//    }

    @Step("Getting booking information with bookingId: {0}")
    public ValidatableResponse getBookingInfoById(int bookingId) {
        //find the new record by id
        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                //.contentType(ContentType.JSON)
                .pathParam("bookingId", bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();


    }

    @Step("Updating a booking record by Id with firstname: {0}, lastname: {1}, totalPrice{2}, depositPaid: {3}," +
            "bookingDates: {4}, additionalNeeds: {5}, bookingId: {6} ")
    public ValidatableResponse updateBookingRecordById(String firstname, String lastname, int totalprice,
                                                       boolean depositpaid, HashMap<String, Object> bookingdates,
                                                       String additionalneeds, int bookingId) {

        //creating a new auth token everytime an update request is given
        token = "token=" + AuthPojo.getAuthToken();

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid,
                bookingdates, additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("cookie", token)
                .pathParam("bookingId", bookingId)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_SINGLE_BOOKING_BY_ID)
                .then()
                .statusCode(200)
                .log().all();
    }

    public ValidatableResponse updateBookingPartiallyById(String firstname, String lastname, int totalprice,
                                                          boolean depositpaid, HashMap<String, Object> bookingdates,
                                                          String additionalneeds, int bookingId) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("cookie", token)
                .body("{ \"firstname\": \"updated_AfterPatch\" }")
                .pathParam("bookingId", bookingId)
                .when()
                .patch(EndPoints.UPDATE_SINGLE_BOOKING_BY_ID)
                .then()
                .statusCode(200)
                .log().all();
    }
    @Step("Deleting the booking with bookingid: {0}")
    public ValidatableResponse deleteBooking(int bookingId) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Cookie", token)
                .pathParam("bookingId", bookingId)
                .when()
                .delete(EndPoints.DELETE_SINGLE_BOOKING_BY_ID)
                .then();
    }

    @Step("Getting all the booking information")
    public ValidatableResponse getAllBookingInfo() {
        return SerenityRest.given()
                .when()
                .get()
                .then();
    }


}

