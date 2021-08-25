import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;



public class Test_01_GET {

    @Test
    public void getValidStatusCodeForListOfUser() {

        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response  = httpRequest.request(Method.GET, "/12");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getValidErrorCodeStatusForListOfUser() {

        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response  = httpRequest.request(Method.GET, "/15464657");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }


    @Test
    public void getValidStatusLineForListOfUser() {

        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response  = httpRequest.request(Method.GET, "6");
        String statusLine = response.statusLine();
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK" , "Correct status code returned");

    }

    @Test
    public void testListOfUsersResponseHeader() {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response  = httpRequest.request(Method.GET, "/12");
        String contentType = response.header("Content-Type");
        System.out.println("Content-Type value: " + contentType);
    }

    @Test
    public void testIdOfUser() {

        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[0]", equalTo(7));
    }

    @Test
    public void testUserFirstNameAndLastName() {

        given().get("https://reqres.in/api/users?page=2").then().statusCode(200)
                .body("data.first_name", hasItems("Rachel","Byron","Tobias"))
                .body("data.last_name", hasItems("Howell","Fields","Funke"));


    }
}
