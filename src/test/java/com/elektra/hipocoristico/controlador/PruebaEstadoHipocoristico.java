package com.elektra.hipocoristico.controlador;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaEstadoHipocoristico {
  @Test
  public void pruebaEstado(){
    given()
          .when().get("/datos/hipocoristico/status")
          .then()
                .statusCode(200)
                .body("mensaje",equalTo("OK"));
  }
}
