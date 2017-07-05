package co.com.yavasoft.www.mitaxi._Cliente;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import co.com.yavasoft.www.mitaxi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class _HomeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<String> positions;
    LocationManager locationManager;
    double latitud;
    double longitud;

    public _HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        ActivityCompat.requestPermissions( (Activity) getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1 );
        locationManager = (LocationManager) getContext().getSystemService( Context.LOCATION_SERVICE );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        SupportMapFragment mapFragment1 = (SupportMapFragment) getChildFragmentManager().findFragmentById( R.id.mapView );
        mapFragment1.getMapAsync( this );

    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder( getContext() );
        dialog.setTitle( "Enable Location" )
                .setMessage( "Su ubicación esta desactivada.\npor favor active su ubicación " +
                        "usa esta app" )
                .setPositiveButton( "Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mi = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                        startActivity( mi );
                    }
                } )
                .setNegativeButton( "Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                } );
        dialog.show();
    }


    private boolean isLocationEnabled() {
        boolean enableOrDis = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
        boolean enOrdis =  locationManager.isProviderEnabled( LocationManager.NETWORK_PROVIDER );
        Log.d( "Com ---- ", String.valueOf( enableOrDis ) );
        return  (enableOrDis || enOrdis);

    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void setUpMap() {
        checkLocation();

        if (ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled( true );
        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        comenzarLocalizacion();
        final LatLng position = new LatLng( latitud, longitud );
        //mMap.addMarker( new MarkerOptions().position( position ).title( "Mi positions" ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( position, 13 ) );
        mMap.getUiSettings().setCompassEnabled( true );

    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    private void comenzarLocalizacion() {
        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getContext().getSystemService( context );

        Criteria crta = new Criteria();
        crta.setAccuracy( Criteria.ACCURACY_FINE );
        crta.setAltitudeRequired( false );
        crta.setBearingRequired( false );
        crta.setCostAllowed( true );
        crta.setPowerRequirement( Criteria.POWER_LOW );
        String provider = locationManager.getBestProvider( crta, true );

        if (ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation( provider );
        updateWithNewLocation(location);

        locationManager.requestLocationUpdates(provider, 15000, 0,
                locationListener);
    }

    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };


    private void updateWithNewLocation(Location location) {

        if (location != null) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();

            Log.e("LOCATION", "your Current Position is : " + latitud + " -  "+ longitud);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }
}
