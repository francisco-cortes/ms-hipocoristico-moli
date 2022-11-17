package com.elektra.hipocoristico.controlador;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/**
 * <b>PruebaEstadoHipocoristico</b>
 * @descripcion: Prueba del endpoint de status
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 16/11/22
 */
@QuarkusTest
public class PruebaEstadoHipocoristico {
  /**
   * <b>probarEstado</b>
   * @descripcion: Prueba generar un UID
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 16/11/22
   */
  @Test
  public void probarEstado(){
    given()
          .when().get("/datos/hipocoristico/status")
          .then()
                .statusCode(200)
                .body("mensaje",equalTo("OK"));
  }
}
