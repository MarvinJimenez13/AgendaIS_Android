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
import com.unam.agendais.ScrollActivity;
import com.unam.agendais.utils.Component;
import com.unam.agendais.utils.Constantes;
import com.unam.agendais.utils.OnClickListener;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ViewHolder>{

    private List<Component> mComponents;
    private OnClickListener mListener;

    public ComponentAdapter(List<Component> mComponents) {
        this.mComponents = mComponents;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_component, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Component component = mComponents.get(position);
        holder.setClickListener(mListener, component);
        holder.tvNombre.setText(component.getNombre());
        holder.tvComentario.setText(component.getComentario());
        holder.setOnClickListeners(component.getIdAdmin(), component.getNombre(), component.getComentario());

    }

    @Override
    public int getItemCount() {
        return mComponents.size();
    }

    public void add(Component component){

        mComponents.add(component);
        notifyItemInserted(mComponents.size() - 1);

    }

    class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        @BindView(R.id.tvNombre)
        TextView tvNombre;
        @BindView(R.id.tvComentario)
        TextView tvComentario;
        Context context;

        String idAdmin, nombre, comentario;

        View view;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            context = itemView.getContext();
            this.view = itemView;
            ButterKnife.bind(this, itemView);

        }

        void setOnClickListeners(String idAdmin, String nombre, String comentario){

            this.idAdmin = idAdmin;
            this.nombre = nombre;
            this.comentario = comentario;
            view.setOnClickListener(this);

        }

        void setClickListener(OnClickListener listener, Component component){

            view.setOnClickListener(view -> listener.onClick(component));

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, ScrollActivity.class);
            intent.putExtra(Constantes.ARG_NAME, "Detalles");
            intent.putExtra("idAdmin", this.idAdmin);
            intent.putExtra("nombre", this.nombre);
            intent.putExtra("comentario", this.comentario);
            context.startActivity(intent);

        }

    }

}