package com.unam.agendais.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.android.material.textfield.TextInputEditText;
import com.unam.agendais.R;
import com.unam.agendais.utils.ComponentContacto;
import com.unam.agendais.utils.Constantes;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetallesContactoFragment extends Fragment {

    private static ComponentContacto mInstance;
    public static final String TAG = "Detalles Contacto";
    private Unbinder mUnbinder;
    @BindView(R.id.spinnerLugar)
    Spinner spinner;
    @BindView(R.id.tietNombre)
    TextInputEditText nombreET;
    @BindView(R.id.tietApellidos)
    TextInputEditText apellidosET;
    @BindView(R.id.tietTelCelular)
    TextInputEditText celularET;
    @BindView(R.id.tietAvenida)
    TextInputEditText avendiaET;
    @BindView(R.id.tietColonia)
    TextInputEditText coloniaET;
    @BindView(R.id.tietEstado)
    TextInputEditText estadoET;
    @BindView(R.id.tietPais)
    TextInputEditText paisET;
    @BindView(R.id.tietComentario)
    TextInputEditText comentarioET;
    @BindView(R.id.tietFechaRegistro)
    TextInputEditText fechaRegistroET;
    @BindView(R.id.tietAdminRegistro)
    TextInputEditText adminRegistroET;
    public DetallesContactoFragment() {

    }

    public static ComponentContacto getmInstance(int idContacto, String nombre, String apellidos, String numeroCelular, String lugarComun,  String avenida,
                                String colonia, String estado, String pais, String comentario, String fechaRegistro, String adminRegistro){

        mInstance = new ComponentContacto();
        mInstance.setIdContacto(idContacto);
        mInstance.setNombre(nombre);
        mInstance.setApellidos(apellidos);
        mInstance.setNumeroCelular(numeroCelular);
        mInstance.setLugarComun(lugarComun);
        mInstance.setAvenida(avenida);
        mInstance.setColonia(colonia);
        mInstance.setEstado(estado);
        mInstance.setPais(pais);
        mInstance.setComentario(comentario);
        mInstance.setFechaRegistro(fechaRegistro);
        mInstance.setAdminRegistro(adminRegistro);
        mInstance.setType(Constantes.SCROLL);
        return mInstance;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalles_contacto, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        Bundle bun = getArguments();
        nombreET.setText(bun.getString("nombre"));
        apellidosET.setText(bun.getString("apellidos"));
        celularET.setText(bun.getString("numero"));
        avendiaET.setText(bun.getString("avenida"));
        coloniaET.setText(bun.getString("colonia"));
        estadoET.setText(bun.getString("estado"));
        paisET.setText(bun.getString("pais"));
        comentarioET.setText(bun.getString("comentario"));
        fechaRegistroET.setText(bun.getString("fechaRegistro"));
        adminRegistroET.setText(bun.getString("adminRegistro"));

        String opciones[] = {"Trabajo", "Escuela", "Recreativo", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_tipo_usuario, opciones);
        spinner.setAdapter(adapter);

        String lugarComun = bun.getString("lugarComun");
        seleccionarSpinner(lugarComun);

        return view;

    }

    public void seleccionarSpinner(String lugarComun){

        switch (lugarComun){

            case "Trabajo":
                spinner.setSelection(0);
                break;
            case "Escuela":
                spinner.setSelection(1);
                break;
            case "Recreativo":
                spinner.setSelection(2);
                break;
            case "Otro":
                spinner.setSelection(3);
                break;

        }

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        mUnbinder.unbind();

    }
}
