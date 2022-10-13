package com.baz.hipocoristico.services;

import com.baz.hipocoristico.dtos.EstadoResponseDto;
import com.baz.hipocoristico.utilis.Constantes;
import com.baz.servicios.Uid;

import javax.inject.Singleton;
/**
 * <b>MonitoreoService</b>
 * @descripcion: Monitoreo service para el modulo
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@Singleton
public class MonitoreoService {

  /**
   * <b>generarUid</b>
   * @descripcion: genera un UID para verificar el estado del modulo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */

  public EstadoResponseDto generarUid(){
    EstadoResponseDto uidAlive = new EstadoResponseDto();
    uidAlive.setMensaje(Constantes.ESTADO_OK);
    /*
    genera folio a traves de RemesasUtils.jar
     */
    uidAlive.setFolio("UDI" + Uid.generarUid(Constantes.TAMANO_UDI,Constantes.CICLOS_UDI));
    return uidAlive;
  }

}
