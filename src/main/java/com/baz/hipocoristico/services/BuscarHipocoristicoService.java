package com.baz.hipocoristico.services;

import com.baz.log.LogServicio;
import com.baz.hipocoristico.daos.ConsultarHipocorsiticoDao;
import com.baz.hipocoristico.dtos.HipocoristicoResponseDto;
import com.baz.hipocoristico.exceptions.ErrorInternoExepcion;
import com.baz.hipocoristico.utilis.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * <b>BusccarCadenasDiccionario</b>
 * @descripcion: Clase que contiene los métodos a implementar para el consumo del modulo.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 31/05/22
 */

@Singleton
public class BuscarHipocoristicoService {
  private static final Pattern ESPACIOS_REGEX = Pattern.compile("\\s+");
  private static final String NOMBRE_CLASE = "BuscarHipocoristicoService";
  /*
  inyeccion del dao para consultar sp
   */
  @Inject
  private ConsultarHipocorsiticoDao consultarHipocorsiticoDao;

  /*
  variable de control para varias consultas
   */
  private int hipocoristicosEncontrados = 0;
  /*
  mensaje hipocoristicos
   */
  private String mensaje = "";
  /*
  detalles excepcion
   */
  private String detalles = "";

  /**
   * <b>iniciaBuscar</b>
   * @descripcion: Inicio de la estructura que busca hipocoristicos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: nombres, arreglo de nombres de una persona
   * @param: apellido, arreglo de apellido de una persona
   * @param: arregloCompleto, conjunto de nombres y apellidos
   * @ultimaModificacion: 13/10/22
   */

