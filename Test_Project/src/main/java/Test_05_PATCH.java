import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


@Test
public class Test_05_PATCH {
    public void testUserUpdationAndValidStatusCode() {

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Kamini Pandey");
        requestParams.put("job", "LEAD QA CONSULTANT");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(requestParams.toJSONString()).
                when().
                patch("https://reqres.in/api/users/2").
                then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo("Kamini Pandey"))
                .body("job", Matchers.equalTo("LEAD QA CONSULTANT")).log().all();

    }

}
