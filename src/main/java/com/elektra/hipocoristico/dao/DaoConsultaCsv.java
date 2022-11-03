package com.elektra.hipocoristico.dao;

import com.elektra.hipocoristico.modelos.DickModel;
import com.elektra.hipocoristico.util.Constantes;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DaoConsultaCsv {

  public String buscarDick(String hipocorsitico){

    List<DickModel> lista = leerDick();

    String aux = Constantes.SP_RESPUESTA_VACIA_SP;
    System.out.println(hipocorsitico);
    for(int i = 0; i < lista.size()-1; i++){
      if(lista.get(i).getFcPrefijo().equals(hipocorsitico)){
        System.out.println("hipo");
        aux = lista.get(i).getFcNombre();
      }
    }
    return aux;
  }

  private List<DickModel> leerDick(){
    List<DickModel> lista = new ArrayList<DickModel>();
    String file = "Datos_Hipocoristico.csv";
    System.out.println("Entra el try de leer diccionario");
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;

      while ((line = br.readLine()) != null) {
        String[] cloums = line.split(",");
        if (cloums.length < 6) { // title,author,year
          System.err.println("Some error message here");
          continue;
        }
        lista.add(new DickModel(cloums[0].replace("\"",""),
          cloums[1].replace("\"","")));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lista;
  }
}
