package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistroUsuario extends AppCompatActivity {

    @BindView(R.id.spinnerUsuario)
    Spinner spinnerUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        ButterKnife.bind(this);

        String opciones[] = {"Administrador", "Capturista"};
        ArrayAdapter<String> apater = new ArrayAdapter<>(this, R.layout.spinner_item_tipo_usuario, opciones);
        spinnerUsuario.setAdapter(apater);

    }

    public void ingresar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}
