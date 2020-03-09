package com.unam.agendais;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.unam.agendais.utils.CommonUtils;
import com.unam.agendais.utils.Constantes;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        String nameFragment = null;
        if(savedInstanceState == null){

            nameFragment = getIntent().getStringExtra(Constantes.ARG_NAME);
            int idAdmin = getIntent().getIntExtra("idAdmin", 0);
            String nombre = getIntent().getStringExtra("nombre");
            int tipoAdmin = getIntent().getIntExtra("tipoAdmin", 0);
            CommonUtils.setFragment(this, nameFragment, R.id.content_scroll, idAdmin, nombre, tipoAdmin);

        }

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(nameFragment);

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){

            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);

    }

}
