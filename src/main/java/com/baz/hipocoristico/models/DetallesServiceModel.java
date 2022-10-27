package com.baz.hipocoristico.models;

public class DetallesServiceModel {
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
   * <b>getMensaje</b>
   * @descripcion: getter para mensaje
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 04/10/22
   */

  public int getHipos(){
    return hipocoristicosEncontrados;
  }

  /**
   * <b>getMensaje</b>
   * @descripcion: getter para mensaje
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 04/10/22
   */

  public String getMensaje(){
    return mensaje;
  }

  /**
   * <b>getDetalles</b>
   * @descripcion: getter para detalles
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */

  public String getDetalles(){
    return detalles;
  }

  /**
   * <b>setMensaje</b>
   * @descripcion: settter para mensaje
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */

  public void setHipos (int Hipo){
     hipocoristicosEncontrados = Hipo;
  }

  public void sumHipos(int Hipo){
    hipocoristicosEncontrados = hipocoristicosEncontrados + Hipo;
  }

  /**
   * <b>setMensaje</b>
   * @descripcion: settter para mensaje
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */

  public void setMensaje(String mensajeActual){
    mensaje = mensajeActual;
  }

  /**
   * <b>setDetalles</b>
   * @descripcion: setter par detalles
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */
  public void setDetalles(String excepcionMensaje){
    detalles = excepcionMensaje;
  }

}
