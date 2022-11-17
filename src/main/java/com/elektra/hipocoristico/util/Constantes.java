package com.elektra.hipocoristico.util;

public class Constantes {

 /*
 versión
  */
  public final static String NOMBRE_MS = "ms-modulo-hipocoristicos";
  public final static String MS_VERSION = "1.0.0";
  /*
  Respuesta HTTP
   */
  public final static String CODIGO_HTTP_200 = "200";
  public final static String CODIGO_HTTP_400 = "400";
 public final static String CODIGO_HTTP_401 = "401";
  public final static String CODIGO_HTTP_404 = "404";
  public final static String CODIGO_HTTP_500 = "500";
  public static final String CERO_HIPOCORISTICOS = "Sin Hipocorístico.";
  public static final String UN_HIPOCORISTICO = "1 Hipocorístico Encontrado.";
  public static final String DOS_HIPOCORISTICO = "No se puede tener mas de un hipocorístico por evaluación.";
  public final static String MENSAJE_CODIGO_400 = "Datos de entrada incorrectos, por favor valide su información.";

  public final static String MENSAJE_CODIGO_401 = "No estas autorizado, favor de validar.";
  public final static String MENSAJE_CODIGO_404 = "Recurso no encontrado";
  public final static String MENSAJE_CODIGO_500 = "Ocurrió un inconveniente al procesar la solicitud.";

 public final static String MENSAJE_ERROR_SQL = "Ocurrió un error al invocar la consulta " +
   "hacia diccionario de base de datos.";

  public final static String CODIGO_SIN_HIPOCORISTICO = "IDCMH00000";
  public final static String CODIGO_UN_HIPOCORISTICO = "IDCMH00001";
  public final static String CODIGO_DOS_HIPOCORISTICO =  "IDCMH00002";
  public final static String CODIGO_ERROR_SQL = "IDCMH00003";
  public final static String CODIGO_SOLICITUD_INCORRECTA = "IDCMH00004";
  public final static String CODIGO_NO_AUTORIZADO = "IDCMH00005";
  public final static String CODIGO_NO_ENCONTRADO = "IDCMH00006";
  public final static String CODIGO_ERROR_GENERAL = "IDCMH00007";
  /*
  Constates de datos
   */
  public static final String C3REMESASC = "c3remesas";
  public static final short MEXICO = 1;
  public static final String SP_RESPUESTA_VACIA_SP = "SIN REGISTRO";

  public static final int TAMANO_UDI = 15;
  public static final int CICLOS_UDI = 3;
  public static final int LONGITUD_MIN_ENTRADA = 1;
  public static final int LONGITUD_MAX_ENTRADA = 10;
  public final static boolean ES_REQUERIDO = true;
  public final static boolean NO_REQUERIDO = false;
  public static final String ESTADO_OK = "OK";
  public static final String CODIGO_EXITO = "C00000";
  public static final String MENSAJE_EXITO = "Operacion exitosa.";
  /*
  Números
   */
 public static final int UNO = 1;
 public static final int DOS = 2;
 public static final int TRES = 3;
 public static final int CUATRO = 4;
 public static final int CINCO = 5;

}
