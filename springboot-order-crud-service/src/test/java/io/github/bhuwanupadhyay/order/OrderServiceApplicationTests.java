package io.github.bhuwanupadhyay.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.http.ContentType.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceApplicationTests {

    @LocalServerPort
    private int port;

    private RequestSpecification http;

    @Before
    public void setUp() {
        this.http = RestAssured
                .given()
                .log()
                .all()
                .port(port)
                .accept(JSON)
                .contentType(JSON);
    }

    @Test
    public void canCreateOrder() {
        http.body(order("ITM-1", "1", UUID.randomUUID().toString()))
                .post("/orders")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @SneakyThrows
    private Order order(String itemId, String customerId, String orderId) {
        return new ObjectMapper().readValue(
                "{\n" +
                        "    \"orderId\": \"" + orderId + "\",\n" +
                        "    \"customerId\": \"" + customerId + "\",\n" +
                        "    \"deliveryAddress\": {\n" +
                        "      \"address\": \"JLT, Dubai, UAE\"\n" +
                        "    },\n" +
                        "    \"orderLines\": [\n" +
                        "      {\n" +
                        "        \"itemId\": \"" + itemId + "\",\n" +
                        "        \"quantity\": 10\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }",
                Order.class
        );

    }
}
