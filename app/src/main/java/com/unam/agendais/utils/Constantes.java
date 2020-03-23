package com.unam.agendais.utils;

public class Constantes {

    public static final int ADMIN = 1;
    public static final int CAPTURISTA = 2;
    public static final String ARG_NAME = "name";
    public static final int SCROLL = 0;
    public static final String IP = "192.168.100.105";

    public static int tipoAdmin(String admin){

        int tipo = 0;

        if(admin.equals("Administrador")){

            tipo = Constantes.ADMIN;

        }else if(admin.equals("Capturista")){

            tipo = Constantes.CAPTURISTA;

        }

        return tipo;

    }

}
