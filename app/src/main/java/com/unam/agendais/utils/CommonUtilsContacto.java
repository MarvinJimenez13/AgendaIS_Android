package com.unam.agendais.utils;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.unam.agendais.fragments.DetallesContactoFragment;

public class CommonUtilsContacto {

    private static String nombreS, numeroS;
    private static int idContactoS;

    public static void setFragment(AppCompatActivity activity, String nameFragment, int contentRes, int idContacto, String nombre, String numero){

        idContactoS = idContacto;
        nombreS = nombre;
        numeroS = numero;
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
                bun.putString("numero", numeroS);
                fragment.setArguments(bun);
                break;

        }

        return fragment;

    }

}
