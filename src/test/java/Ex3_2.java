import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Ex3_2 {
    @Test
    public void long_redirect_2() {

        String url = "https://playground.learnqa.ru/api/long_redirect";
        int statusCode = 0;
        int counter = 0;

        while (statusCode != 200) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(url)
                    .andReturn();

            url = response.getHeader("location");
            statusCode = response.getStatusCode();
            counter++;
        }
        System.out.println(statusCode);
        System.out.println("Count redirections = " + counter);

    }
}