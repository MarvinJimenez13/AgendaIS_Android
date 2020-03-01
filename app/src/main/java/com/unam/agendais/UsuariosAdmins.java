package com.unam.agendais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;

import com.unam.agendais.adaptadores.ComponentAdapter;
import com.unam.agendais.fragments.DetallesFragment;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UsuariosAdmins extends AppCompatActivity {

    public static ComponentAdapter myAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tool)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_admins);
        ButterKnife.bind(this);

        configAdapter();
        configRecyclerView();

        toolbar.setTitle("Adminis y Capturistas");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void configAdapter(){

        myAdapter = new ComponentAdapter(new ArrayList<>());
        myAdapter.add(DetallesFragment.getmInstance("1", "Andrea Berenice Cabrera Rivera", "Administrador"));
        myAdapter.add(DetallesFragment.getmInstance("2", "Martin Jiménez Rodriguez", "Capturista"));

    }

    private void configRecyclerView(){

        recyclerView.setAdapter(myAdapter);

    }

}
