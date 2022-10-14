package com.baz.hipocoristico.interceptors;

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
public class HipocoristicoInterceptor implements ReaderInterceptor {

  private static final Pattern NOMBRE_REGEX =
    Pattern.compile("^(?=.{1,40}$)[a-zA-ZáéíóúüñÁÉÍÓÚÑ]+(?:[\\s][a-zA-ZáéíóúüñÁÉÍÓÚÑ]+)*$");

  @Context
  private UriInfo uri;

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context)
    throws IOException, WebApplicationException {
    System.out.println("ENTRA AL INTERCEPTOR");
    LogServicio log = new LogServicio();
    String nombreClaseMetodo = "ConsultaBarriInterceptor-aroundReadFrom";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);
    System.out.println("URI:" + uri.getPath());
    int contadorNulosNombres = 0;
    int contadorNulosApellidos = 0;
    HipocoristicoRequestDto request = (HipocoristicoRequestDto) context.proceed();

    String[] noms = request.getNombres();
    String[] aps = request.getApellidos();

    if(!(noms == null)){
      for (String nom : noms) {
        if (!NOMBRE_REGEX.matcher(nom).matches()) {
          contadorNulosNombres++;
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
