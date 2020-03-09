package com.unam.agendais.utils;

import java.util.Objects;

public class ComponentContacto {

    private int idContacto, type;
    private String nombre, apellidos, numeroCelular, avenida, colonia, estado, pais, comentario, lugarComun;

    public ComponentContacto(){

    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getLugarComun() {
        return lugarComun;
    }

    public void setLugarComun(String lugarComun) {
        this.lugarComun = lugarComun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentContacto that = (ComponentContacto) o;
        return idContacto == that.idContacto &&
                type == that.type &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(numeroCelular, that.numeroCelular) &&
                Objects.equals(avenida, that.avenida) &&
                Objects.equals(colonia, that.colonia) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(pais, that.pais) &&
                Objects.equals(comentario, that.comentario) &&
                Objects.equals(lugarComun, that.lugarComun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContacto, type, nombre, apellidos, numeroCelular, avenida, colonia, estado, pais, comentario, lugarComun);
    }

}
