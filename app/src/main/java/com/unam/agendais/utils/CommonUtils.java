package com.unam.agendais.utils;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.unam.agendais.fragments.DetallesFragment;

public class CommonUtils {

    private static String nombreS = "", comentarioS = "";
    private static String idAdminS;


    public static void setFragment(AppCompatActivity activity, String nameFragment, int contentRes, String idAdmin, String nombre, String comentario){

        idAdminS = idAdmin;
        nombreS = nombre;
        comentarioS = comentario;
        Fragment fragment = getFragmentById(nameFragment);
        activity.getSupportFragmentManager().beginTransaction().add(contentRes, fragment).commit();

    }

    private static Fragment getFragmentById(String nameFragment) {

        DetallesFragment fragment = null;

        switch (nameFragment) {

            case DetallesFragment.TAG:
                fragment = new DetallesFragment();
                Bundle bun = new Bundle();
                bun.putString("idAdmin", idAdminS);
                bun.putString("nombre", nombreS);
                bun.putString("nombre", comentarioS);
                fragment.setArguments(bun);
                break;

        }

        return fragment;

    }

}
