package test.base;

import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

public class BaseTest {

    public static final String baseUrl = "http://localhost:8080";
    public static final String getAllUrl = "/allGrocery";
    public static final String addlUrl = "/add";
    public int randomId;
    public String randomName;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @BeforeMethod
    public void setUniqueFields() {
        randomId = getRandomNumber();
        randomName = getRandomString();
    }

    public String getRandomString() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }

    public int getRandomNumber() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }


}
