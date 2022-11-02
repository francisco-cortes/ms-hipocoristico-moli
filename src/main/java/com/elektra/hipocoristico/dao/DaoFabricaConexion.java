package com.elektra.hipocoristico.dao;

import com.elektra.hipocoristico.propiedades.Propiedades;
import com.elektra.hipocoristico.util.Constantes;
import com.baz.servicios.CifradorAes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>DaoFabricaConexion</b>
 * @descripcion: Administra las conexiones a Base de Datos.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@ApplicationScoped
public class DaoFabricaConexion {
  /**
   * Instancia el objeto para la interfaz de propiedades.
   **/
  @Inject
  private Propiedades propiedades;

  private CifradorAes cifradorAes;

  /**
   * obtenerConexion
   * Descrpcion: creoa le objeto SQL Connection
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * returns: String
   **/
  public Connection obtenerConexion() throws Exception {
    cifradorAes = new CifradorAes(false);
     /*
    cadena de conexión otorgada por servicios de base datos
     */
    String cadenaConexion = "jdbc:postgresql://" +
      cifradorAes.desencriptarDato( propiedades.conexionesdb().get(Constantes.C3REMESASC).ip()) + ":" +
      cifradorAes.desencriptarDato( propiedades.conexionesdb().get(Constantes.C3REMESASC).port())  + "/" +
      cifradorAes.desencriptarDato( propiedades.conexionesdb().get(Constantes.C3REMESASC).name());
    /*
    construye y retorna el objeto connection a través del DriveManager
     */
    return DriverManager.getConnection(cadenaConexion,
      cifradorAes.desencriptarDato( propiedades.conexionesdb().get(Constantes.C3REMESASC).credenciales().usuario()),
      cifradorAes.desencriptarDato( propiedades.conexionesdb().get(Constantes.C3REMESASC).credenciales().contrasena()));
  }

  /**
   * cerrarConexion
   * Descripción: Cierra y termina los procesos resultantes de la consulta a base de datos sin result set, ya que hay
   * consulta que no generan y asi se evita un fallo por excepción
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * params: Connection, Statement
   * returns: Void
   **/
  public void cerrarConexion(Connection conexion, Statement declaracionInvocable)
    throws SQLException {

    if (!conexion.isClosed()) {
      conexion.close();
    }

    if (!declaracionInvocable.isClosed()){
      declaracionInvocable.close();
    }
  }

}
