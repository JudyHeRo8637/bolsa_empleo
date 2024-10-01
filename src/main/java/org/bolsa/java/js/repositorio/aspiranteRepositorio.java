package org.bolsa.java.js.repositorio;

import org.bolsa.java.js.models.Aspirante;
import org.bolsa.java.js.util.conexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class aspiranteRepositorio implements Repositorio<Aspirante> {

    private Connection getConnection() throws SQLException {
        return conexionBaseDatos.getInstance();
    }

    //----------LISTAR LOS ASPIRANTES --------------------------------
    @Override
    public List<Aspirante> Listar() {

        List<Aspirante> aspirantes = new ArrayList<Aspirante>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM aspirantes")) {

            while (rs.next()) {
                Aspirante a = crearAspirante(rs);

                aspirantes.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aspirantes;
    }

    //----------BUSCAR POR ID---------------------------------------
    @Override
    public Aspirante porId(long id) {

        Aspirante aspirante = null;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM aspirantes WHERE id =?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aspirante = crearAspirante(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aspirante;
    }

    //-----------ACTUALIZAR O CREAR --------------------------------
    @Override
    public void guardar(Aspirante aspirante) {

        String sql;
        if (aspirante.getId() != null && aspirante.getId() > 0) {
            sql = "UPDATE aspirantes SET nombre=?, cedula=?, edad=?, profesion=?, experiencia=?, correo=? WHERE id=?";
        } else {
            sql = "INSERT INTO aspirantes (nombre, cedula, edad, profesion, experiencia, correo, fecha_registro) VALUES (?,?,?,?,?,?,?)";
        }

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, aspirante.getNombre());
            stmt.setInt(2, aspirante.getCedula());
            stmt.setInt(3, aspirante.getEdad());
            stmt.setString(4, aspirante.getProfesion());
            stmt.setInt(5, aspirante.getExperiencia());
            stmt.setString(6, aspirante.getCorreo());

            if (aspirante.getId() != null && aspirante.getId() > 0) {
                stmt.setLong(7, aspirante.getId());
            }else{
                stmt.setDate(7, new Date(aspirante.getFecha_registro().getTime()));
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //----------BORRAR UN ASPIRANTE ----------------------------------
    @Override
    public void borrar(long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM aspirantes WHERE id=?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //----------OBTENER LAS CEDULAS ----------------------------------
    @Override
    public List<String> obtenerCedulas() {
        List<String> cedulas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT cedula FROM aspirantes")) {

            while (rs.next()) {
                cedulas.add(rs.getString("cedula"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cedulas;
    }

    //----------OBTENER INFORMACION POR CEDULA -------------------------
    @Override
    public Aspirante informacionPorCedula(String cedula) {

        Aspirante aspirante = null;

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM aspirantes WHERE cedula=?")) {
            stmt.setInt(1, Integer.parseInt(cedula));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aspirante = crearAspirante(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aspirante;
    }

    //----------OBTENER INFORMACION POR NOMBRE ------------------------
    @Override
    public Aspirante porNombre(String nombre) {

        Aspirante aspirante = null;

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM aspirantes WHERE nombre=?")) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aspirante = crearAspirante(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aspirante;
    }

    //----------LISTAR LOS ASPIRANTES MAS JOVENES ------------------
    @Override
    public List<Aspirante> masJoven() {

        List<Aspirante> aspirantes = new ArrayList<Aspirante>();

        String query = "SELECT id, nombre, cedula, edad, profesion, experiencia, correo, fecha_registro " +
                "FROM aspirantes ORDER BY edad ASC";

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Aspirante a = crearAspirante(rs);

                aspirantes.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aspirantes;

    }


    //----------OBTENER INFORMACION POR MAYOR EXPERIENCIA ------------------
    @Override
    public Aspirante conMayorExperiencia() {

        Aspirante aspirante = null;

        String query = "SELECT id, nombre, cedula, edad, profesion, experiencia, correo, fecha_registro " +
                "FROM aspirantes ORDER BY experiencia DESC LIMIT 1";

        try(PreparedStatement stmt = getConnection().prepareStatement(query)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aspirante = crearAspirante(rs);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aspirante;
    }

    //----------BORRAR LOS ASPIRANTES CON MENOR EXPERIENCIA ------------------
    @Override
    public void borrarPorExperiencia(int experiencia) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM aspirantes WHERE experiencia <?")) {
            stmt.setLong(1, experiencia);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //----------OBTENER EL PROMEDIO DE EDAD DE LOS ASPIRANTES ------------------
    public double promedioEdad(){

        double promedioEdad = 0.0;

        String query = "SELECT AVG(edad) AS promedio_edad FROM aspirantes";

        try (PreparedStatement stmt = getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                promedioEdad = rs.getDouble("promedio_edad");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return promedioEdad;

    }

    //----------ORDENAR POR CRITERIO ------------------
    @Override
    public List<Aspirante> OrdenarDiferentesCriterios(Comparator<Aspirante> coparador) {
        return null;
    }

    private static Aspirante crearAspirante(ResultSet rs) throws SQLException {
        Aspirante a = new Aspirante();
        a.setId(rs.getLong("id"));
        a.setNombre(rs.getString("nombre"));
        a.setCedula(rs.getInt("cedula"));
        a.setEdad(rs.getInt("edad"));
        a.setProfesion(rs.getString("profesion"));
        a.setExperiencia(rs.getInt("experiencia"));
        a.setCorreo(rs.getString("correo"));
        a.setFecha_registro(rs.getDate("fecha_registro"));
        return a;
    }
}
