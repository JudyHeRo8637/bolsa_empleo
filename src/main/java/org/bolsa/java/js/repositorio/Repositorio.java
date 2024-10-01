package org.bolsa.java.js.repositorio;

import java.util.Comparator;
import java.util.List;

public interface Repositorio <T>{
    List<T> Listar();

    T porId(long id);

    void guardar(T t);

    void borrar(long id);

    List<String> obtenerCedulas(); //consulta para obtener el listado de cedulas

    T informacionPorCedula(String cedula); //consulta para mostrar informacion a partir de la cedula

    T porNombre(String nombre); //consulta para buscar por nombre

    T conMayorExperiencia();  //consulta para buscar con mayor experiencia

    List<T> masJoven(); //consulta aspirantes mas jovenes

    void borrarPorExperiencia(int experiencia); //Eliminar aspirante por experiencia

    double promedioEdad(); //Calcula el promedio de edad de los aspirantes

    List<T> OrdenarDiferentesCriterios(Comparator<T> coparador); //Ordenar la lista por diferentes criterios

}
