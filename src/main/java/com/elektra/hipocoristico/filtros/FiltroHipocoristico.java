package com.elektra.hipocoristico.filtros;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.UnauthorizedException;
import com.elektra.hipocoristico.modelos.Header;
import com.elektra.hipocoristico.modelos.Resultado;
import com.elektra.hipocoristico.utilidades.Constantes;
import com.elektra.hipocoristico.utilidades.UtilidadGenerarExcepcion;
import com.baz.log.LogServicio;
import com.baz.servicios.ValidarDto;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * <b>FiltroHipocoristico</b>
 * @descripcion: Filtra la petición http.
 * @autor: Francisco Javier Cortes Torre, Desarrollador
 * @ultimaModificacion: 13/10/2022
 */
@Provider
@PreMatching
public class FiltroHipocoristico implements ContainerRequestFilter {

  private static final UtilidadGenerarExcepcion UTILIDAD_GENERAR_EXCEPCION = new UtilidadGenerarExcepcion();

  /**
   * <b>filtros</b>
   * @descripcion: breve descripcion del metodo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: requestContex, Contexto de peticion
   * @ultimaModificacion: 14/10/22
   */

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    System.out.println("ENTRA AL FILTER");
    LogServicio log = new LogServicio();
    String nombreClaseMetodo  = "FiltroHipocoristico-filtros";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    String uid = requestContext.getHeaderString("uid");
    String token = requestContext.getHeaderString("token");
    ValidarDto validarDto;

    System.out.println("VALOR DE uid "+ uid);
    try {

      if (!"/remesas/hipocoristico/buscar-hipocoristico".equals(requestContext.getUriInfo().getPath())) {
        return;
      }

      System.out.println("ENTRA EN EL TRY");
      Resultado resultado = new Resultado(uid, Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO);
      validarDto = new ValidarDto();
      System.out.println("entra a validar peticion ");
      validarDto.validarPeticionAes(new Header(uid, token), resultado);
      System.out.println(resultado.getMensaje());
      System.out.println("sale de validar peticion");
      System.out.println(resultado.getCodigo());

      if (!resultado.getCodigo().equals(Constantes.CODIGO_EXITO)) {
        UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.HTTP_400, resultado.getCodigo(),
          resultado.getMensaje(), uid);
      }
      return;
    }
    catch(BadRequestException | UnauthorizedException excepcion) {
      log.registrarExcepcion(excepcion, null);
      throw excepcion;
    }
    catch(Exception excepcion){
      log.registrarExcepcion(excepcion, null);
      UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.HTTP_500, Constantes.CODIGO_ERROR_GENERAL_API,
        excepcion.getMessage(), uid);
    }
  }
}