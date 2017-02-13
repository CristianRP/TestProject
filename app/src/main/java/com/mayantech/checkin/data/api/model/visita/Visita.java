package com.mayantech.checkin.data.api.model.visita;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian Ramírez on 7/02/17.
 * Email:
 * Copyright (c) 2017 Mayantech
 */
public class Visita {
    @SerializedName("id_visita")
    private Long idVisita;
    @SerializedName("estado_visita_crm")
    private String estado;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("ubicacion")
    private Ubicacion ubicacion;
    @SerializedName("cliente")
    private Cliente cliente;
    @SerializedName("fecha_modificacion")
    private String fechaModificacion;
    @SerializedName("fecha_inicio_planificacion")
    private String fechaInicioPlan;
    @SerializedName("fecha_final_planificacion")
    private String fechaFinPlan;

    public static List<Visita> VISITAS = new ArrayList<>();


    public Visita() {
    }

    public Visita(Long idVisita, String estado, String tipo, Ubicacion ubicacion,
                  Cliente cliente, String fechaModificacion, String fechaInicioPlan, String fechaFinPlan) {
        this.idVisita = idVisita;
        this.estado = estado;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.cliente = cliente;
        this.fechaModificacion = fechaModificacion;
        this.fechaInicioPlan = fechaInicioPlan;
        this.fechaFinPlan = fechaFinPlan;
    }

    public Long getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Long idVisita) {
        this.idVisita = idVisita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaInicioPlan() {
        return fechaInicioPlan;
    }

    public void setFechaInicioPlan(String fechaInicioPlan) {
        this.fechaInicioPlan = fechaInicioPlan;
    }

    public String getFechaFinPlan() {
        return fechaFinPlan;
    }

    public void setFechaFinPlan(String fechaFinPlan) {
        this.fechaFinPlan = fechaFinPlan;
    }
}
