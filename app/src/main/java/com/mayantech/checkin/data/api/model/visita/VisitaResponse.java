package com.mayantech.checkin.data.api.model.visita;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian Ramírez on 7/02/17.
 * Email:
 * Copyright (c) 2017 Mayantech
 */
public class VisitaResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("response")
    private String response;
    @SerializedName("visitas")
    private List<Visita> visita;


    public static List<VisitaResponse> VISITAS = new ArrayList<>();

    public VisitaResponse() {
    }

    public VisitaResponse(String response, String message, List<Visita> visita) {
        this.response = response;
        this.message = message;
        this.visita = visita;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Visita> getVisita() {
        return visita;
    }

    public void setVisita(List<Visita> visita) {
        this.visita = visita;
    }
}
