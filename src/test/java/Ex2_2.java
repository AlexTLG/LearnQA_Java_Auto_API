import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.http.Headers;

import java.util.HashMap;
import java.util.Map;

public class Ex2_2 {
    @Test
    public void long_redirect_1() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String locationHeader = response.getHeader("location");
        System.out.println(locationHeader);

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);


    }
}
