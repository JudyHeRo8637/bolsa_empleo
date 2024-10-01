package org.bolsa.java.js.models;


import java.util.Date;

public class Aspirante {
    private Long id;
    private String nombre;
    private Integer cedula;
    private Integer edad;
    private String profesion;
    private Integer experiencia;
    private String correo;
    private Date fecha_registro;

    public Aspirante() {
    }

    @Override
    public String toString() {
        return  id +
                nombre +
                " | "+
                cedula +
                " | "+
                edad +
                " | "+
                profesion +
                " | "+
                experiencia +
                " | "+
                correo +
                " | "+
                fecha_registro;

    }

    public Aspirante(Long id, String nombre, Integer cedula, Integer edad, String profesion, Integer experiencia, String correo, Date fecha_registro) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.profesion = profesion;
        this.experiencia = experiencia;
        this.correo = correo;
        this.fecha_registro = fecha_registro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}

