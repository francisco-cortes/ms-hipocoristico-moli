package com.baz.hipocoristico.filter;

import com.baz.hipocoristico.utilis.Constantes;
import com.baz.log.LogServicio;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * <b>ConsultaBarriFilter</b>
 * @descripcion: Filtra la petición http.
 * @autor: Francisco Javier Cortes Torre, Desarrollador
 * @ultimaModificacion: 13/10/2022
 */
@Provider
@PreMatching
public class HipocoristicoFilter implements ContainerRequestFilter {

  private static final String RUTA_MONITOREO = "/remesas/hipocoristico/estado";
  private static final String RUTA_HIPOCORISTICO = "/remesas/hipocoristico/buscar-hipocoristico";

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    String rutaConsultada = requestContext.getUriInfo().getPath();
    LogServicio log = new LogServicio();
    String nombreClaseMetodo  = "HipocoristicoFilter-filter";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    switch (rutaConsultada){
      case RUTA_HIPOCORISTICO:
        log.registrarMensaje(nombreClaseMetodo,"Consultado hipocoristico");
        break;
      case RUTA_MONITOREO:
        log.registrarMensaje(nombreClaseMetodo,"Consultado monitoreo");
        break;
      default:
        log.registrarMensaje(nombreClaseMetodo,"NO existe ruta");
        log.terminarTiempoMetodo(nombreClaseMetodo);
        return;
    }

    log.terminarTiempoMetodo(nombreClaseMetodo);
  }
}
