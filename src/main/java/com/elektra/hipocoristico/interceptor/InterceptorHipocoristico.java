package com.elektra.hipocoristico.interceptor;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.InternalServerErrorException;
import com.baz.excepciones.NotFoundException;
import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.modelos.Resultado;
import com.elektra.hipocoristico.util.Constantes;
import com.elektra.hipocoristico.util.UtilidadGenerarExcepcion;
import com.baz.log.LogServicio;
import com.baz.servicios.CifradorAes;
import com.baz.servicios.ValidacionObjeto;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 * <b>HipocoristicoiInterceptor</b>
 * @descripcion: Intercepta la petición http antes de que llegue al controlador
 * para la inspección de la misma.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/2022
 */
@ConstrainedTo(RuntimeType.SERVER)
@Provider
public class InterceptorHipocoristico implements ReaderInterceptor{

  private static final UtilidadGenerarExcepcion UTILIDAD_GENERAR_EXCEPCION = new UtilidadGenerarExcepcion();
  /*
  inyeccion de UriInfo
   */
  @Context
  private UriInfo uri;

  /**
   * <b>aroundReadFrom</b>
   * @descripcion: Lógica para la validación de la entrada
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: context, contexto de lectura de interceptor
   * @ultimaModificacion: 14/10/22
   */
  @Override
  public final Object aroundReadFrom(ReaderInterceptorContext contexto) throws WebApplicationException {
    LogServicio log = new LogServicio();
    String nombreClaseMetodo = "GeneraTokenInterceptor-aroundReadFrom";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    String uid = contexto.getHeaders().getFirst("uid");
    Resultado resultado = new Resultado(uid, Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO);
    DtoPeticionHipocoristico request = null;

    try{

      if("/datos/hipocoristico/buscar-hipocoristico".equals(uri.getPath())){
        request = (DtoPeticionHipocoristico) contexto.proceed();
        validarPeticion(request, resultado);

        if (resultado.getCodigo().equals(Constantes.CODIGO_EXITO)) {
          return request;
        }
        else {
          UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_400,
            Constantes.CODIGO_SOLICITUD_INCORRECTA,
            resultado.getMensaje(), resultado.getUid());
        }
      }
      else {
        UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_404, Constantes.CODIGO_NO_ENCONTRADO,
          "No se encuentra el recurso: " + uri.getPath() + " en el servicio.", resultado.getUid());
      }
    }
    catch(BadRequestException | NotFoundException excepcion){
      log.registrarExcepcion(excepcion, null);
      throw excepcion;
    }
    catch (Exception excepcion) {
      log.registrarExcepcion(excepcion, null);
      UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_500, Constantes.CODIGO_ERROR_GENERAL,
        excepcion.toString(), resultado.getUid());
    }
    finally{
      log.terminarTiempoMetodo(nombreClaseMetodo);
    }

    return request;
  }

  /**
   * <b>ValidarPeticion</b>
   * @descripcion: Método para validar el cuerpo de la petición.
   * @autor: Angel Eduardo Hernández Aguilar.
   * @param peticion Petición enviada.
   * @param resultado Resultado del proceso de validación.
   * @ultimaModificacion: 06/12/2021
   */
  private void validarPeticion(DtoPeticionHipocoristico peticion, Resultado resultado) throws Exception {
    CifradorAes cifrador = new CifradorAes(true);
    cifrador.desencriptarObjeto(peticion, resultado);
    if (resultado.getCodigo().equals(Constantes.CODIGO_EXITO)) {
      ValidacionObjeto validador = new ValidacionObjeto();
      validador.validarDto(peticion, resultado);
    }
    else {
      UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_500, Constantes.CODIGO_ERROR_GENERAL,
        resultado.getMensaje(), resultado.getUid());
    }
  }

}
