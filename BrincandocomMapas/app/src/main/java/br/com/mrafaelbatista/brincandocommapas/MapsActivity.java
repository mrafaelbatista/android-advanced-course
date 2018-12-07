package br.com.mrafaelbatista.brincandocommapas;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        //Alterar exibição do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Adicionar marker no Almeidão
        LatLng almeidao =  new LatLng(-7.167038, -34.873445);

        /*CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(almeidao);
        circleOptions.radius(500); // em metros
        circleOptions.fillColor(Color.argb(100, 255, 153, 102)); //0 to 255 alpha
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.GREEN);
        mMap.addCircle(circleOptions);*/

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-7.165244, -34.873326));
        polygonOptions.add(new LatLng(-7.167958, -34.871835));
        polygonOptions.add(new LatLng(-7.167064, -34.875150));
        polygonOptions.fillColor(Color.argb(100, 255, 153, 102));
        polygonOptions.strokeColor(Color.GREEN);
        polygonOptions.strokeWidth(10);

        mMap.addPolygon(polygonOptions);





        //Adicionando evento de click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Seu local")
                        .snippet("Uma descrição que você pode adicionar")
                        .icon(
                                /*BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)*/
                                BitmapDescriptorFactory.fromResource(R.drawable.icone_loja)
                        ));
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Seu local")
                        .snippet("Uma descrição que você pode adicionar")
                        .icon(
                                /*BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)*/
                                BitmapDescriptorFactory.fromResource(R.drawable.icone_carro_roxo_48px)
                        ));
            }
        });


        //Alterações no marcador
        mMap.addMarker(new MarkerOptions()
                .position(almeidao)
                .title("Estádio Almeidão")
                .icon(
                        /*BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)*/
                        BitmapDescriptorFactory.fromResource(R.drawable.soccer_field)
                ));

        //Mover a câmera e alteração do Zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(almeidao, 16)); /*Zoom = 2.0 e 21.0*/
    }
}
