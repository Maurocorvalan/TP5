package com.ulp.tp5.ui.mapas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ulp.tp5.R;

public class MapsFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap googleMap;

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsFragment.this.googleMap = googleMap;
            getLocationAndMoveCamera();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
    }

    private void getLocationAndMoveCamera() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Handle permissions here
            return;
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), location -> {
                    if (location != null && googleMap != null) {
                        LatLng ubicacionActual = new LatLng(location.getLatitude(), location.getLongitude());
                        LatLng farmacia1 = new LatLng(-33.266367, -66.307099);
                        LatLng farmacia2 = new LatLng(-33.265617, -66.298616);
                        LatLng farmacia3 = new LatLng(-33.270978, -66.307442);
                        LatLng farmacia4 = new LatLng(-33.261107, -66.315230);
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Ubicacion actual"));
                        googleMap.addMarker(new MarkerOptions().position(farmacia1).title("Farmacia Gardel"));
                        googleMap.addMarker(new MarkerOptions().position(farmacia2).title("Farmacia DaVinci"));
                        googleMap.addMarker(new MarkerOptions().position(farmacia3).title("Farmacia Hospital Cerro de la Cruz"));
                        googleMap.addMarker(new MarkerOptions().position(farmacia4).title("Farmacia Eva Peron"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual, 15));
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        ///
                    }
                });
    }
}
