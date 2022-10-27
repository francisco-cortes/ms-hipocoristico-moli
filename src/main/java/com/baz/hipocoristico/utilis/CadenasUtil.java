package com.baz.hipocoristico.utilis;

import java.util.Arrays;
import java.util.regex.Pattern;

public class CadenasUtil {

  private static final Pattern ESPACIOS_REGEX = Pattern.compile("\\s+");

  /**
   * <b>unirSubCadena</b>
   * @descripcion: une un arreglo de cadenas en una varialbe String eliminado caracteres basura
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 01/06/22
   */
  public static String unirSubCadena(String[] cadsep){
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
  public static boolean buscarEspaciosCadena(String cadenaAnalizada){
    return cadenaAnalizada.contains(" ");
  }
  /**
   * <b>subCadenaSeparada</b>
   * @descripcion: separa una cadena en un arreglo de cadenas apartir de cada espacio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 01/06/22
   */
  public static String[] subCadenaSeparada(String cadenaAnalizada){
    return ESPACIOS_REGEX.split(cadenaAnalizada);
  }
}
