package br.com.mrafaelbatista.aprendendogooglemapssdk;

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

        //Adicionando marcador no Parque do Povo (Campina Grande-PB)
        LatLng parqueDoPovo = new LatLng(-7.223702, -35.887580);

        //Adiconando um CircleOptions
       /* CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(parqueDoPovo);
        circleOptions.radius(500); //em metros
        circleOptions.fillColor(Color.argb(100, 255, 255, 204)); //alpha 0 to 255
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.BLACK);
        mMap.addCircle(circleOptions);*/

       //Adicionando PolygonOptions
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-7.223532, -35.887507));
        polygonOptions.add(new LatLng(-7.223743, -35.887890));
        polygonOptions.add(new LatLng(-7.224012, -35.887729));
        polygonOptions.add(new LatLng(-7.223786, -35.887347));
        polygonOptions.fillColor(Color.argb(100,153, 204, 255));
        polygonOptions.strokeWidth(10);
        polygonOptions.fillColor(Color.YELLOW);

        mMap.addPolygon(polygonOptions);


        //Alterar a exibição do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Adicionando evento de click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Meu local")
                        .snippet("Descrição do meu local")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_loja)));

            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Meu local")
                        .snippet("Descrição do meu local")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            }
        });



        //Adicionando um Marcador
        mMap.addMarker(
                new MarkerOptions()
                        .position(parqueDoPovo)
                        .title("Parque do Povo - Aula")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.fireworks)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parqueDoPovo, 15)); /* 2.0 até 21.0 */
    }
}
