package com.mayantech.checkin.data.api.model.visita;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 7/02/17.
 * Email:
 * Copyright (c) 2017 Mayantech
 */
public class Ubicacion {
    @SerializedName("id_ubicacion")
    private Long idUbicacion;
    @SerializedName("direccion")
    private String direccion;
    @SerializedName("longitud")
    private float longitud;
    @SerializedName("latitud")
    private float latitud;

    public Ubicacion() {
    }

    public Ubicacion(Long idUbicacion, String direccion, float longitud, float latitud) {
        this.idUbicacion = idUbicacion;
        this.direccion = direccion;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Long getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Long idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }
}
