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
        holder.setOnClickListeners(component.getIdContacto(), component.getNombre(), component.getApellidos(), component.getNumeroCelular(), component.getLugarComun(),
                component.getAvenida(), component.getColonia(), component.getEstado(), component.getPais(), component.getComentario(), component.getFechaRegistro(), component.getAdminRegistro());

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
        String nombre, apellidos, numero, lugarComun, avenida, colonia, estado, pais, comentario, fechaRegistro, adminRegistro;

        public  ViewHolderContacto(@NonNull View viewItem){

            super(viewItem);
            context = viewItem.getContext();
            this.view = viewItem;
            ButterKnife.bind(this, viewItem);

        }

        void setOnClickListeners(int idContacto, String nombre, String apellidos, String numero, String lugarComun, String avenida, String colonia, String estado,
                                 String pais, String comentario, String fechaRegistro, String adminRegistro){

            this.idContacto = idContacto;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.numero = numero;
            this.lugarComun = lugarComun;
            this.avenida = avenida;
            this.colonia = colonia;
            this.estado = estado;
            this.pais = pais;
            this.comentario = comentario;
            this.fechaRegistro = fechaRegistro;
            this.adminRegistro = adminRegistro;
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
            intent.putExtra("apellidos", this.apellidos);
            intent.putExtra("numero", this.numero);
            intent.putExtra("lugarComun", this.lugarComun);
            intent.putExtra("avenida", this.avenida);
            intent.putExtra("colonia", this.colonia);
            intent.putExtra("estado", this.estado);
            intent.putExtra("pais", this.pais);
            intent.putExtra("comentario", this.comentario);
            intent.putExtra("fechaRegistro", this.fechaRegistro);
            intent.putExtra("adminRegistro", this.adminRegistro);
            context.startActivity(intent);

        }

    }

}
