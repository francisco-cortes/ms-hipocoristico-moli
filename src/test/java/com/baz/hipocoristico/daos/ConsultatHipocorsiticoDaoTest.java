package com.baz.hipocoristico.daos;

import com.baz.log.LogServicio;
import com.baz.hipocoristico.exceptions.ErrorInternoExepcion;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ConsultatHipocorsiticoDaoTest {

  @Inject
  private ConsultarHipocorsiticoDao consultarHipocorsiticoDao;

  @DisplayName("SP")
  @Test
  public void testEjecutarSp() throws SQLException {
    LogServicio log = new LogServicio();
    final String[] NOMBRES = new String[]{"PACO","PEDRO"};
    final String[] APELLIDO = new String[]{"MARTINEZ","MILLAN"};
    final String HIPOCORISTICO_TEST = "PACO";
    final String RESULTADO_ESPERADO = "FRANCISCO";
    String resp = consultarHipocorsiticoDao.ejecutarSp(HIPOCORISTICO_TEST,log,NOMBRES,APELLIDO);
    assertEquals(RESULTADO_ESPERADO,resp);
  }

  @DisplayName("SP sql expecion")
  @Test
  public void testEjecutarSpSqlExpcion() throws SQLException {
    LogServicio log = new LogServicio();
    final String[] NOMBRES = new String[]{"PACO","PEDRO"};
    final String[] APELLIDO = new String[]{"MARTINEZ","MILLAN"};
    final String HIPOCORISTICO_TEST = "DELETE FROM SC_FONET.TADICCIONARIO";
    Assertions.assertThrows(ErrorInternoExepcion.class, () -> {
      consultarHipocorsiticoDao.ejecutarSp(HIPOCORISTICO_TEST,log,NOMBRES,APELLIDO);
    });
  }

}
