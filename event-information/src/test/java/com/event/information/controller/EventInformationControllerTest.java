package com.raju.event.information.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.post;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventInformationControllerTest {

  @LocalServerPort private Integer port;

  @Test
  @DisplayName("EventInformationJob")
  public void testRunSaveEventInformationJob() {
    String batchStatus =
        post("http://localhost:" + port + "/job")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(String.class);

    assertEquals("COMPLETED", batchStatus, "Job status should be completed");
  }
}
