package usuario.example.tpo4.ui.home;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeViewModel extends ViewModel implements OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private MutableLiveData<Boolean> locationPermissionGranted = new MutableLiveData<>();
    private MutableLiveData<GoogleMap> map = new MutableLiveData<>();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private FragmentActivity fragmentActivity;



    public LiveData<Boolean> getLocationPermissionGranted() { return locationPermissionGranted; }

    public LiveData<GoogleMap> getMap() { return map; }

    public void initMap(Fragment fragment, MapView mapView) {
        fragmentActivity = fragment.getActivity();

//        Pedir permiso al usuario para usar la ubicacion
        if (ActivityCompat.checkSelfPermission(fragmentActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted.setValue(false);
            ActivityCompat.requestPermissions(fragmentActivity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }

        mapView.onCreate(null);
        mapView.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(fragment.requireActivity());
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map.setValue(googleMap);

        if (ActivityCompat.checkSelfPermission(fragmentActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted.setValue(true);
            googleMap.setMyLocationEnabled(true);
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f));

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(currentLatLng);
                    markerOptions.title("Mi ubicacion");
                    googleMap.addMarker(markerOptions);
                }
            });
        } else {
            locationPermissionGranted.setValue(false);
        }
    }
}