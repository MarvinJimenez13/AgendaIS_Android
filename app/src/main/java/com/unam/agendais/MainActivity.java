package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nombre_TI_ET)
    TextInputEditText nombreET;
    @BindView(R.id.contrasena_TI_ET)
    TextInputEditText contraET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void ingresar(View view){

        String nombre = nombreET.getText().toString();
        String contrasena = contraET.getText().toString();
        Intent intent;

        if(nombre.equals("admin") && contrasena.equals("admin")){

            intent = new Intent(this, HomeAdmin.class);
            startActivity(intent);
            finish();

        }else if(nombre.equals("cap") && contrasena.equals("cap")){

            intent = new Intent(this, HomeCapturista.class);
            startActivity(intent);
            finish();

        }else{

            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_LONG).show();

        }

    }

    public void registro(View view){

        Intent intent = new Intent(this, RegistroUsuario.class);
        startActivity(intent);
        finish();

    }

}
