package com.restful.booker.bookinginfo;

import com.restful.booker.testbase.TestBaseBooking;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;

//@RunWith(SerenityRunner.class)
public class BookingTagsTest extends TestBaseBooking {

    @WithTags({@WithTag("bookingfeature:SMOKE"),
            @WithTag("bookingfeature:POSITIVE")})
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .log().all();
    }

    @WithTag("bookingfeature:NEGATIVE")
    @Title("Provide a 500 status code when incorrect HTTP method is used to access resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .post()
                .then()
                .statusCode(415)
                .log().all();
    }

    @WithTags({@WithTag("bookingfeature:SMOKE"),
            @WithTag("bookingfeature:NEGATIVE")})

    @Title("This test will provide an error code of 404 when user tries to access an invalid resource")
    @Test
    public void inCorrectResource() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/products123")
                .then()
                .statusCode(404)
                .log().all();
    }






}