  public HipocoristicoResponseDto iniciaBuscar(String[] nombres, String[] apellidos, String[] arregloCompleto){
    LogServicio log = new LogServicio();
    StringBuilder cadenaNombres = new StringBuilder();
    StringBuilder cadenaApellidos = new StringBuilder();
    final String NOMBRE_METODO = "iniciaBuscar";
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO,Constantes.NOMBRE_MS);
    log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO,"Recibido: " + arregloCompleto.length +
      " de nombres");
    for(int i = 0; i < nombres.length; i++){
      cadenaNombres.append(nombres[i]);
      cadenaNombres.append(" ");
    }
    for(int j = 0; j < apellidos.length; j++){
      cadenaApellidos.append(apellidos[j]);
      cadenaApellidos.append(" ");
    }
    log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO, nombres.length + " Nombres: " +
      cadenaNombres + " " + apellidos.length + " Apellidos: " + cadenaApellidos);
    /*
    se incia en 0 para cada consulta
     */
    hipocoristicosEncontrados = 0;
    /*
    Variables iniciadas para la respuesta
     */
    String[] nombreRes = new String[nombres.length];
    String[] apellidoRes = new String[apellidos.length];
    String[] resultadoBusqueda;
    try {
      /*
      busca en el arraglo los hipocoristicos
       */
      resultadoBusqueda = buscarNombres(arregloCompleto, log, nombres, apellidos);
      setDetalles(Constantes.MENSAJE_EXITO);
    }
    catch (Exception e) {
      /*
      solo existen exepciones sql
       */
      setDetalles(e.getMessage());
      setMensaje("SQL error");
      log.registrarExcepcion(e,"Error SQL");
      log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO,e.getMessage());
      /*
      arroja respuesta cotrolada a traves del controlador de exepciones
       */
      throw new ErrorInternoExepcion(Constantes.HTTP_500,getMensaje(),getDetalles(),nombres,apellidos);
    }
    /*
    parte el arreglo en 2 para nombres y apellidso
     */
    System.arraycopy(resultadoBusqueda, 0, nombreRes, 0, nombres.length);
    System.arraycopy(resultadoBusqueda,nombres.length,apellidoRes,0,apellidos.length);

    /*
    retorna la respuesta exitosa
     */
    log.obtenerTiempoTotal(NOMBRE_CLASE+NOMBRE_METODO);
    log.terminarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO);
    return new HipocoristicoResponseDto(
      Constantes.HTTP_200,nombreRes,apellidoRes,getMensaje(),getDetalles());
  }

  /**
   * <b>buscarNombres</b>
   * @descripcion: recorre el arreglo dado invocando el metodo buscar en tabla y reassigna valores dentro del arreglo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 01/06/22
   */
  private String[] buscarNombres(String[] arregloCompleto, LogServicio log, String[] nombres, String[] apellido)
    throws SQLException {

    final String NOMBRE_METODO = "buscarNombres";
    /*
    variables de iteracion de arreglo
     */
    int posicionArreglo = 0;
    int auxiliar = 0;
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO,Constantes.NOMBRE_MS);

    while (posicionArreglo<arregloCompleto.length){

      if (buscarEspaciosCadena(arregloCompleto[posicionArreglo])) {

        String[] cadenaSeparada = subCadenaSeparada(arregloCompleto[posicionArreglo]);

        while (auxiliar<cadenaSeparada.length){
          cadenaSeparada[auxiliar] = buscarEnTabla(cadenaSeparada[auxiliar],log, nombres, apellido);
          auxiliar ++;
        }

        arregloCompleto[posicionArreglo] = unirSubCadena(cadenaSeparada);

      }
      else {

        arregloCompleto[posicionArreglo] = buscarEnTabla(arregloCompleto[posicionArreglo],log, nombres, apellido);

      }

      posicionArreglo ++;

    }
    log.terminarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO);
    return arregloCompleto;

  }

  /**
   * <b>buscarEnTabla</b>
   * @descripcion: ejecuta consulta a sp con un String como parametro actualiza
   * mensaje dependiendo de parametros mesajeActual y HipocoristicosEncotrados
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 01/06/22
   */

  private String buscarEnTabla(String nombreBuscado, LogServicio log, String[] nombres, String[] apellidos)
    throws SQLException {
    final String NOMBRE_METODO = "buscarEnTabla";
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO, Constantes.NOMBRE_MS);
    String respuestaSp;
    String busquedaTabla = consultarHipocorsiticoDao.ejecutarSp(nombreBuscado.toUpperCase(),log, nombres, apellidos);

    if(busquedaTabla.isBlank() || busquedaTabla.isEmpty() || Constantes.SP_RESPUESTA_VACIA.equals(busquedaTabla)){
      respuestaSp = nombreBuscado;
      if(hipocoristicosEncontrados == 0){
        setMensaje(Constantes.CERO_HIPOCORISTICOS);
      }
      else if (hipocoristicosEncontrados == 1) {
        setMensaje(Constantes.UN_HIPOCORISTICO);
      }
      else {
        setMensaje(Constantes.DOS_HIPOCORISTICO);
      }
    }
    else {
      hipocoristicosEncontrados++;
      if (hipocoristicosEncontrados == 1) {
        respuestaSp = busquedaTabla;
        setMensaje(Constantes.UN_HIPOCORISTICO);
      }
      else {
        respuestaSp = nombreBuscado;
        setMensaje(Constantes.DOS_HIPOCORISTICO);
      }
    }

    log.terminarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO);
    return respuestaSp;
  }

  /**
   * <b>unirSubCadena</b>
   * @descripcion: une un arreglo de cadenas en una varialbe String eliminado caracteres basura
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 01/06/22
   */
  private String unirSubCadena(String[] cadsep){
    String cadenaUnida = Arrays.toString(cadsep);
    cadenaUnida = cadenaUnida.replace("[","");
    cadenaUnida = cadenaUnida.replace(",","");
    cadenaUnida = cadenaUnida.replace("]","");
    return cadenaUnida;
  }

  /**
   * <b>buscarEspaciosCadena</b>
   * @descripcion: busca si la cadena contiene un espacio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 01/06/22
   */
  private boolean buscarEspaciosCadena(String cadenaAnalizada){
    return cadenaAnalizada.contains(" ");
  }

  /**
   * <b>subCadenaSeparada</b>
   * @descripcion: separa una cadena en un arreglo de cadenas apartir de cada espacio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 01/06/22
   */
  private String[] subCadenaSeparada(String cadenaAnalizada){
    return ESPACIOS_REGEX.split(cadenaAnalizada);
  }

  /**
   * <b>getMensaje</b>
   * @descripcion: getter para mensaje
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 04/10/22
   */

  private String getMensaje(){
    return mensaje;
  }

  /**
   * <b>getDetalles</b>
   * @descripcion: getter para detalles
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */

  private String getDetalles(){
    return detalles;
  }

  /**
   * <b>setMensaje</b>
   * @descripcion: settter para mensaje
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */

  private void setMensaje(String mensajeActual){
    mensaje = mensajeActual;
  }

  /**
   * <b>setDetalles</b>
   * @descripcion: setter par detalles
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */
  private void setDetalles(String excepcionMensaje){
    detalles = excepcionMensaje;
  }

}
