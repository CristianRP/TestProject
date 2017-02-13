package com.mayantech.checkin.data.api;

import com.mayantech.checkin.data.api.model.login.LoginBody;
import com.mayantech.checkin.data.api.model.login.LoginResponse;
import com.mayantech.checkin.data.api.model.visita.VisitaResponse;
import com.mayantech.checkin.data.api.model.visita.VisitasBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Cristian Ramírez on 22/01/2017.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */

public interface CheckinApi {

    public static final String BASE_URL = "http://104.130.143.72:8080/ncomercial/app-rest/";

    @POST("autenticacion/")
    Call<LoginResponse> autenticacion(@Body LoginBody loginBody);

    @POST("get_visitas/")
    Call<VisitaResponse> getVisitas(@Body VisitasBody visitasBody);

}
