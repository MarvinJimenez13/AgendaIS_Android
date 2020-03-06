package com.unam.agendais.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.button.MaterialButton;
import com.unam.agendais.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegistroUsuariosFragment extends DialogFragment {

    public static final String TAG = "Registrar Usuarios";
    Unbinder mUnbinder;
    @BindView(R.id.spinnerUsuarioR)
    Spinner spinnerUsuario;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnGuardar)
    MaterialButton btnGuardar;

    public RegistroUsuariosFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registro_usuarios, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        String opciones[] = {"Administrador", "Capturista"};
        ArrayAdapter<String> apater = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_tipo_usuario, opciones);
        spinnerUsuario.setAdapter(apater);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(R.string.titulo_registro_usuario);
        btnGuardar.setOnClickListener(v -> {

            Toast.makeText(getActivity(), "Funciona.", Toast.LENGTH_SHORT).show();

        });
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
