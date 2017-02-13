package com.mayantech.checkin.data.api.model.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 22/01/2017.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */

public class LoginBody {
    @SerializedName("user-crm")
    private String user_crm;
    @SerializedName("password-crm")
    private String password_crm;
    @SerializedName("user-app")
    private String user_app;
    @SerializedName("password-app")
    private String password_app;

    public LoginBody(String user_crm, String password_crm, String user_app, String password_app) {
        this.user_crm = user_crm;
        this.password_crm = password_crm;
        this.user_app = user_app;
        this.password_app = password_app;
    }

    public String getUser_crm() {
        return user_crm;
    }

    public void setUser_crm(String user_crm) {
        this.user_crm = user_crm;
    }

    public String getPassword_crm() {
        return password_crm;
    }

    public void setPassword_crm(String password_crm) {
        this.password_crm = password_crm;
    }

    public String getUser_app() {
        return user_app;
    }

    public void setUser_app(String user_app) {
        this.user_app = user_app;
    }

    public String getPassword_app() {
        return password_app;
    }

    public void setPassword_app(String password_app) {
        this.password_app = password_app;
    }
}
