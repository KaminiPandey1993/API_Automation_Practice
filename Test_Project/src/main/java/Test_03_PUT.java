import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Test_03_PUT {

    @Test
    public void testUserUpdationAndValidStatusCode() {

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "morpheus");
        requestParams.put("job", "zion resident");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(requestParams.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then().statusCode(200 ).log().all();

    }
}
