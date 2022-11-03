package com.elektra.hipocoristico.servicios;

import com.baz.excepciones.InternalServerErrorException;
import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.modelos.Resultado;
import com.elektra.hipocoristico.util.UtilidadCadenas;
import com.elektra.hipocoristico.util.UtilidadGenerarExcepcion;
import com.baz.log.LogServicio;
import com.elektra.hipocoristico.dao.DaoConsultaHipocorsitico;
import com.elektra.hipocoristico.dto.DtoRespuestaHipocoristico;
import com.elektra.hipocoristico.util.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Stream;

/**
 * <b>BusccarCadenasDiccionario</b>
 * @descripcion: Clase que contiene los métodos a implementar para el consumo del modulo.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 31/05/22
 */

@Singleton
public class ServicioBuscaHipocoristico {

  private int contadorHipocoristico;
  /*
  inyección del dao para consultar sp
   */
  @Inject
  private DaoConsultaHipocorsitico daoConsultaHipocorsitico;

  @Inject
  private UtilidadGenerarExcepcion utilidadGenerarExcepcion;

  /**
   * <b>iniciaBuscar</b>
   * @descripcion: Inicio de la estructura que busca hipocoristicos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: arregloCompleto, conjunto de nombres y apellidos
   * @ultimaModificacion: 13/10/22
   */

  public DtoRespuestaHipocoristico iniciarBuscar(DtoPeticionHipocoristico peticion, String uid){
    contadorHipocoristico = 0;
    String nombreClaseMetodo = "ServicioBuscaHipocoristico-iniciarBuscar";
    LogServicio log = new LogServicio();
    log.iniciarTiempoMetodo(nombreClaseMetodo,Constantes.NOMBRE_MS);

    Resultado resultado = new Resultado(uid, Constantes.CODIGO_ERROR_GENERAL,
      Constantes.MENSAJE_CODIGO_500);

    StringBuilder cadenaNombres = new StringBuilder();
    StringBuilder cadenaApellidos = new StringBuilder();
    DtoRespuestaHipocoristico respuesta = null;

    try {
      /*
      obtiene la cantidad de strings del arreglo nombre
      */
      String[] nombres = peticion.getNombres();
      /*
      obtiene la cantidad de string del arreglo apellido
      */
      String[] apellidos = peticion.getApellidos();
      /*
      une los arreglos para iterar después
      */
      String[] arregloCompleto= Stream.of(peticion.getNombres(), peticion.getApellidos())
        .flatMap(Stream::of).toArray(String[]::new);

      log.registrarMensaje(nombreClaseMetodo  ,"Recibido: " + arregloCompleto.length +
        " de nombres");

      for (String nombre : nombres) {
        cadenaNombres.append(nombre);
        cadenaNombres.append(" ");
      }
      for (String apellido : apellidos) {
        cadenaApellidos.append(apellido);
        cadenaApellidos.append(" ");
      }
      log.registrarMensaje(nombreClaseMetodo, nombres.length + " Nombres: " +
        cadenaNombres + " " + apellidos.length + " Apellidos: " + cadenaApellidos);
      /*
      Variables iniciadas para la respuesta
      */
      String[] nombreRes = new String[nombres.length];
      String[] apellidoRes = new String[apellidos.length];
      String[] resultadoBusqueda = new String[0];
      /*
      busca en el arreglo los hipocorísticos
       */
      resultadoBusqueda = buscarNombres(arregloCompleto, resultado, log);
      /*
      parte el arreglo en 2 para nombres y apellidos
      */
      System.arraycopy(resultadoBusqueda, 0, nombreRes, 0, nombres.length);
      System.arraycopy(resultadoBusqueda,nombres.length,apellidoRes,0,apellidos.length);

      if(contadorHipocoristico > 1) {
        resultado.setCodigo(Constantes.CODIGO_DOS_HIPOCORISTICO);
        resultado.setMensaje(Constantes.DOS_HIPOCORISTICO);

        utilidadGenerarExcepcion.generarExcepcion(Constantes.CODIGO_HTTP_500, resultado.getCodigo(),
          resultado.getMensaje() , uid);
      }
      else {
        if(contadorHipocoristico == 1){
          respuesta = new DtoRespuestaHipocoristico(
            Constantes.CODIGO_HTTP_200, nombreRes, apellidoRes, Constantes.CODIGO_UN_HIPOCORISTICO
            + Constantes.UN_HIPOCORISTICO);
        }
        else{
          respuesta = new DtoRespuestaHipocoristico(
            Constantes.CODIGO_HTTP_200, nombreRes, apellidoRes, Constantes.CODIGO_SIN_HIPOCORISTICO
            + Constantes.CERO_HIPOCORISTICOS);
        }
      }
    }
    catch (InternalServerErrorException excepcion){
      log.registrarExcepcion(excepcion, null);
      throw excepcion;
    }
    catch (Exception excepcion){
      log.registrarExcepcion(excepcion, Constantes.CODIGO_ERROR_SQL);
      /*
      arroja respuesta controlada a través del controlador de excepciones
       */
      utilidadGenerarExcepcion.generarExcepcion(Constantes.CODIGO_HTTP_500, resultado.getCodigo(),
        resultado.getMensaje() + " " + excepcion.getMessage(), uid);
    }
    finally {
      /*
      retorna la respuesta exitosa
      */
      log.terminarTiempoMetodo(nombreClaseMetodo);
    }
    return respuesta;
  }

  /**
   * <b>buscarNombres</b>
   * @descripcion: recorre el arreglo dado invocando el metodo buscar en tabla y reassigna valores dentro del arreglo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 01/06/22
   */
  private String[] buscarNombres(String[] arregloCompleto, Resultado resultado,LogServicio log)
    throws Exception {
    /*
    variables de iteración de arreglo
     */
    int posicionArreglo = 0;
    int auxiliar = 0;
    while (posicionArreglo<arregloCompleto.length){
      if (UtilidadCadenas.buscarEspaciosCadena(arregloCompleto[posicionArreglo])) {
        String[] cadenaSeparada = UtilidadCadenas.subCadenaSeparada(arregloCompleto[posicionArreglo]);
        while (auxiliar<cadenaSeparada.length){
          cadenaSeparada[auxiliar] = buscarEnTabla(cadenaSeparada[auxiliar], resultado, log);
          auxiliar ++;
        }
        arregloCompleto[posicionArreglo] = UtilidadCadenas.unirSubCadena(cadenaSeparada);
      }
      else {
        arregloCompleto[posicionArreglo] = buscarEnTabla(arregloCompleto[posicionArreglo], resultado, log);
      }
      posicionArreglo ++;
    }
    return arregloCompleto;
  }

  /**
   * <b>buscarEnTabla</b>
   * @descripcion: ejecuta consulta a sp con un String como parametro actualiza
   * mensaje dependiendo de parametros mensajeActual y HipocorísticosEncontrados
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 01/06/22
   */

  private String buscarEnTabla(String nombreBuscado, Resultado resultado,LogServicio log)
    throws Exception {
    String respuestaDiccionario = daoConsultaHipocorsitico.buscarDiccionario(nombreBuscado.toUpperCase(),
      resultado, log);
    if(Constantes.SP_RESPUESTA_VACIA_SP.equals(respuestaDiccionario)){
      return nombreBuscado;
    }
    else {
      contadorHipocoristico++;
      return respuestaDiccionario;
    }
  }

}
