package com.unam.agendais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.unam.agendais.controladores.AdminSQLiteOpenHelper;
import com.unam.agendais.controladores.ServicioTaskRegistroContacto;
import com.unam.agendais.utils.Constantes;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegistarContacto extends AppCompatActivity {

    private Intent intent;
    private Unbinder mUnbinder;
    private String linkRegistrarContactoREST = "http://" + Constantes.IP + ":8080/AgendaAPI/Contacto/registrarContacto";
    @BindView(R.id.toolbarC)
    Toolbar toolbar;
    @BindView(R.id.spinnerLugar)
    Spinner spinner;
    @BindView(R.id.registrarContactoBTN)
    MaterialButton registrarContactoBTN;
    @BindView(R.id.tietNombre)
    TextInputEditText nombreET;
    @BindView(R.id.tietApellidos)
    TextInputEditText apellidosET;
    @BindView(R.id.tietTelCelular)
    TextInputEditText celularET;
    @BindView(R.id.tietAvenida)
    TextInputEditText avenidaET;
    @BindView(R.id.tietColonia)
    TextInputEditText coloniaET;
    @BindView(R.id.tietEstado)
    TextInputEditText estadoET;
    @BindView(R.id.tietPais)
    TextInputEditText paisET;
    @BindView(R.id.tietComentario)
    TextInputEditText comentarioET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_contacto);
        mUnbinder = ButterKnife.bind(this);

        String opciones[] = {"Trabajo", "Escuela", "Recreativo", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_tipo_usuario, opciones);
        spinner.setAdapter(adapter);

        registrarContactoBTN.setOnClickListener(v -> {

            if(!nombreET.getText().toString().trim().equalsIgnoreCase("") && !apellidosET.getText().toString().trim().equalsIgnoreCase("") && !celularET.getText().toString().trim().equalsIgnoreCase("")
                && !avenidaET.getText().toString().trim().equalsIgnoreCase("") && !coloniaET.getText().toString().trim().equalsIgnoreCase("") && !estadoET.getText().toString().trim().equalsIgnoreCase("")
                && !paisET.getText().toString().trim().equalsIgnoreCase("") && !comentarioET.getText().toString().trim().equalsIgnoreCase("")){

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "sesion", null, 1);
                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                Cursor fila = baseDeDatos.rawQuery("SELECT idAdmin FROM sesion WHERE id = " + "1", null);
                int idAdmin = 0;

                if(fila.moveToFirst()){

                    idAdmin = fila.getInt(0);

                }else{

                    baseDeDatos.close();

                }
                baseDeDatos.close();

                String nombre = nombreET.getText().toString();
                String apellidos = apellidosET.getText().toString();
                String celular = celularET.getText().toString();
                String lugarComun = spinner.getSelectedItem().toString();
                String avenida = avenidaET.getText().toString();
                String colonia = coloniaET.getText().toString();
                String estado = estadoET.getText().toString();
                String pais = paisET.getText().toString();
                String comentario = comentarioET.getText().toString();

                ServicioTaskRegistroContacto registroContacto = new ServicioTaskRegistroContacto(this, linkRegistrarContactoREST, idAdmin, nombre,
                                    apellidos, celular, lugarComun, avenida, colonia, estado, pais, comentario);
                registroContacto.execute();

                limpiarCampos();

            }else{

                Toast.makeText(this, "Llena todos los campos.", Toast.LENGTH_LONG).show();

            }

        });

        toolbar.setTitle("Registrar Contacto");
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

    public void limpiarCampos(){

        nombreET.setText("");
        apellidosET.setText("");
        celularET.setText("");
        avenidaET.setText("");
        coloniaET.setText("");
        estadoET.setText("");
        paisET.setText("");
        comentarioET.setText("");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
