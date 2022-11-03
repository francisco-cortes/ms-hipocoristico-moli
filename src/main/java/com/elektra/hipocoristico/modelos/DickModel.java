package com.elektra.hipocoristico.modelos;

public class DickModel {
  private String fcPrefijo;
  private String fcNombre;

  public DickModel(String fcPrefijo, String fcNombre){
    this.fcPrefijo = fcPrefijo;
    this.fcNombre = fcNombre;
  }

  public void setFcPrefijo(String fcPrefijo){
    this.fcPrefijo = fcPrefijo;
  }
  public void setFcNombre(String fcNombre){
    this.fcNombre = fcNombre;
  }

  public String getFcPrefijo(){
    return this.fcPrefijo;
  }
  public String getFcNombre(){
    return this.fcNombre;
  }
}
