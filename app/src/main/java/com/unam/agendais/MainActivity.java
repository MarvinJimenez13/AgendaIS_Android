package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.unam.agendais.controladores.AdminSQLiteOpenHelper;
import com.unam.agendais.controladores.ServicioTaskLogin;
import com.unam.agendais.utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String ingresarLinkREST = "http://"+ Constantes.IP +":8080/AgendaAPI/admin/iniciarSesion";
    private Intent intent;
    @BindView(R.id.nombre_TI_ET)
    TextInputEditText nombreET;
    @BindView(R.id.contrasena_TI_ET)
    TextInputEditText contraET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "sesion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        Cursor fila = baseDeDatos.rawQuery("SELECT idAdmin, nombre, tipoAdmin FROM sesion WHERE id = " + "1", null);

        if(fila.moveToFirst()){

            int tipoAdmin = fila.getInt(2);

            if(tipoAdmin == Constantes.ADMIN){

                intent = new Intent(this, HomeAdmin.class);
                startActivity(intent);
                baseDeDatos.close();
                finish();

            }else if(tipoAdmin == Constantes.CAPTURISTA){

                intent = new Intent(this, HomeCapturista.class);
                startActivity(intent);
                baseDeDatos.close();
                finish();

            }else{

                baseDeDatos.close();

            }

        }else{

            baseDeDatos.close();

        }

    }

    public void ingresar(View view){

        if(!nombreET.getText().toString().trim().equalsIgnoreCase("") && !contraET.getText().toString().trim().equalsIgnoreCase("")){

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

                ServicioTaskLogin ingresarTask =  new ServicioTaskLogin(this, ingresarLinkREST, nombre, contrasena);
                ingresarTask.execute();

            }

        }else{

            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show();

        }

    }

    public void registro(View view){

        Intent intent = new Intent(this, RegistroUsuario.class);
        startActivity(intent);
        finish();

    }

}
