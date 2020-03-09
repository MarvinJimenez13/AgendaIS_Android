package com.unam.agendais.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.unam.agendais.R;
import com.unam.agendais.utils.Component;
import com.unam.agendais.utils.ComponentContacto;
import com.unam.agendais.utils.OnClickListener;
import java.util.List;
import butterknife.ButterKnife;

public class ComponentAdapterContacto extends RecyclerView.Adapter<ComponentAdapterContacto.ViewHolderContacto> {

    private List<ComponentContacto> mComponents;
    private OnClickListener mListener;

    @NonNull
    @Override
    public ViewHolderContacto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_component_contacto, parent, false);
        return new ViewHolderContacto(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContacto holder, int position) {

    }

    @Override
    public int getItemCount() {

        return mComponents.size();

    }

    public void add(Component component){



    }

    class ViewHolderContacto extends RecyclerView.ViewHolder implements  View.OnClickListener{

        Context context;
        View view;

        public  ViewHolderContacto(@NonNull View viewItem){

            super(viewItem);
            context = viewItem.getContext();
            this.view = viewItem;
            ButterKnife.bind(this, viewItem);

        }

        void setOnClickListeners(){


        }

        void setOnClickListener(OnClickListener onClickListener, Component component){

            view.setOnClickListener(v -> onClickListener.onClick(component));

        }

        @Override
        public void onClick(View v) {

        }

    }

}
