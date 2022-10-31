package com.elektra.hipocoristico.servicios;

import com.elektra.hipocoristico.dtos.DtoEstadoResponse;
import com.elektra.hipocoristico.utilidades.Constantes;
import com.baz.servicios.Uid;

import javax.inject.Singleton;
/**
 * <b>ServicioMonitoreo</b>
 * @descripcion: Monitoreo servicio para el modulo
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@Singleton
public class ServicioMonitoreo {

  /**
   * <b>generarUid</b>
   * @descripcion: genera un UID para verificar el estado del modulo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */

  public DtoEstadoResponse generarUid(){
    DtoEstadoResponse uidAlive = new DtoEstadoResponse();
    uidAlive.setMensaje(Constantes.ESTADO_OK);
    /*
    genera folio a traves de RemesasUtils.jar
     */
    uidAlive.setFolio("UID" + Uid.generarUid(Constantes.TAMANO_UDI,Constantes.CICLOS_UDI));
    return uidAlive;
  }

}
