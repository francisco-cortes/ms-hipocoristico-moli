package com.baz.hipocoristico.interceptors;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.InternalServerErrorException;
import com.baz.excepciones.NotFoundException;
import com.baz.hipocoristico.dtos.HipocoristicoRequestDto;
import com.baz.hipocoristico.exceptions.ErrorInternoExepcion;
import com.baz.hipocoristico.utilis.Constantes;
import com.baz.log.LogServicio;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * <b>HipocoristicoiInterceptor</b>
 * @descripcion: Intercepta la petición http antes de que llegue al controlador
 * para las inspección de la misma.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/2022
 */
@ConstrainedTo(RuntimeType.SERVER)
@Provider
public class HipocoristicoInterceptor implements ReaderInterceptor{

  /*
  regex de validacion de cadenas para nombres en español
   */
  private static final Pattern NOMBRE_REGEX =
    Pattern.compile("^(?=.{1,40}$)[a-zA-ZáéíóúüñÁÉÍÓÚÑ]+(?:[\\s][a-zA-ZáéíóúüñÁÉÍÓÚÑ]+)*$");

  /*
  inyeccion de UriInfo
   */
  @Context
  private UriInfo uri;

  /**
   * <b>aroundReadFrom</b>
   * @descripcion: breve descripción del contenido
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: context, contexto de lectura de interceptor
   * @ultimaModificacion: 14/10/22
   */
  @Override
  public final Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
    System.out.println("ENTRA AL INTERCEPTOR");
    HipocoristicoRequestDto request = null;
    LogServicio log = new LogServicio();
    String nombreClaseMetodo = "ConsultaBarriInterceptor-aroundReadFrom";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);
    String uid = context.getHeaders().getFirst("uid");
    System.out.println(uid);
    System.out.println("URI:" + uri.getPath());
    int contadorNulosNombres = 0;
    int contadorNulosApellidos = 0;

    try {
      request = (HipocoristicoRequestDto) context.proceed();
    }
    catch(BadRequestException | NotFoundException | InternalServerErrorException excepcion){
      excepcion.printStackTrace();
    }

    String[] noms = request.getNombres();
    String[] aps = request.getApellidos();

    if(!(noms == null)){
      for (String nom : noms) {
        if (!NOMBRE_REGEX.matcher(nom).matches()) {
          contadorNulosNombres++;
          System.out.println(contadorNulosNombres);
        }
      }
    }

    if(!(aps == null)){
      for (String ap : aps) {
        if (!NOMBRE_REGEX.matcher(ap).matches()) {
          contadorNulosApellidos++;
        }
      }
    }

    if(contadorNulosNombres > 0 || contadorNulosApellidos > 0 ){
      assert aps != null;
      assert noms != null;
      throw new ErrorInternoExepcion(Constantes.HTTP_500,"Hubo Nombres invalidos",
        "Nombres invalidos: " + contadorNulosNombres + " Apellidos invalidos: " + contadorNulosApellidos
        ,noms,aps);
    }

    return request;
  }
}
