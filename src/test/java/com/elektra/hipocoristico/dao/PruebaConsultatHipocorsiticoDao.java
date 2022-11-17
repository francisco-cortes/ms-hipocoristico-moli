package com.elektra.hipocoristico.dao;

import com.baz.log.LogServicio;
import com.elektra.hipocoristico.modelos.Resultado;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PruebaConsultatHipocorsiticoDao {

  @Inject
  private DaoConsultaHipocorsitico daoConsultaHipocorsitico;

  @DisplayName("SP")
  @Test
  public void probarEjecutarSp() throws Exception {
    LogServicio log = new LogServicio();
    final String HIPOCORISTICO_TEST = "PACO";
    final String RESULTADO_ESPERADO = "FRANCISCO";
    Resultado resultado = new Resultado();
    String resp = daoConsultaHipocorsitico.buscarDiccionario(HIPOCORISTICO_TEST,resultado,log);
    assertEquals(RESULTADO_ESPERADO,resp);
  }

  @DisplayName("SP sql excepción")
  @Test
  public void probarEjecutarSpSqlExcepcion(){
    LogServicio log = new LogServicio();
    Resultado resultado = new Resultado();
    final String HIPOCORISTICO_TEST = "ESTO ES UNA CADENA MUY LARGA PARA OBTENER UNA EXCEPCIÒN";
    Assertions.assertThrows( PSQLException.class, () -> {
      daoConsultaHipocorsitico.buscarDiccionario(HIPOCORISTICO_TEST,resultado,log);
    });
  }

}
