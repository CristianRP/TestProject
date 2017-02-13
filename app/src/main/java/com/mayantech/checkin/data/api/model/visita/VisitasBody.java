package com.mayantech.checkin.data.api.model.visita;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 7/02/17.
 * Email:
 * Copyright (c) 2017 Mayantech
 */
public class VisitasBody {
    @SerializedName("user-app")
    private String userApp;
    @SerializedName("password-app")
    private String passwordApp;
    @SerializedName("id_consultor")
    private Long idConsultor;

    public VisitasBody() {
    }

    public VisitasBody(String userApp, String passwordApp, Long idConsultor) {
        this.userApp = userApp;
        this.passwordApp = passwordApp;
        this.idConsultor = idConsultor;
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

    public Long getIdConsultor() {
        return idConsultor;
    }

    public void setIdConsultor(Long idConsultor) {
        this.idConsultor = idConsultor;
    }
}
