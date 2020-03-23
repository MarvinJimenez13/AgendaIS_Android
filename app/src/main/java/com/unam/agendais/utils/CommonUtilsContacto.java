package com.unam.agendais.utils;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.unam.agendais.fragments.DetallesContactoFragment;

public class CommonUtilsContacto {

    private static String nombreS, apellidosS, telefonoS, lugarComunS, avenidaS, coloniaS, estadoS, paisS, comentarioS, fechaRegistroS, adminRegistroS;
    private static int idContactoS;

    public static void setFragment(AppCompatActivity activity, String nameFragment, int contentRes, int idContacto, String nombre, String apellidos, String telefono, String lugarComun, String avenida,
                                   String colonia, String estado, String pais, String comentario, String fechaRegistro, String adminRegistro){

        idContactoS = idContacto;
        nombreS = nombre;
        apellidosS = apellidos;
        telefonoS = telefono;
        lugarComunS = lugarComun;
        avenidaS = avenida;
        coloniaS = colonia;
        estadoS = estado;
        paisS = pais;
        comentarioS = comentario;
        fechaRegistroS = fechaRegistro;
        adminRegistroS = adminRegistro;
        Fragment fragment = getFragmentById(nameFragment);
        activity.getSupportFragmentManager().beginTransaction().add(contentRes, fragment).commit();

    }

    private static Fragment getFragmentById(String nameFragment){

        DetallesContactoFragment fragment = null;

        switch (nameFragment){

            case DetallesContactoFragment.TAG:
                fragment = new DetallesContactoFragment();
                Bundle bun = new Bundle();
                bun.putInt("idContacto", idContactoS);
                bun.putString("nombre", nombreS);
                bun.putString("apellidos", apellidosS);
                bun.putString("numero", telefonoS);
                bun.putString("lugarComun", lugarComunS);
                bun.putString("avenida", avenidaS);
                bun.putString("colonia", coloniaS);
                bun.putString("estado", estadoS);
                bun.putString("pais", paisS);
                bun.putString("comentario", comentarioS);
                bun.putString("fechaRegistro", fechaRegistroS);
                bun.putString("adminRegistro", adminRegistroS);
                fragment.setArguments(bun);
                break;

        }

        return fragment;

    }

}
