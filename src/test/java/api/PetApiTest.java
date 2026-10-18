package api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class PetApiTest {

    @Test
    public void getAvailablePetsTest() {

        RestAssured
                .given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPetTest() {

        String requestBody = "{\"id\": 123456, \"name\": \"DenizPet\", \"status\": \"available\"}";

        RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePetTest() {

        String requestBody = "{\"id\": 123456, \"name\": \"UpdatedDenizPet\", \"status\": \"available\"}";

        RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200);
    }

    @Test
    public void deletePetTest() {

        RestAssured
                .given()
                .when()
                .delete("https://petstore.swagger.io/v2/pet/123456")
                .then()
                .statusCode(200);
    }

    @Test
    public void getPetByIdTest() {
        RestAssured
                .given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/10")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .time(lessThan(3000L))
                .body("id", equalTo(10));
    }

    @Test
    public void getPetResponseValidationTest() {
        RestAssured
                .given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/10")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .time(lessThan(3000L))
                .body("id", equalTo(10));
    }
}