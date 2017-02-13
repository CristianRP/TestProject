package com.mayantech.checkin.data.api.model.visita;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 7/02/17.
 * Email:
 * Copyright (c) 2017 Mayantech
 */
public class Cliente {
    @SerializedName("nombre_cuenta")
    private String nombreCuenta;
    @SerializedName("codigo_cliente")
    private String codigoCliente;

    public Cliente() {
    }

    public Cliente(String nombreCuenta, String codigoCliente) {
        this.nombreCuenta = nombreCuenta;
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
}
