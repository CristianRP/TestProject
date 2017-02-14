package com.mayantech.checkin.data.api.model.vehiculo;

import java.util.List;

/**
 * Created by Cristian Ramírez on 13/02/17.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */
public class VehiculoResponse {
    private String message;
    private String response;
    private List<Vehiculo> vehiculos;

    public VehiculoResponse() {
    }

    public VehiculoResponse(String message, String response, List<Vehiculo> vehiculos) {
        this.message = message;
        this.response = response;
        this.vehiculos = vehiculos;
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

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
