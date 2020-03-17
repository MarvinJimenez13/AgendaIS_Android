package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.unam.agendais.adaptadores.ComponentAdapterContacto;
import com.unam.agendais.controladores.AdminSQLiteOpenHelper;
import com.unam.agendais.controladores.ServicioTaskContactos;
import com.unam.agendais.utils.Constantes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdministrarContactos extends AppCompatActivity {

    public static ComponentAdapterContacto myAdapter;
    private String linkAPI = "http://" + Constantes.IP + ":8080/AgendaAPI/Contacto/obtenerContactos";
    private Intent intent;
    @BindView(R.id.recyclerViewC)
    RecyclerView recyclerView;
    @BindView(R.id.toolbarC)
    Toolbar toolbar;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_contactos);
        mUnbinder = ButterKnife.bind(this);

        ServicioTaskContactos contactosTask = new ServicioTaskContactos(this, linkAPI);
        contactosTask.execute();
        configAdapter();
        configRecyclerView();

        toolbar.setTitle("Contactos Registrados");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard);
        toolbar.setNavigationOnClickListener(v -> {


            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "sesion", null, 1);
            SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
            Cursor fila = baseDeDatos.rawQuery("SELECT tipoAdmin FROM sesion WHERE id = " + 1, null);

            if(fila.moveToFirst()){

                int tipoAdmin = fila.getInt(0);

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

            }

        });

    }

    public void configAdapter(){

        myAdapter = new ComponentAdapterContacto(new ArrayList<>());

    }

    public void configRecyclerView(){

        recyclerView.setAdapter(myAdapter);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mUnbinder.unbind();

    }
}
