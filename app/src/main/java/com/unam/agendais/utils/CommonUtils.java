package com.unam.agendais.utils;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.unam.agendais.fragments.DetallesFragment;

public class CommonUtils {

    private static String nombreS;
    private static int idAdminS, tipoAdminS;


    public static void setFragment(AppCompatActivity activity, String nameFragment, int contentRes, int idAdmin, String nombre, int tipoAdmin){

        idAdminS = idAdmin;
        nombreS = nombre;
        tipoAdminS = tipoAdmin;
        Fragment fragment = getFragmentById(nameFragment);
        activity.getSupportFragmentManager().beginTransaction().add(contentRes, fragment).commit();

    }

    private static Fragment getFragmentById(String nameFragment) {

        DetallesFragment fragment = null;

        switch (nameFragment) {

            case DetallesFragment.TAG:
                fragment = new DetallesFragment();
                Bundle bun = new Bundle();
                bun.putInt("idAdmin", idAdminS);
                bun.putString("nombre", nombreS);
                bun.putInt("tipoAdmin", tipoAdminS);
                fragment.setArguments(bun);
                break;

        }

        return fragment;

    }

}
