package test;

import com.restapi.test.model.Grocery;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test.base.BaseTest;
import test.util.TestUtil;

import static org.testng.Assert.assertEquals;

public class GroceryAPITest extends BaseTest {

    private Grocery grocery;

    @Test
    public void methodNotAllowedTest() {
        grocery = new Grocery(randomId, randomName, 12.75, 100);

        TestUtil.setHeader("Content-Type", "application/json")
                .body(grocery)
                .when().put(addlUrl)
                .then().assertThat().statusCode(405);
    }

    @Test
    public void endpointNotFoundTest() {
        grocery = new Grocery(randomId, randomName, 19.90, 75);

        TestUtil.setHeader("Content-Type", "application/json")
                .body(grocery)
                .when().post("/adds")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void getByNameNegativeTest() {
        Response response = TestUtil.getResponse(baseUrl + getAllUrl + "/" + randomName);
        TestUtil.checkStatusCode(response, 404);
        assertEquals(response.asString(), "Not found with name: " + randomName);
    }

    @Test
    public void getAllDetailsResponseTest() {
        Response response = TestUtil.getResponse(baseUrl + getAllUrl);
        TestUtil.checkStatusCode(response, 200);
    }

    @Test
    public void addNewItem_EmptyBodyTest() {
        TestUtil.setHeader("Content-Type", "application/json")
                .body("")
                .when().post(addlUrl)
                .then().assertThat().statusCode(400);
    }

    @Test
    public void addNewItemToGroceryTest() {
        grocery = new Grocery(randomId, randomName, 8.50, 215);

        TestUtil.setHeader("Content-Type", "application/json")
                .body(grocery)
                .when().post(addlUrl)
                .then().assertThat().statusCode(201);

        Grocery getResponse = TestUtil.getResponse(baseUrl + getAllUrl + "/" + grocery.getName()).as(Grocery.class);

        assertEquals(getResponse.getId(), grocery.getId());
        assertEquals(getResponse.getName(), grocery.getName());
        assertEquals(getResponse.getPrice(), grocery.getPrice());
        assertEquals(getResponse.getStock(), grocery.getStock());
    }

    @Test
    public void addNewItemToGrocery_ExistIdTest() {
        int randomGroceryId = getRandomNumber();
        grocery = new Grocery(randomGroceryId, randomName, 9.90, 50);

        Response postResponse = TestUtil.setHeader("Content-Type", "application/json")
                .body(grocery)
                .when().post(addlUrl);
        TestUtil.checkStatusCode(postResponse, 201);

        Response tryPostResponse =
                TestUtil.setHeader("Content-Type", "application/json")
                        .body(grocery)
                        .when().post(addlUrl);
        TestUtil.checkStatusCode(tryPostResponse, 409);

        assertEquals(tryPostResponse.asString(), "Already exists with id: " + randomGroceryId);
    }

    @Test
    public void addNewItemToGrocery_ExistNameTest() {
        int randomGroceryIdNew = getRandomNumber();

        grocery = new Grocery(randomId, randomName, 9.90, 50);
        Grocery groceryNew = new Grocery(randomGroceryIdNew, randomName, 9.90, 50);

        TestUtil.setHeader("Content-Type", "application/json")
                .body(grocery)
                .when().post(addlUrl)
                .then().assertThat().statusCode(201);

        Response response = TestUtil.setHeader("Content-Type", "application/json")
                .body(groceryNew)
                .when().post(addlUrl)
                .then().assertThat().statusCode(409)
                .extract().response();

        assertEquals(response.asString(), "Already exists with name: " + randomName);
    }


}
