package org.bolsa.java.js;

import org.bolsa.java.js.models.Aspirante;
import org.bolsa.java.js.repositorio.Repositorio;
import org.bolsa.java.js.repositorio.aspiranteRepositorio;
import org.bolsa.java.js.util.conexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class bolsajdbcUpdate {
    public static void main(String[] args) {


        try (Connection conn = conexionBaseDatos.getInstance()){

            Repositorio<Aspirante> repositorio = new aspiranteRepositorio();

            System.out.println(" ");

            System.out.println("=============== LISTAR ASPIRANTES =============================");
            repositorio.Listar().forEach(System.out::println);

            System.out.println(" ");

            System.out.println("================ EDITAR ASPIRANTES =============================");
            Aspirante aspirante = new Aspirante();
            aspirante.setId(3L);
            aspirante.setNombre("Paola Cruz");
            aspirante.setCedula(1056324789);
            aspirante.setEdad(31);
            aspirante.setProfesion("Psicologa");
            aspirante.setExperiencia(7);
            aspirante.setCorreo("Pao19@gmail.com");

            repositorio.guardar(aspirante);
            System.out.println("Aspirante actualizado con exito");

            System.out.println(" ");

            System.out.println("=============== LISTAR ASPIRANTES =============================");
            repositorio.Listar().forEach(System.out::println);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
