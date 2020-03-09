package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unam.agendais.adaptadores.ComponentAdapter;
import com.unam.agendais.controladores.AdminSQLiteOpenHelper;
import com.unam.agendais.controladores.ServicioTaskAdmins;
import com.unam.agendais.fragments.RegistroUsuariosFragment;
import com.unam.agendais.utils.Constantes;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UsuariosAdmins extends AppCompatActivity {

    public static ComponentAdapter myAdapter;
    private String linkAPI = "http://" + Constantes.IP + ":8080/AgendaAPI/admin/obtenerUsuarios";
    private Intent intent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tool)
    Toolbar toolbar;
    @BindView(R.id.fabNuevo)
    FloatingActionButton fabNuevo;
    @BindView(R.id.actualizarBTN)
    MaterialButton actualizarBTN;
    Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_admins);
        mUnbinder = ButterKnife.bind(this);

        fabNuevo.setOnClickListener(v -> {

            RegistroUsuariosFragment registro = new RegistroUsuariosFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            registro.show(transaction, RegistroUsuariosFragment.TAG);

        });

        actualizarBTN.setOnClickListener(v -> {

            finish();
            startActivity(getIntent());

        });

        ServicioTaskAdmins adminsTask = new ServicioTaskAdmins(this, linkAPI);
        adminsTask.execute();
        configAdapter();
        configRecyclerView();

        toolbar.setTitle("Admins y Capturistas");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard);
        toolbar.setNavigationOnClickListener(v -> {

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

            }

        });

    }

    public void configAdapter(){

        myAdapter = new ComponentAdapter(new ArrayList<>());

    }

    private void configRecyclerView(){

        recyclerView.setAdapter(myAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

}
