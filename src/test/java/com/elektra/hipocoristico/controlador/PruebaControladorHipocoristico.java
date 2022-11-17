package com.elektra.hipocoristico.controlador;

import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/**
 * <b>PruebaControladorHipocoristico</b>
 * @descripcion: Pruebas unitarias para desde el endpoint principal.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 16/11/22
 */
@QuarkusTest
public class PruebaControladorHipocoristico {

  private static final String RUTA = "/datos/hipocoristico/buscar-hipocoristico";
  private static final String UID = "UID123456789012";
  private static final String ENCABEZADO_UID = "uid";
  private static final String TOKEN = "022DEE73F8528EA4445B133DDB5B224848B2258B";
  private static final String ENCABEZADO_TOKEN = "token";
  private static final String CONTENT = "Content-Type";
  private static final String APLICATION = "application/json";
  private static final String MENSAJE = "mensaje";
  private static final String DETALLES = "detalles";
  private static final String LEONARDO = "4Y_0J8qXzWjpKeoc2wpgkg";
  private static final String ISRAEL = "9sUwFgmGz3qXpS0QnrIoRw";
  private static final String MILLAN = "0Bwh3yJGaU81NrG5_t_qUg";
  private static final String GARCIA = "0Bwh3yJGaU81NrG5_t_qUg";
  private static final String PACO = "0Bwh3yJGaU81NrG5_t_qUg";
  private static final String PACO_PUNTO = "frfC9Y0X03iDPeCiMjsStA";

  @Inject
  private ControladorHipocoristico controladorHipocoristico;
  /**
   * <b>probarConsumoHipocoristicoSinHipocoristico</b>
   * @descripcion: Prueba de consumo donde no se encuentran hipocorísticos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 16/11/22
   */
  @DisplayName("Prueba de Consumo sin hipocoristico")
  @Test
  public void probarConsumoHipocoristicoSinHipocoristico(){
    String nombres[] = {LEONARDO, ISRAEL};
    String apellidos[] = {MILLAN, GARCIA};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_SIN_HIPOCORISTICO+Constantes.CERO_HIPOCORISTICOS));
  }
  /**
   * <b>probarConsumoHipocoristicoUnHipocoristico</b>
   * @descripcion: Prueba cuando se envía 1 hipocorístico.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 16/11/22
   */
  @DisplayName("Prueba de Consumo 1 hipocoristico")
  @Test
  public void probarConsumoHipocoristicoUnHipocoristico(){
    String nombres[] = {PACO, ISRAEL};
    String apellidos[] = {MILLAN, GARCIA};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.CODIGO_UN_HIPOCORISTICO+Constantes.UN_HIPOCORISTICO));
  }
  /**
   * <b>probarConsumoHipocoristicoDosHipocoristico</b>
   * @descripcion: Prueba de consumo cuando se envía 2 hipocorístico.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 16/11/22
   */
  @DisplayName("Prueba de Consumo 2 hipocoristico")
  @Test
  public void probarConsumoHipocoristicoDosHipocoristico(){
    String nombres[] = {PACO, PACO};
    String apellidos[] = {MILLAN, GARCIA};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(DETALLES,equalTo(Constantes.DOS_HIPOCORISTICO));
  }
  /**
   * <b>probarConsumoHipocoristicoUidIncorrecto</b>
   * @descripcion: Prueba de consumo con el un UID incorrecto
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 17/11/22
   */
  @DisplayName("Prueba de Consumo uid incorrecto")
  @Test
  public void probarConsumoHipocoristicoUidIncorrecto(){
    String nombres[] = {LEONARDO, ISRAEL};
    String apellidos[] = {MILLAN, GARCIA};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header(ENCABEZADO_UID,"UID")
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(DETALLES,equalTo(" El dato 'uid' con valor 'UID' NO es válido. " +
        "Debe tener una longitud exacta de 15 carácteres"));
  }
  /**
   * <b>probarConsumoHipocoristicoTokenIncorrecto</b>
   * @descripcion: Prueba de consumo con el token incorrecto
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 17/11/22
   */
  @DisplayName("Prueba de Consumo token incorrecto")
  @Test
  public void probarConsumoHipocoristicoTokenIncorrecto(){
    String nombres[] = {LEONARDO, ISRAEL};
    String apellidos[] = {MILLAN, GARCIA};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,"TOKEN")
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(DETALLES,equalTo(" El dato 'token' con valor 'TOKEN' NO es válido. " +
        "Debe tener una longitud exacta de 40 carácteres"));
  }
  /**
   * <b>probarConsumoHipocoristicoPeticionIncorrecta</b>
   * @descripcion: prueba de consumo con una petición incorrecta
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 17/11/22
   */
  @DisplayName("Prueba de Consumo Petición Incorrecta")
  @Test
  public void probarConsumoHipocoristicoPeticionIncorrecta(){
    String nombres[] = {PACO_PUNTO, ISRAEL};
    String apellidos[] = {MILLAN, GARCIA};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA)
      .then()
      .statusCode(200)
      .body(DETALLES,equalTo(" El dato 'nombres' con valor 'PACO.' " +
        "NO es válido.(Verifique mayúsculas y minúsculas). No debe contener los siguientes caracteres: ."));
  }
  /**
   * <b>probarConsumoHipocoristicoRecursoNoEncontrado</b>
   * @descripcion: Prueba de consumo con un recurso no encontrad
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 17/11/22
   */
  @DisplayName("Prueba de Consumo No encontrado")
  @Test
  public void probarConsumoHipocoristicoRecursoNoEncontrado(){
    String nombres[] = {LEONARDO, ISRAEL};
    String apellidos[] = {MILLAN, GARCIA};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);

    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .header(CONTENT, APLICATION)
      .body(peticion)
      .post(RUTA + "/")
      .then()
      .statusCode(200)
      .body(DETALLES,equalTo("No se encuentra el recurso: " +
        "/datos/hipocoristico/buscar-hipocoristico/ en el servicio."));
  }

}
