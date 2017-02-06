package com.mayantech.checkin.data.api;

import com.mayantech.checkin.data.api.model.Asesor;
import com.mayantech.checkin.data.api.model.LoginBody;

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
    Call<Asesor> login(@Body LoginBody loginBody);

}
