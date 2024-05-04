import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class Ex4_2 {
    @Test
    public void testGotToken() throws InterruptedException {

        String jobUrl = "https://playground.learnqa.ru/ajax/api/longtime_job";
        JsonPath response = RestAssured
                .get(jobUrl)
                .jsonPath();

        String token = response.get("token");
        int time = response.get("seconds");
        System.out.println("Token:" + token);
        System.out.println("Time:" + time);


        Map<String, String> params = new HashMap<>();
        params.put("token", token);

        JsonPath response1 = RestAssured
                .given()
                .queryParams(params)
                .get(jobUrl)
                .jsonPath();

        String text = response1.get("status");
        assertEquals("Job is NOT ready", text);


        Thread.sleep(time * 1000);

        JsonPath response2 = RestAssured
                .given()
                .queryParams(params)
                .get(jobUrl)
                .jsonPath();

        String result = response2.get("result");
        String resultStatus = response2.get("status");

        Validate.notNull(result);
        assertEquals("Job is ready", resultStatus);

        System.out.println("Result:" + result);
        System.out.println(resultStatus);
    }
}