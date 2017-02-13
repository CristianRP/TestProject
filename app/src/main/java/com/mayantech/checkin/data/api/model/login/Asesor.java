package com.mayantech.checkin.data.api.model.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 22/01/2017.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */

public class Asesor {

    @SerializedName("id_consultor")
    private int id_consultor;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("codigo")
    private String codigo;

    public Asesor() {
    }

    public Asesor(int id_consultor, String nombre, String codigo) {
        this.id_consultor = id_consultor;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public int getId_consultor() {
        return id_consultor;
    }

    public void setId_consultor(int id_consultor) {
        this.id_consultor = id_consultor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
