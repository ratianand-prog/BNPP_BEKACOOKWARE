package BekaCookware;

        import io.restassured.RestAssured;
        import org.testng.annotations.Test;

        import static io.restassured.RestAssured.*;
        import static org.hamcrest.Matchers.*;

public class TestNG_APITest {

    @Test(groups = "smoke", description = "Basic API test to validate the status code 200")
    public void validateHomePageResponse() {
        RestAssured.baseURI = "https://www.beka-cookware.com";

        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .time(lessThan(3000L));
    }
}