package com.mayantech.checkin.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.mayantech.checkin.data.api.model.Asesor;

/**
 * Created by Cristian Ramírez on 22/01/2017.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */

public class SessionPrefs {

    public static final String PREFS_NAME = "CHECKIN_PREFS";
    public static final String PREF_CODIGO_ASESOR = "PREF_CODIGO_ASESOR";
    public static final String PREF_NOMBRE_ASESOR = "PREF_NOMBRE_ASESOR";
    public static final String PREF_ID_CONSULTOR = "PREF_ID_CONSULTOR";
    public static final String PREF_ASESOR_USER_CRM = "PREF_USER_CRM";
    public static final String PREF_ASESOR_USER_PASSWORD_CRM = "PREF_PASSWORD_CRM";
    public static final String PREF_ASESOR_USER_APP= "PREF_USER_APP";
    public static final String PREF_ASESOR_USER_PASSWORD_APP = "PREF_PASSWORD_APP";

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

        //mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_AFFILAITE_TOKEN, null));
    }


    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void saveAsesor(Asesor asesor) {
        if (asesor != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(PREF_CODIGO_ASESOR, asesor.getCodigo());
            editor.putString(PREF_NOMBRE_ASESOR, asesor.getNombre());
            editor.putString(PREF_ID_CONSULTOR, String.valueOf(asesor.getId_consultor()));
            editor.apply();
            mIsLoggedIn = true;
        }
    }

    public void logOut() {
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_CODIGO_ASESOR, null);
        editor.putString(PREF_NOMBRE_ASESOR, null);
        editor.putString(PREF_ID_CONSULTOR, null);
        editor.apply();
    }

}
