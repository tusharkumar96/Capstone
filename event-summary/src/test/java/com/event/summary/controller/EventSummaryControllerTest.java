package com.event.summary.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.post;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventSummaryControllerTest {

  @LocalServerPort private Integer port;

  @Test
  @DisplayName("EventSummaryJob")
  public void testRunEventSummaryJob() {
    String response =
        post("http://localhost:" + port + "/job")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(String.class);

    assertEquals("COMPLETED", response, "Job Status should be completed");
  }
}
