package com.unam.agendais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import com.unam.agendais.utils.CommonUtilsContacto;
import com.unam.agendais.utils.Constantes;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScrollContactoActivity extends AppCompatActivity {

    private Unbinder mUnbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_contacto);
        mUnbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        String nameFragment = null;
        if(savedInstanceState == null){

            nameFragment = getIntent().getStringExtra(Constantes.ARG_NAME);
            int idContacto = getIntent().getIntExtra("idContacto", 0);
            String nombre = getIntent().getStringExtra("nombre");
            String numero = getIntent().getStringExtra("numero");
            CommonUtilsContacto.setFragment(this, nameFragment, R.id.content_contacto_scroll, idContacto, nombre, numero);

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

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mUnbinder.unbind();

    }
}
