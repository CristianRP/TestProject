package com.mayantech.checkin.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mayantech.checkin.R;
import com.mayantech.checkin.data.api.CheckinApi;
import com.mayantech.checkin.data.api.model.ApiError;
import com.mayantech.checkin.data.api.model.Asesor;
import com.mayantech.checkin.data.api.model.LoginBody;
import com.mayantech.checkin.data.prefs.SessionPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Retrofit mRestAdapter;
    private CheckinApi mCheckinApi;

    @BindView(R.id.login_progress)
    ProgressBar mProgressView;
    @BindView(R.id.image_logo)
    ImageView mLogoView;
    @BindView(R.id.username_crm)
    TextInputEditText mUserCrmView;
    @BindView(R.id.float_labe_username_crm)
    TextInputLayout mFloatLabelUserCrm;
    @BindView(R.id.password_crm)
    TextInputEditText mPasswordCrmView;
    @BindView(R.id.float_label_password_crm)
    TextInputLayout mFloatLabelPasswordCrm;
    @BindView(R.id.username_app)
    TextInputEditText mUserView;
    @BindView(R.id.float_label_user_id)
    TextInputLayout mFloatLabelUser;
    @BindView(R.id.password_app)
    TextInputEditText mPasswordView;
    @BindView(R.id.float_label_password)
    TextInputLayout mFloatLabelPassword;
    @BindView(R.id.email_sign_in_button)
    Button mLoginButton;
    @BindView(R.id.email_login_form)
    LinearLayout mLinearLogin;
    @BindView(R.id.login_form)
    ScrollView mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mRestAdapter = new Retrofit.Builder()
                .baseUrl(CheckinApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mCheckinApi = mRestAdapter.create(CheckinApi.class);

        mPasswordCrmView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    if (!isOnline()) {
                        showLoginError(getString(R.string.error_network));
                        return false;
                    }
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    if (!isOnline()) {
                        showLoginError(getString(R.string.error_network));
                        return false;
                    }
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnline()) {
                    showLoginError(getString(R.string.error_network));
                    return;
                }
                attemptLogin();
            }
        });

    }

    private void attemptLogin() {
        // Reset errors
        mFloatLabelUserCrm.setError(null);
        mFloatLabelPasswordCrm.setError(null);
        mFloatLabelUser.setError(null);
        mFloatLabelPassword.setError(null);

        // Store values at the time of the login attempt
        String user_crm = mUserCrmView.getText().toString();
        String password_crm = mPasswordCrmView.getText().toString();
        String user_app = mUserView.getText().toString();
        String password_app = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password crm, if the user entered one
        if (TextUtils.isEmpty(password_crm)) {
            mFloatLabelPasswordCrm.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPasswordCrm;
            cancel = true;
        } else if (!isPasswordValid(password_crm)) {
            mFloatLabelPasswordCrm.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPasswordCrm;
            cancel = true;
        }

        // Check for a valid password app, if the user entered one
        if (TextUtils.isEmpty(password_app)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        } else if (!isPasswordValid(password_app)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        }

        // Verifica si el usuario esta vacio o no es un email (user_crm)
        if (TextUtils.isEmpty(user_crm)) {
            mFloatLabelUserCrm.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelUserCrm;
            cancel = true;
        } else if (!isValidEmailAddress(user_crm)) {
            mFloatLabelUserCrm.setError(getString(R.string.error_invalid_user_crm));
            focusView = mFloatLabelUserCrm;
            cancel = true;
        }

        // Verifica si el usuario esta vacio o no es un email (user_app)
        if (TextUtils.isEmpty(user_crm)) {
            mFloatLabelUser.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelUser;
            cancel = true;
        } else if (!isValidUserApp(user_crm)) {
            mFloatLabelUser.setError(getString(R.string.error_invalid_user_app));
            focusView = mFloatLabelUser;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Muestra el inidicador de carga y luego inicia la petición asincrona
            showProgress(true);
            Call<Asesor> loginCall = mCheckinApi.login(new LoginBody(user_crm,
                    password_crm, user_app, password_app));
            loginCall.enqueue(new Callback<Asesor>() {
                @Override
                public void onResponse(Call<Asesor> call, Response<Asesor> response) {
                    // Ocultar progreso
                    showProgress(false);
                    // Procesar errores
                    if (!response.isSuccessful()) {
                        String error;
                        if (response.errorBody()
                                .contentType()
                                .subtype()
                                .equals("application/json")) {
                            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                            error = apiError.getMessage();
                            Log.d("LoginActivity", apiError.getDeveloperMessage());
                        } else {
                            error = response.message();
                        }
                        showLoginError(error);
                        return;
                    }

                    // Guarda el asesor en las preferencias
                    SessionPrefs.get(LoginActivity.this).saveAsesor(response.body());
                    Log.e("LoginAcitivity", "inicio con exito " + response.body());
                    // Show listado par de visitas
                    showListadoParVisitas();

                }

                @Override
                public void onFailure(Call<Asesor> call, Throwable t) {
                    showProgress(false);
                    showLoginError(t.getMessage());
                }
            });
        }



    }

    private static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private static boolean isValidUserApp(String username) {
        return username.length() > 4;
    }

    private static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        int visibility = show ? View.GONE : View.VISIBLE;
        mLogoView.setVisibility(visibility);
        mLoginFormView.setVisibility(visibility);
    }

    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void showListadoParVisitas() {
        startActivity(new Intent(LoginActivity.this, ListadoVisitasActivity.class));
        finish();
    }

}
