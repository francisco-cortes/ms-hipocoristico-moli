package com.baz.hipocoristico.filter;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.InternalServerErrorException;
import com.baz.excepciones.UnauthorizedException;
import com.baz.hipocoristico.models.Header;
import com.baz.hipocoristico.models.Resultado;
import com.baz.hipocoristico.utilis.Constantes;
import com.baz.log.LogServicio;
import com.baz.servicios.CifradorAes;
import com.baz.servicios.ValidarDto;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * <b>HipocoristicoFilter</b>
 * @descripcion: Filtra la petición http.
 * @autor: Francisco Javier Cortes Torre, Desarrollador
 * @ultimaModificacion: 13/10/2022
 */
@Provider
@PreMatching
public class HipocoristicoFilter implements ContainerRequestFilter {

  /**
   * <b>filter</b>
   * @descripcion: breve descripcion del metodo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: requestContex, Contexto de peticion
   * @ultimaModificacion: 14/10/22
   */

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    System.out.println("ENTRA AL FILTER");
    //logger
    LogServicio log = new LogServicio();
    CifradorAes cifradorAes = new CifradorAes(false);
    String nombreClaseMetodo  = "HipocoristicoFilter-filter";
    //ruta consultada
    String valorToken = requestContext.getHeaderString("token");
    String valorUid = requestContext.getHeaderString("uid");

    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    try {
      if (!"/remesas/hipocoristico/buscar-hipocoristico".equals(requestContext.getUriInfo().getPath())) {
        return;
      }
      String token = cifradorAes.desencriptarDato(valorToken);

      Resultado resultado = new Resultado();
      resultado.setUid(valorUid);
      resultado.setCodigo(Constantes.GENERAL_EXITO);
      resultado.setMensaje(Constantes.MENSAJE_EXITO);
      ValidarDto validarDto = new ValidarDto();
      validarDto.validarPeticionAes(new Header(valorUid,token), resultado);
      log.terminarTiempoMetodo(nombreClaseMetodo);

      if (!resultado.getCodigo().equals(Constantes.CODIGO_EXITO)) {
        throw new BadRequestException(Constantes.HTTP_400,
          resultado.getCodigo(),
          Constantes.MENSAJE_CODIGO_400,
          valorUid.isBlank() ? valorUid.isEmpty()
            ? "-" : valorUid : valorUid,
          Constantes.NOMBRE_MS,
          "Ocurrió un problema en la validación " + resultado.getMensaje());
      }
    }
    catch (BadRequestException | UnauthorizedException unauthorized_request) {
      log.registrarExcepcion(unauthorized_request, null);
      throw unauthorized_request;
    }
    catch (Exception e) {
      log.registrarExcepcion(e, null);
      throw new InternalServerErrorException(
        Constantes.HTTP_500,
        Constantes.GENERAL_ERROR,
        Constantes.MENSAJE_CODIGO_500,
        valorUid,
        nombreClaseMetodo,
        "Ocurrió un problema en la validación " + e.getMessage());
    }
  }
}
