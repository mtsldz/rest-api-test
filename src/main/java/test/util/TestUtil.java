package test.util;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class TestUtil {

    // set header values
    public static RequestSpecification setHeader(String contentType, Object contentTypeValue) {
        return given().log().all().header(contentType, contentTypeValue);
    }

    // returns response by given path
    public static Response getResponse(String path) {
        return given().get(path);
    }

    // check response status
    public static void checkStatusCode(Response res, int expectedRes) {
        Assert.assertEquals(res.getStatusCode(), expectedRes, "Status Check Failed!");
    }

}