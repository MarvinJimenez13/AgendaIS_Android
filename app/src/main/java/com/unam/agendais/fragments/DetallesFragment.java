package com.unam.agendais.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.unam.agendais.R;
import com.unam.agendais.controladores.ServicioTaskActualizarAdmin;
import com.unam.agendais.controladores.ServicioTaskEliminarAdmin;
import com.unam.agendais.utils.Component;
import com.unam.agendais.utils.Constantes;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetallesFragment extends Fragment {

    private static Component mInstance;
    public static final String TAG = "Detalles";
    private Unbinder mUnbinder;
    @BindView(R.id.spinnerTipo)
    Spinner spinnerTipo;
    @BindView(R.id.tietUsuario)
    TextInputEditText nombreET;
    @BindView(R.id.tietContrasena)
    TextInputEditText contrasenaET;
    @BindView(R.id.actualizarBTN)
    MaterialButton actualizarBTN;
    @BindView(R.id.eliminarBTN)
    MaterialButton eliminarBTN;
    @BindView(R.id.cbContra)
    CheckBox cbContra;
    private String linkAPIREST = "http://" + Constantes.IP + ":8080/AgendaAPI/admin/actualizar";
    private String linkAPIRESTEliminar = "http://" + Constantes.IP + ":8080/AgendaAPI/admin/eliminar";
    private String nombre, contrasena;
    private int idAdmin, tipoAdmin;

    public DetallesFragment() {
        // Required empty public constructor
    }

    public static Component getmInstance(int idAdmin, String nombre, int tipoAdmin){

        mInstance = new Component();
        mInstance.setIdAdmin(idAdmin);
        mInstance.setNombre(nombre);
        mInstance.setTipoAdmin(tipoAdmin);
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
        nombreET.setText(String.valueOf(bun.getString("nombre")));
        this.idAdmin = bun.getInt("idAdmin");

        String opciones[] = {"Administrador", "Capturista"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_tipo_usuario, opciones);
        spinnerTipo.setAdapter(adapter);
        spinnerTipo.setSelection((bun.getInt("tipoAdmin") == 1)?  0 : 1);

        contrasenaET.setEnabled(false);
        cbContra.setOnClickListener(v -> {

            if(cbContra.isChecked()) {
                contrasenaET.setEnabled(true);
            }else{
                contrasenaET.setEnabled(false);
                contrasenaET.setText("");
            }

        });

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

    @OnClick({R.id.actualizarBTN, R.id.eliminarBTN})
    public void onClickBTN(View view){

        switch (view.getId()){

            case R.id.actualizarBTN:
                this.nombre = nombreET.getText().toString();
                this.tipoAdmin = Constantes.tipoAdmin(spinnerTipo.getSelectedItem().toString());
                this.contrasena = (contrasenaET.isEnabled())? contrasenaET.getText().toString(): null;
                ServicioTaskActualizarAdmin actAdmin = new ServicioTaskActualizarAdmin(getContext(), linkAPIREST, this.idAdmin, this.nombre, this.tipoAdmin, this.contrasena);
                actAdmin.execute();
                break;
            case R.id.eliminarBTN:
                ServicioTaskEliminarAdmin eliminarAdmin = new ServicioTaskEliminarAdmin(getContext(), linkAPIRESTEliminar, this.idAdmin);
                eliminarAdmin.execute();
                break;

        }

    }

}
