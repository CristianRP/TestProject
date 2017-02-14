package com.mayantech.checkin.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mayantech.checkin.R;

import java.util.Calendar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalleVisitaActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindString(R.string.title_activity_detalle_visita)
    String activityName;
    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_visita);
        ButterKnife.bind(this);

        setToolbar();

        mMapView.onCreate(savedInstanceState);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (status == ConnectionResult.SUCCESS) {
            Log.e("alv", "map");
            mMapView.getMapAsync(this);
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
            dialog.show();
        }

        String date = "16/2/2017";
        String parts[] = date.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milliTime = calendar.getTimeInMillis();

        mCalendarView.setDate(milliTime, true, true);

    }

    /**
     * Establece la toolbar como action bar
     */
    private void setToolbar() {
        setSupportActionBar(mToolbar);
        setTitle(activityName);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        Intent data = getIntent();
        LatLng latlng;
        // Add a marker in Sydney and move the camera
        latlng = new LatLng(14.651306666666668, -90.45510666666665);
        mMap.addMarker(new MarkerOptions().position(latlng).title("Test").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        float zoomLevel = 16;
        mMap.setMinZoomPreference(zoomLevel);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            try {
                mMapView.onDestroy();
            } catch (NullPointerException np) {
                Log.e("DetallePublicidad", "Error while attempting onDestroy", np);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null) {
            mMapView.onLowMemory();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
