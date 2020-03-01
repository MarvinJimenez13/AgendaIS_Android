package com.unam.agendais.utils;

import java.util.Objects;

public class Component {

    private String nombre, comentario, idAdmin;
    private int type;

    public Component() {

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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idClase) {
        this.idAdmin = idClase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return  type == component.type &&
                Objects.equals(nombre, component.nombre) &&
                Objects.equals(comentario, component.comentario) &&
                Objects.equals(idAdmin, component.idAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, comentario, idAdmin, type);
    }

}