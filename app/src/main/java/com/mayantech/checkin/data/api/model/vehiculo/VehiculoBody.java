package com.mayantech.checkin.data.api.model.vehiculo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 13/02/17.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */
public class VehiculoBody {
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("user-app")
    private String userApp;
    @SerializedName("password-app")
    private String passwordApp;

    public VehiculoBody() {
    }

    public VehiculoBody(String fecha, String userApp, String passwordApp) {
        this.fecha = fecha;
        this.userApp = userApp;
        this.passwordApp = passwordApp;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUserApp() {
        return userApp;
    }

    public void setUserApp(String userApp) {
        this.userApp = userApp;
    }

    public String getPasswordApp() {
        return passwordApp;
    }

    public void setPasswordApp(String passwordApp) {
        this.passwordApp = passwordApp;
    }
}
