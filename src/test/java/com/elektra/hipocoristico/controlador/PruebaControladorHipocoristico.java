package com.elektra.hipocoristico.controlador;

import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaControladorHipocoristico {

  @Inject
  private ControladorHipocoristico controladorHipocoristico;

  @DisplayName("Prueba de Consumo")
  @Test
  public void pruebaConsumoHipocoristico(){
    String nombres[] = {"JbMcAZhekXc33Y7NMH5kpA", "9sUwFgmGz3qXpS0QnrIoRw"};
    String apellidos[] = {"0Bwh3yJGaU81NrG5_t_qUg", "tjrljQgpkY3rjr2GCXySlA"};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header("uid","UID1234456452353")
      .header("Content-Type", "application/json")
      .body(peticion)
      .post("/datos/hipocoristico/buscar-hipocoristico")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo(Constantes.CODIGO_SIN_HIPOCORISTICO+Constantes.CERO_HIPOCORISTICOS));
  }

}
