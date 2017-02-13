package com.mayantech.checkin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mayantech.checkin.R;
import com.mayantech.checkin.data.api.model.visita.Visita;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cristian Ramírez on 12/02/17.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */
public class VisitasAdapter
        extends RecyclerView.Adapter<VisitasAdapter.ViewHolder> {

    private Context mContext;
    private List<Visita> mListaVisitas;

    public interface OnItemClickListener {
        void onItemClick(ViewHolder item, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return listener;
    }

    public VisitasAdapter(List<Visita> visitas, Context context) {
        this.mListaVisitas = visitas;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.tvIdVisita)
        TextView mIdVisita;
        @BindView(R.id.tvNombreCuenta)
        TextView mNombreCuenta;
        @BindView(R.id.tvFechaInicio)
        TextView mFechaInicio;
        @BindView(R.id.btnCheckin)
        Button btnCheckin;

        private VisitasAdapter parent = null;

        public ViewHolder(View v, VisitasAdapter parent) {
            super(v);
            v.setOnClickListener(this);
            this.parent = parent;
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = parent.getOnItemClickListener();
            if (listener != null) {
                listener.onItemClick(this, getAdapterPosition());
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return mListaVisitas.get(position).getIdVisita();
    }

    @Override
    public int getItemCount() {
        return mListaVisitas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listado_visitas, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Visita visita = mListaVisitas.get(position);
        holder.mIdVisita.setText(String.valueOf(visita.getIdVisita()));
        holder.mNombreCuenta.setText(visita.getCliente().getNombreCuenta());
        holder.mFechaInicio.setText(visita.getFechaInicioPlan());
    }
}
