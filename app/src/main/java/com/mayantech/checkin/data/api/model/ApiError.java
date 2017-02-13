package com.mayantech.checkin.data.api.model;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Cristian Ramírez on 22/01/2017.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */

public class ApiError {
    private String message;
    private String response;
    private String asesor;

    public ApiError(String message, String response, String asesor) {
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

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public static ApiError fromResponseBody(ResponseBody responseBody) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(responseBody.string(), ApiError.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
