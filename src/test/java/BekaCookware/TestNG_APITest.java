package BekaCookware;

        import io.restassured.RestAssured;
        import io.restassured.response.Response;
        import org.testng.Assert;
        import org.testng.annotations.Test;

        import static io.restassured.RestAssured.*;
        import static org.hamcrest.Matchers.*;

public class TestNG_APITest {

    @Test(groups = "api", description = "Basic API test to validate the status code 200")
    public void validateHomePageResponse() {
        RestAssured.baseURI = "https://www.beka-cookware.com";

        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .time(lessThan(3000L));
    }
    @Test(groups = "api", description = "Validate product list not empty")
    public void ValidateProductListNotEmpty() {
        Response response = given()
                .when()
                .get("https://www.beka-cookware.com/products.json");

        // 1️⃣ Validate status code
        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code is not 200");
        int productCount = response.jsonPath()
                .getList("products")
                .size();

        Assert.assertTrue(productCount > 0,
                "Product list is empty");
    }
}