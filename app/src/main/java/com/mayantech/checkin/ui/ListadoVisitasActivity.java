package com.mayantech.checkin.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mayantech.checkin.R;
import com.mayantech.checkin.adapter.VisitasAdapter;
import com.mayantech.checkin.data.api.CheckinApi;
import com.mayantech.checkin.data.api.model.ApiError;
import com.mayantech.checkin.data.api.model.visita.Visita;
import com.mayantech.checkin.data.api.model.visita.VisitaResponse;
import com.mayantech.checkin.data.api.model.visita.VisitasBody;
import com.mayantech.checkin.data.prefs.SessionPrefs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoVisitasActivity extends AppCompatActivity
        implements VisitasAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerVisitas)
    RecyclerView mRecyclerVisitas;
    @BindView(R.id.content_listado_visitas)
    RelativeLayout mContentListadoVisitas;
    @BindString(R.string.title_activity_listado_visita)
    String activityName;

    private Retrofit mRestAdapter;
    private CheckinApi mCheckinApi;
    private ProgressDialog mProgressDialog;
    private LinearLayoutManager mLayoutManager;
    private VisitasAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!SessionPrefs.get(this).isLoggedIn()) {
            startActivity(new Intent(ListadoVisitasActivity.this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_listado_visitas);
        ButterKnife.bind(this);

        setToolbar();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        mRestAdapter = new Retrofit.Builder()
                .baseUrl(CheckinApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mCheckinApi = mRestAdapter.create(CheckinApi.class);

        mRecyclerVisitas.setHasFixedSize(true);

        mRecyclerVisitas.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerVisitas.setLayoutManager(mLayoutManager);

        mRecyclerVisitas.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Obteniendo visitas...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();


        VisitasBody visitasBody = new VisitasBody(
                SessionPrefs.getString(ListadoVisitasActivity.this,
                        SessionPrefs.PREF_ASESOR_USER_APP),
                SessionPrefs.getString(ListadoVisitasActivity.this,
                        SessionPrefs.PREF_ASESOR_USER_PASSWORD_APP),
                Long.parseLong(SessionPrefs.getString(ListadoVisitasActivity.this,
                        SessionPrefs.PREF_ID_CONSULTOR))
        );

        getListadoParVisitas(visitasBody);

    }

    /**
     * Establece la toolbar como action bar
     */
    private void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(activityName);
        final ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null) {
        // Establecer icono del drawer toggle
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado_visitas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getListadoParVisitas(VisitasBody visitasBody) {
        Call<VisitaResponse> visitaResponseCall = mCheckinApi.getVisitas(visitasBody);
        visitaResponseCall.enqueue(new Callback<VisitaResponse>() {
            @Override
            public void onResponse(Call<VisitaResponse> call, Response<VisitaResponse> response) {
                Log.e("listado_visitas", "getVisitas()");
                mProgressDialog.dismiss();
                // Procesar errores
                if (!response.isSuccessful()) {
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                        error = apiError.getMessage();
                        Log.e("ListadoVisitas", " " + apiError.getResponse());
                    } else {
                        try {
                            // Reportar causas de error no relacionado con la API
                            Log.e("ListadoVisitas", " " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Toast.makeText(ListadoVisitasActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e("ListadoVisitasActivity", " " + error);
                    return;
                }

                Visita.VISITAS = response.body().getVisita();
                for (Visita visita : Visita.VISITAS) {
                    Log.e("visita", " " + visita.getCliente().getNombreCuenta());
                }

                mAdapter = new VisitasAdapter(Visita.VISITAS, ListadoVisitasActivity.this);
                mAdapter.setHasStableIds(true);
                mAdapter.setOnItemClickListener(ListadoVisitasActivity.this);
                mRecyclerVisitas.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<VisitaResponse> call, Throwable t) {
                mProgressDialog.dismiss();
                Log.e("retrofit-visitas", "Error al recuperar las visitas " + t.getMessage() +
                        t.getLocalizedMessage() + t.getLocalizedMessage() + t.getCause() +
                        t.getStackTrace());
            }
        });

    }

    @Override
    public void onItemClick(VisitasAdapter.ViewHolder item, int position) {
        Intent visita = new Intent(ListadoVisitasActivity.this, DetalleVisitaActivity.class);
        Visita v = Visita.VISITAS.get(position);
        visita.putExtra("idVisita", v.getIdVisita());
        startActivity(visita);
    }
}
