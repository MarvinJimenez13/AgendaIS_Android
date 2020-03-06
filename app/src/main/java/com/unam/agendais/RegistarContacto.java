package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegistarContacto extends AppCompatActivity {

    private Intent intent;
    private Unbinder mUnbinder;
    @BindView(R.id.toolbarC)
    Toolbar toolbar;
    @BindView(R.id.spinnerLugar)
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_contacto);
        mUnbinder = ButterKnife.bind(this);

        String opciones[] = {"Trabajo", "Escuela", "Recreativo", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_tipo_usuario, opciones);
        spinner.setAdapter(adapter);

        toolbar.setTitle("Registrar Contacto");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard);
        toolbar.setNavigationOnClickListener(v -> {

            /*
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
                    finish();

                }

            }else{

                baseDeDatos.close();

            }*/

            intent = new Intent(this, HomeAdmin.class);
            startActivity(intent);
            finish();

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
