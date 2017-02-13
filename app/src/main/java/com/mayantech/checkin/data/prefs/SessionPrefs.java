package com.mayantech.checkin.data.prefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.mayantech.checkin.data.api.model.login.Asesor;

/**
 * Created by Cristian Ramírez on 22/01/2017.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */

public class SessionPrefs {

    public static final String PREFS_NAME = "CHECKIN_PREFS";
    public static final String PREF_NOMBRE_ASESOR = "PREF_NOMBRE_ASESOR";
    public static final String PREF_CODIGO_ASESOR = "PREF_CODIGO_ASESOR";
    public static final String PREF_ID_CONSULTOR = "PREF_ID_CONSULTOR";
    public static final String PREF_ASESOR_USER_CRM = "PREF_USER_CRM";
    public static final String PREF_ASESOR_USER_PASSWORD_CRM = "PREF_PASSWORD_CRM";
    public static final String PREF_ASESOR_USER_APP= "PREF_USER_APP";
    public static final String PREF_ASESOR_USER_PASSWORD_APP = "PREF_PASSWORD_APP";
    public static final String PREF_RESPONSE = "PREF_RESPONSE";
    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_RESPONSE, null));
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void saveDataPref(Asesor asesor, String user_crm, String pass_crm,
                           String user_app, String pass_app, String response) {
        SharedPreferences.Editor editor = mPrefs.edit();
        if (asesor != null) {
            editor.putString(PREF_CODIGO_ASESOR, asesor.getCodigo());
            editor.putString(PREF_NOMBRE_ASESOR, asesor.getNombre());
            editor.putString(PREF_ID_CONSULTOR, String.valueOf(asesor.getId_consultor()));
        }
        editor.putString(PREF_ASESOR_USER_CRM, user_crm);
        editor.putString(PREF_ASESOR_USER_PASSWORD_CRM, pass_crm);
        editor.putString(PREF_ASESOR_USER_APP, user_app);
        editor.putString(PREF_ASESOR_USER_PASSWORD_APP, pass_app);
        editor.putString(PREF_RESPONSE, response);
        editor.apply();
        mIsLoggedIn = true;
    }

    public void logOut() {
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_CODIGO_ASESOR, null);
        editor.putString(PREF_NOMBRE_ASESOR, null);
        editor.putString(PREF_ID_CONSULTOR, null);
        editor.putString(PREF_ASESOR_USER_CRM, null);
        editor.putString(PREF_ASESOR_USER_PASSWORD_CRM, null);
        editor.putString(PREF_ASESOR_USER_APP, null);
        editor.putString(PREF_ASESOR_USER_PASSWORD_APP, null);
        editor.putString(PREF_RESPONSE, null);
        editor.apply();
    }

    public static String getString(Context mContext, String key){
        SharedPreferences pref = mContext.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        return pref.getString(key, null);
    }

}
