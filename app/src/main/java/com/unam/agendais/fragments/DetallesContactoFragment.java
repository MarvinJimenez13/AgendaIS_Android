package com.unam.agendais.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.unam.agendais.R;
import com.unam.agendais.utils.ComponentContacto;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetallesContactoFragment extends Fragment {

    private static ComponentContacto mInstance;
    public static final String TAG = "Detalles Contacto";
    private Unbinder mUnbinder;

    public DetallesContactoFragment() {

    }

    public static ComponentContacto getmInstance(int idContacto, String nombre, String apellidos, String numeroCelular, String lugarComun,  String avenida,
                                String colonia, String estado, String pais, String comentario){

        mInstance = new ComponentContacto();
        mInstance.setIdContacto(idContacto);
        mInstance.setNombre(nombre);
        mInstance.setApellidos(apellidos);
        mInstance.setNumeroCelular(numeroCelular);
        mInstance.setAvenida(avenida);
        mInstance.setColonia(colonia);
        mInstance.setEstado(estado);
        mInstance.setPais(pais);
        mInstance.setComentario(comentario);
        return mInstance;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalles_contacto, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        mUnbinder.unbind();

    }
}
