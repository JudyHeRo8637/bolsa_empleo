package org.bolsa.java.js;

import org.bolsa.java.js.models.Aspirante;
import org.bolsa.java.js.repositorio.Repositorio;
import org.bolsa.java.js.repositorio.aspiranteRepositorio;
import org.bolsa.java.js.util.conexionBaseDatos;

import java.sql.*;
import java.util.Date;

public class bolsajdbc {
    public static void main(String[] args) {


        try (Connection conn = conexionBaseDatos.getInstance()) {

            Repositorio<Aspirante> repositorio = new aspiranteRepositorio();

            System.out.println(" ");

            System.out.println("=============== LISTAR ASPIRANTES =============================");
            repositorio.Listar().forEach(System.out::println);

            System.out.println(" ");

            System.out.println("================ CREAR ASPIRANTES =============================");
            Aspirante aspirante = new Aspirante();
            aspirante.setNombre("Juan Carlos Cortes");
            aspirante.setCedula(73654962);
            aspirante.setEdad(35);
            aspirante.setProfesion("lincenciado en Quimica");
            aspirante.setExperiencia(12);
            aspirante.setCorreo("juan.cortes@gmail.com");
            aspirante.setFecha_registro(new Date());

            repositorio.guardar(aspirante);
            System.out.println("Aspirante guardado con exito");

            System.out.println(" ");

            System.out.println("=============== LISTAR ASPIRANTES =============================");
            repositorio.Listar().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
