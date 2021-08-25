import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class Test_02_POST {
    @Test
    public void testUserCreationAndValidStatusCode() {

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("name", "KAMINI");
        jsonRequest.put("job", "LEAD_QA");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonRequest.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().statusCode(201 );

    }
    
     @Test
    public void testUserLoginUnsuccefulAndStatusCode() {

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", "peter@klaven");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonRequest.toJSONString()).
                when().
                post("https://reqres.in/api/login").
                then()
                .assertThat()
                .statusCode(400 )
                .body("error", Matchers.equalTo("Missing password"))
                .log().all();

    }

    }
