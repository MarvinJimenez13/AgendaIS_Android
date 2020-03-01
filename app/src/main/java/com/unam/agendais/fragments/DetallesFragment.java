package com.unam.agendais.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.unam.agendais.R;
import com.unam.agendais.utils.Component;
import com.unam.agendais.utils.Constantes;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetallesFragment extends Fragment {

    private static Component mInstance;
    public static final String TAG = "Detalles";
    private Unbinder mUnbinder;

    public DetallesFragment() {
        // Required empty public constructor
    }

    public static Component getmInstance(String idAdmin, String nombre, String comentario){

        mInstance = new Component();
        mInstance.setIdAdmin(idAdmin);
        mInstance.setNombre(nombre);
        mInstance.setComentario(comentario);
        mInstance.setType(Constantes.SCROLL);
        return mInstance;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Bundle bun = getArguments();
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        mUnbinder.unbind();

    }

}
