package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeCapturista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_capturista);
    }

    public void cerrarSesion(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}
