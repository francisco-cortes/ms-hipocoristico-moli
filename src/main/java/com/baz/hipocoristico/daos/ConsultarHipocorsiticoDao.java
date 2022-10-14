package com.baz.hipocoristico.daos;

import com.baz.log.LogServicio;
import com.baz.hipocoristico.exceptions.ErrorInternoExepcion;
import com.baz.hipocoristico.utilis.Constantes;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * <b>ConsultarHipocoristicoDao</b>
 * @descripcion: Clase Dao principal que aloja metodos de consulta de hipocoristicos a base de datos
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 31/05/22
 */

@ApplicationScoped
@Service("ConsultarHipocoristicoDao")
@NoArgsConstructor
public class ConsultarHipocorsiticoDao {

  private static final String NOMBRE_CLASE = "ConsultarHipocoristicoDao";
  /**
   * Instancia el objeto para conexion a DB a travez de fabricaDao
   */
  @Inject
  private FabricaConexionDao fabricaConexionDao;

  /**
   * <b>ejecutarSp</b>
   * @descripcion: Ejecucuta un porcedimiento almacenado, obtiene y retorna una obejto String resultado del procedimiento.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: hipocoristico, nombre analizado
   * @param: log, instancia de logger
   * @param: nombres, arreglos de nombres de una persona
   * @param: apellidos, arreglos de apellidos de una persona
   * @ultimaModificacion: 13/10/22
   */
  @Transactional
  public String ejecutarSp(String hipocoristico, LogServicio log, String[] nombres, String[] apellidos)
    throws SQLException {
    /*
    Constantes para indices de parametros en SP
     */
    final int RESPUESTA = 1;
    final int PARAMETRO_PAIS = 2;
    final int PARAMETRO_HIPOCORSITICO = 3;
    final String NOMBRE_METODO = "ejecutarSp";
    //String de resouesta del metodo
    String resp;
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO,Constantes.NOMBRE_MS);
    // objeto de conexion sql
    Connection con = null;
    //objeto callable sql
    CallableStatement cstmt = null;

    try {
      /*
    obtiene conexion a base de datos postgres
     */
      con = fabricaConexionDao.getConexion();
      con.setAutoCommit(false);
      /*
    se declara la query para la ejecucion del sp, ((buscar forma de guardarla en properties))
     */
      cstmt = con.prepareCall("{? = call SC_FONET.FNDICCIONARIO(?, ?)}");
    /*
    salida String nombre solicitado
     */
      cstmt.registerOutParameter(RESPUESTA, Types.VARCHAR);
    /*
    Datos de entrada, MEXICO por default
     */
      cstmt.setShort(PARAMETRO_PAIS, Constantes.MEXICO);
    /*
    Datos de entrada, nombre a buscar
     */
      cstmt.setString(PARAMETRO_HIPOCORSITICO,hipocoristico);
    /*
    ejcucion de sp
     */
      cstmt.execute();
    /*
    obtencion de salida sp
     */
      resp = cstmt.getString(RESPUESTA);
    }
    catch (Exception exception){
      resp = hipocoristico;
      log.registrarExcepcion(exception,"Error SQL");
      log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO,exception.getMessage());

      throw new ErrorInternoExepcion(Constantes.HTTP_500,"Error SQL",
        exception.getMessage(),nombres,apellidos);
    }
    finally {
      /*
    cerrar conexion a base postgres
     */
      assert con !=null;
      assert cstmt !=null;
      fabricaConexionDao.cerrarConexionSinResult(con,cstmt);
    }
    /*
    retorna salida de sp
     */
    log.terminarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO);
    return resp;
  }

}
