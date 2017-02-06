package com.mayantech.checkin.data.api.model;

/**
 * Created by Cristian Ramírez on 24/01/2017.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */

public class LoginResponse {
    private String message;
    private String response;
    private Asesor asesor;

    public LoginResponse() {
    }

    public LoginResponse(String message, String response, Asesor asesor) {
        this.message = message;
        this.response = response;
        this.asesor = asesor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }
}
