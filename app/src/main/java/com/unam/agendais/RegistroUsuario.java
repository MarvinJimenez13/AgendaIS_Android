package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.unam.agendais.controladores.ServicioTaskRegistro;
import com.unam.agendais.utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistroUsuario extends AppCompatActivity {

    @BindView(R.id.spinnerUsuario)
    Spinner spinnerUsuario;
    @BindView(R.id.nombreRegistroTI_ET)
    TextInputEditText nombreET;
    @BindView(R.id.contrasenaRegistroTI_ET)
    TextInputEditText contraET;
    @BindView(R.id.contrasena2RegistroTI_ET)
    TextInputEditText contra2ET;
    private String linkRegistroREST = "http://" + Constantes.IP + ":8080/AgendaAPI/admin/registrarse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        ButterKnife.bind(this);

        String opciones[] = {"Administrador", "Capturista"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_tipo_usuario, opciones);
        spinnerUsuario.setAdapter(adapter);

    }

    public void registrar(View view){

        if(!nombreET.getText().toString().trim().equalsIgnoreCase("") && !contraET.getText().toString().trim().equalsIgnoreCase("")
           && !contra2ET.getText().toString().trim().equalsIgnoreCase("")){

            String nombre = nombreET.getText().toString();
            String contra = contraET.getText().toString();
            String contra2 = contra2ET.getText().toString();
            String tipoAdmin = spinnerUsuario.getSelectedItem().toString();
            int tipo = Constantes.tipoAdmin(tipoAdmin);

            if (contra.equals(contra2)){

                ServicioTaskRegistro taskRegistro = new ServicioTaskRegistro(this, linkRegistroREST, nombre, contra, tipo);
                taskRegistro.execute();
                limpiarCampos();

            }else{

                Toast.makeText(this, "Las contraseñas no son las mismas.", Toast.LENGTH_LONG).show();

            }

        }else{

            Toast.makeText(this, "Completa todos los campos.", Toast.LENGTH_LONG).show();

        }

    }

    public void limpiarCampos(){

        nombreET.setText("");
        contraET.setText("");
        contra2ET.setText("");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void ingresar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}
