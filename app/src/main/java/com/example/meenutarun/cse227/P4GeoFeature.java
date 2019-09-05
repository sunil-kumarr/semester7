package com.example.meenutarun.cse227;
//add dependency
//compile 'com.google.android.gms:play-services-location:15.0.1'
//compile 'com.google.android.gms:play-services-places:15.0.1'
//compile 'com.google.android.gms:play-services-maps:15.0.1'

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
public class P4GeoFeature extends AppCompatActivity {
/*    TextView tvlatitue, tvlongitude, tvphysicaladdress;

    //FusedLocationProviderClient is for interacting with the location using fused location provider.

    FusedLocationProviderClient fusedLocationProviderClient;

    Location lastlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4_geo_feature);
      tvlatitue = (TextView)findViewById(R.id.latitude);
        tvlongitude = (TextView)findViewById(R.id.longitude);
        tvphysicaladdress = (TextView)findViewById(R.id.physicaladdress);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(P4GeoFeature.this);


    }

    public void dothis(View view) {
        getLocation();
    }

    void getLocation()
    {
        // request for permission if not granted and get the result on onRequestPermissionsResult overridden method
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }
        else
        {
            Log.d("TAG", "getLocation: permissions granted");
           fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if(location != null)
                    {
                        lastlocation = location;
                        double latitude = lastlocation.getLatitude();
                        double longitue = lastlocation.getLongitude();

                        tvlatitue.setText(""+latitude);
                        tvlongitude.setText(""+longitue);

                  // Geocoder class is used for Geocoding as well as
                        // Reverse Geocoding. Geocoding refers to
                        // transforming street address or any address
                        // into latitude and longitude.
                        Geocoder geocoder = new Geocoder(P4GeoFeature.this, Locale.getDefault());


                        try {
                            List<Address> locationlist = geocoder.getFromLocation(latitude,longitue,1);


                            if(locationlist.size() >0)
                            {
                                Address address = locationlist.get(0);


                                tvphysicaladdress.setText("Address is:"+ address);
                            }

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    getLocation();
                else
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void openMap(View view) {

        startActivity(new Intent(this,P4MapActivity.class));

    }
*/
}

