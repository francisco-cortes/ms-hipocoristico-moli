package com.baz.hipocoristico.services;

import com.baz.hipocoristico.dtos.HipocoristicoRequestDto;
import com.baz.hipocoristico.models.DetallesServiceModel;
import com.baz.hipocoristico.utilis.CadenasUtil;
import com.baz.hipocoristico.utilis.GenerarExcepcionUtil;
import com.baz.log.LogServicio;
import com.baz.hipocoristico.daos.ConsultarHipocorsiticoDao;
import com.baz.hipocoristico.dtos.HipocoristicoResponseDto;
import com.baz.hipocoristico.utilis.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * <b>BusccarCadenasDiccionario</b>
 * @descripcion: Clase que contiene los métodos a implementar para el consumo del modulo.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 31/05/22
 */

@Singleton
public class BuscarHipocoristicoService {

  private static final GenerarExcepcionUtil generarExcepcionUtil = new GenerarExcepcionUtil();
  private static final DetallesServiceModel detalles = new DetallesServiceModel();
  private static final String NOMBRE_CLASE = "BuscarHipocoristicoService";
  /*
  inyeccion del dao para consultar sp
   */
  @Inject
  private ConsultarHipocorsiticoDao consultarHipocorsiticoDao;

  /**
   * <b>iniciaBuscar</b>
   * @descripcion: Inicio de la estructura que busca hipocoristicos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: nombres, arreglo de nombres de una persona
   * @param: apellido, arreglo de apellido de una persona
   * @param: arregloCompleto, conjunto de nombres y apellidos
   * @ultimaModificacion: 13/10/22
   */

  public HipocoristicoResponseDto iniciaBuscar(HipocoristicoRequestDto peticion, String uid){
    LogServicio log = new LogServicio();
    StringBuilder cadenaNombres = new StringBuilder();
    StringBuilder cadenaApellidos = new StringBuilder();
    final String NOMBRE_METODO = "iniciaBuscar";
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO,Constantes.NOMBRE_MS);
    /*
    obtiene la cantidad de strings del arreglo nombre
     */
    String[] nombres = peticion.getNombres();
    /*
    obtiene la cantida de string del arreglo apellido
     */
    String[] apellidos = peticion.getApellidos();
    /*
    une los arreglos para iterar despues
     */
    String[] arregloCompleto= Stream.of(peticion.getNombres(), peticion.getApellidos())
      .flatMap(Stream::of).toArray(String[]::new);

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
    detalles.setHipos(0);
    /*
    Variables iniciadas para la respuesta
     */
    String[] nombreRes = new String[nombres.length];
    String[] apellidoRes = new String[apellidos.length];
    String[] resultadoBusqueda = new String[0];
    try {
      /*
      busca en el arraglo los hipocoristicos
       */
      resultadoBusqueda = buscarNombres(arregloCompleto, log, nombres, apellidos, uid);
      detalles.setDetalles(Constantes.MENSAJE_EXITO);
    }
    catch (Exception e) {
      /*
      arroja respuesta cotrolada a traves del controlador de exepciones
       */
      generarExcepcionUtil.generarExcepcion(Constantes.HTTP_500,Constantes.CODIGO_ERROR_GENERAL_API,
        e.getMessage(), uid);

      log.registrarExcepcion(e,"Error SQL");
      log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO,e.getMessage());
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
      Constantes.HTTP_200,nombreRes,apellidoRes,detalles.getMensaje(),detalles.getDetalles());
  }

  /**
   * <b>buscarNombres</b>
   * @descripcion: recorre el arreglo dado invocando el metodo buscar en tabla y reassigna valores dentro del arreglo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 01/06/22
   */
  private String[] buscarNombres(String[] arregloCompleto, LogServicio log, String[] nombres,
                                 String[] apellido, String uid)
    throws SQLException {

    final String NOMBRE_METODO = "buscarNombres";
    /*
    variables de iteracion de arreglo
     */
    int posicionArreglo = 0;
    int auxiliar = 0;
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO,Constantes.NOMBRE_MS);

    while (posicionArreglo<arregloCompleto.length){

      if (CadenasUtil.buscarEspaciosCadena(arregloCompleto[posicionArreglo])) {

        String[] cadenaSeparada = CadenasUtil.subCadenaSeparada(arregloCompleto[posicionArreglo]);

        while (auxiliar<cadenaSeparada.length){
          cadenaSeparada[auxiliar] = buscarEnTabla(cadenaSeparada[auxiliar],log, nombres, apellido, uid);
          auxiliar ++;
        }

        arregloCompleto[posicionArreglo] = CadenasUtil.unirSubCadena(cadenaSeparada);

      }
      else {

        arregloCompleto[posicionArreglo] = buscarEnTabla(arregloCompleto[posicionArreglo],log, nombres,
          apellido, uid);

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

  private String buscarEnTabla(String nombreBuscado, LogServicio log, String[] nombres,
                               String[] apellidos, String uid)
    throws SQLException {
    final String NOMBRE_METODO = "buscarEnTabla";
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO, Constantes.NOMBRE_MS);
    String respuestaSp;
    String busquedaTabla = consultarHipocorsiticoDao.ejecutarSp(nombreBuscado.toUpperCase(),log,uid);

    if(busquedaTabla.isBlank() || busquedaTabla.isEmpty() || Constantes.SP_RESPUESTA_VACIA.equals(busquedaTabla)){
      respuestaSp = nombreBuscado;
      if(detalles.getHipos() == 0){
        detalles.setMensaje(Constantes.CERO_HIPOCORISTICOS);
      }
      else if (detalles.getHipos() == 1) {
        detalles.setMensaje(Constantes.UN_HIPOCORISTICO);
      }
      else {
        detalles.setMensaje(Constantes.DOS_HIPOCORISTICO);
      }
    }
    else {
      detalles.sumHipos(1);
      if (detalles.getHipos() == 1) {
        respuestaSp = busquedaTabla;
        detalles.setMensaje(Constantes.UN_HIPOCORISTICO);
      }
      else {
        respuestaSp = nombreBuscado;
        detalles.setMensaje(Constantes.DOS_HIPOCORISTICO);
      }
    }

    log.terminarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO);
    return respuestaSp;
  }

}
