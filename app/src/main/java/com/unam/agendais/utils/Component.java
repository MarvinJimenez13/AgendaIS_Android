package com.unam.agendais.utils;

import java.util.Objects;

public class Component {

    private String nombre;
    private int type, tipoAdmin, idAdmin;

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

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idClase) {
        this.idAdmin = idClase;
    }

    public int getTipoAdmin() {
        return tipoAdmin;
    }

    public void setTipoAdmin(int tipoAdmin) {
        this.tipoAdmin = tipoAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return type == component.type &&
                Objects.equals(nombre, component.nombre) &&
                Objects.equals(tipoAdmin, component.tipoAdmin) &&
                Objects.equals(idAdmin, component.idAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, tipoAdmin, idAdmin, type);
    }

}