package com.mayantech.checkin.data.api.model.vehiculo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 13/02/17.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */
public class Vehiculo {
    @SerializedName("estado")
    private String estado;
    @SerializedName("id_vehiculo")
    private int idVehiculo;
    @SerializedName("numero")
    private String numero;

    public Vehiculo() {
    }

    public Vehiculo(String estado, int idVehiculo, String numero) {
        this.estado = estado;
        this.idVehiculo = idVehiculo;
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
