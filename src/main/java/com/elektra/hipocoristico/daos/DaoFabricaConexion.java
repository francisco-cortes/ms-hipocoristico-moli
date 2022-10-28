package com.elektra.hipocoristico.daos;

import com.elektra.hipocoristico.propiedades.Properties;
import com.elektra.hipocoristico.utilidades.Constantes;
import com.baz.servicios.CifradorAes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>FabricaDao</b>
 * @descripcion: Administra las conexiones a Base de Datos.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@ApplicationScoped
public class DaoFabricaConexion {
  /**
   * Instancia el objeto para la interface de propiedades.
   **/
  @Inject
  private Properties properties;

  private CifradorAes cifradorAes;

  /**
   * obtenerConexion
   * Descrpcion: creoa le objeto SQL Connection
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * returns: String
   **/
  public Connection getConexion() throws Exception {
    cifradorAes = new CifradorAes(false);
     /*
    cadena de conexion otrogada por servicios de base datos
     */
    String cadenaConexion = "jdbc:postgresql://" +
      cifradorAes.desencriptarDato( properties.conexionesdb().get(Constantes.C3REMESASC).ip() ) + ":" +
      cifradorAes.desencriptarDato( properties.conexionesdb().get(Constantes.C3REMESASC).port() )  + "/" +
      cifradorAes.desencriptarDato( properties.conexionesdb().get(Constantes.C3REMESASC).name() );
    /*
    construye y retorna el objeto connection a traves del DriveManager
     */
    return DriverManager.getConnection(cadenaConexion,
      cifradorAes.desencriptarDato( properties.conexionesdb().get(Constantes.C3REMESASC).credenciales().usuario() ),
      cifradorAes.desencriptarDato( properties.conexionesdb().get(Constantes.C3REMESASC).credenciales().contrasena() ));
  }

  /**
   * cerrarConexionSinResult
   * Descrpcion: Cierra y termina los procesos resultantes de la consulta a base de datos sin result set ya que hay
   * consulta que no generan y asi se evita un fallo por excepcion
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * params: Connection, Statement
   * returns: Void
   **/
  public void cerrarConexionSinResult(Connection conexion, Statement declaracionInvocable)
    throws SQLException {

    if (!conexion.isClosed()) {
      conexion.close();
    }

    if (!declaracionInvocable.isClosed()){
      declaracionInvocable.close();
    }
  }

}
