package com.unam.agendais.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.unam.agendais.R;
import com.unam.agendais.ScrollContactoActivity;
import com.unam.agendais.utils.ComponentContacto;
import com.unam.agendais.utils.Constantes;
import com.unam.agendais.utils.OnClickListener;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ComponentAdapterContacto extends RecyclerView.Adapter<ComponentAdapterContacto.ViewHolderContacto> {

    private List<ComponentContacto> mComponents;
    private OnClickListener mListener;

    public ComponentAdapterContacto(List<ComponentContacto> mComponents){

        this.mComponents = mComponents;
        this.mListener = mListener;

    }

    @NonNull
    @Override
    public ViewHolderContacto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_component_contacto, parent, false);
        return new ViewHolderContacto(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContacto holder, int position) {

        ComponentContacto component = mComponents.get(position);
        holder.setOnClickListener(mListener, component);
        holder.tvNombre.setText(component.getNombre());
        holder.tvNumero.setText(component.getNumeroCelular());
        holder.setOnClickListeners(component.getIdContacto(), component.getNombre(), component.getNumeroCelular());

    }

    @Override
    public int getItemCount() {

        return mComponents.size();

    }

    public void add(ComponentContacto component){

        mComponents.add(component);
        notifyItemInserted(mComponents.size() - 1);

    }

    class ViewHolderContacto extends RecyclerView.ViewHolder implements  View.OnClickListener{

        Context context;
        View view;
        @BindView(R.id.tvNombre)
        TextView tvNombre;
        @BindView(R.id.tvNumero)
        TextView tvNumero;

        int idContacto;
        String nombre, numero;

        public  ViewHolderContacto(@NonNull View viewItem){

            super(viewItem);
            context = viewItem.getContext();
            this.view = viewItem;
            ButterKnife.bind(this, viewItem);

        }

        void setOnClickListeners(int idContacto, String nombre, String numero){

            this.idContacto = idContacto;
            this.nombre = nombre;
            this.numero = numero;
            view.setOnClickListener(this);

        }

        void setOnClickListener(OnClickListener onClickListener, ComponentContacto component){

            view.setOnClickListener(v -> onClickListener.onClickContacto(component));

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, ScrollContactoActivity.class);
            intent.putExtra(Constantes.ARG_NAME, "Detalles Contacto");
            intent.putExtra("idContacto", this.idContacto);
            intent.putExtra("nombre", this.nombre);
            intent.putExtra("numero", this.numero);
            context.startActivity(intent);

        }

    }

}
