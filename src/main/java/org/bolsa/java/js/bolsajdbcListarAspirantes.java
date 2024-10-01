package org.bolsa.java.js;

import org.bolsa.java.js.models.Aspirante;
import org.bolsa.java.js.repositorio.Repositorio;
import org.bolsa.java.js.repositorio.aspiranteRepositorio;
import org.bolsa.java.js.util.conexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class bolsajdbcListarAspirantes {
    public static void main(String[] args) {


        try (Connection conn = conexionBaseDatos.getInstance()){

            Repositorio<Aspirante> repositorio = new aspiranteRepositorio();

            System.out.println(" ");

            System.out.println("=============== LISTAR ASPIRANTES =============================");
            repositorio.Listar().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
