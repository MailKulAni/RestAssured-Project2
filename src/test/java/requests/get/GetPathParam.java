package requests.get;

import io.restassured.*;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class GetPathParam {

    @BeforeTest
    public void setUri(){
        System.out.println("in before test...");
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api";
    }

    @Test
    public void getUsers(){
        System.out.println("in get users test...");

        String response =
        given()
                .queryParam("page", "x")
                .log().all()
        .when()
                .get("/users")
        .then()
                .log().all()
                .assertThat()
                    .statusCode(200)
                    .body("total", equalTo(12))
                .extract().response().asString();

        extractResponse(response);
        }

    public static void extractResponse(String response){
        JsonPath js = new JsonPath(response);
        String total_pages  = js.getString("total_");
        System.out.println("-----------------------------------");
        System.out.println("total_pages : "+total_pages);
    }
}