package com.elektra.hipocoristico.servicio;

import com.baz.excepciones.InternalServerErrorException;
import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.dto.DtoRespuestaHipocoristico;
import com.elektra.hipocoristico.servicios.ServicioBuscaHipocoristico;
import com.elektra.hipocoristico.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.wildfly.common.Assert.assertTrue;
/**
 * <b>PruebaServicioBuscaHipocoristico</b>
 * @descripcion: Pruebas unitarias sobre la lógica del microservicio
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 17/11/22
 */
@QuarkusTest
public class PruebaServicioBuscaHipocoristico {
  public static final String UID = "UID123412341332";
  private static final String[] APELLIDOS = new String[] {"MILLAN","GARCIAS"};
  @Inject
  private ServicioBuscaHipocoristico servicioBuscaHipocoristico;
  /**
   * <b>probarIniciarBusquedaIdeal</b>
   * @descripcion: Prueba buscado un hipocorístico
   * @autor: Francisco Javier Cortes Torres, Desarrollado
   * @ultimaModificacion: 17/11/22
   */
  @DisplayName("Prueba Unitaria busqueda 1 hipocoristico")
  @Test
  public void probarIniciarBusquedaIdeal(){
    String[] nombres = {"LEONARDO PACO", "ISRAEL"};
    DtoRespuestaHipocoristico resp = servicioBuscaHipocoristico.iniciarBuscar(peticion(nombres), UID);
    Assertions.assertEquals(Constantes.CODIGO_UN_HIPOCORISTICO+Constantes.UN_HIPOCORISTICO,
      resp.getMensaje());
  }
  /**
   * <b>probarIniciarBusquedaDosHipocoristicos</b>
   * @descripcion: Prueba buscado dos hipocorísticos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 17/11/22
   */
  @DisplayName("Prueba Unitaria buqueda 2 o mas hipocoristicos")
  @Test
  public void probarIniciarBusquedaDosHipocoristicos(){
    String[] nombres = {"PETE","PACO"};

    InternalServerErrorException thrown = assertThrows(
      InternalServerErrorException.class,
      () -> servicioBuscaHipocoristico.iniciarBuscar(peticion(nombres),UID),
      "2 hipocoristicos"
                                                      );
    assertTrue(thrown.getDetalles().contains(Constantes.DOS_HIPOCORISTICO));
  }

  /**
   * <b>probarIniciarBusquedaSinHipocoristicos</b>
   * @descripcion: Prueba buscado CERO hipocorístico
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 17/11/22
   */
  @DisplayName("Prueba sin hipocorisiticos")
  @Test
  public void probarIniciarBusquedaSinHipocoristicos(){
    String[] nombres = {"LEONARDO","ISRAEL MILLAN"};
    DtoRespuestaHipocoristico resp = servicioBuscaHipocoristico.iniciarBuscar(peticion(nombres),UID);
    assertEquals(Constantes.CODIGO_SIN_HIPOCORISTICO + Constantes.CERO_HIPOCORISTICOS,
      resp.getMensaje());
  }

  @DisplayName("Prueba Unitaria buqueda 2 o mas hipocoristicos")
  @Test
  public void probarIniciarBusquedaExcepcionSql(){
    String[] nombres = {"ESTAESUNACADENAMUYLARGAAAAaaaaaaaaaaaaaa","PACO"};

    InternalServerErrorException thrown = assertThrows(
      InternalServerErrorException.class,
      () -> servicioBuscaHipocoristico.iniciarBuscar(peticion(nombres),UID),
      "2 hipocoristicos");
    System.out.println(thrown.getDetalles());
    assertTrue(thrown.getDetalles().contains("Ocurrió un error al invocar la consulta hacia diccionario"));
  }


  /**
   * <b>peticion</b>
   * @descripcion: crea un objeto petición para las pruebas unitarias
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 17/11/22
   */
  private DtoPeticionHipocoristico peticion(String[] nombres){
    DtoPeticionHipocoristico req = new DtoPeticionHipocoristico();
    req.setNombres(nombres);
    req.setApellidos(APELLIDOS);
    return req;
  }

}
