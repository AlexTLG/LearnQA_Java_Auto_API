import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.http.Headers;

import java.util.HashMap;
import java.util.Map;

public class Ex1_2 {

    @Test
    public void testRestAssured() {
        Map<String, String> params = new HashMap<>();
        params.put("message", "And this is a second message");
        params.put("timestamp", "2021-06-04 16:41:51");

        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .when()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        String message = response.get("message");
        if (message == null){
            System.out.println("This message is empty");
            } else {
            System.out.println(message);
        }

    }
}
